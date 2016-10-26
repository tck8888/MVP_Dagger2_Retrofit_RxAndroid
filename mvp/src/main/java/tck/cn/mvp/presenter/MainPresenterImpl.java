package tck.cn.mvp.presenter;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import tck.cn.mvp.api.ApiService;
import tck.cn.mvp.contract.MainContract;
import tck.cn.mvp.model.PhoneBean;

/**
 * Description :
 * <p>
 * Created by tck on 2016/10/26.
 */

public class MainPresenterImpl implements MainContract.MainPresenter {
    private MainContract.MainView mMainView;

    public MainPresenterImpl(MainContract.MainView mainView) {
        mMainView = mainView;
    }

    @Override
    public void getWheather(String phone, String key) {

        Observable<PhoneBean> phoneInfo = new Retrofit.Builder()
                .baseUrl(ApiService.HOST)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class)
                .getPhone(phone, key);

        phoneInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PhoneBean>() {
                    @Override
                    public void onCompleted() {
                        mMainView.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mMainView.hideLoading();
                        mMainView.isSuccess(null);
                    }

                    @Override
                    public void onNext(PhoneBean phoneBean) {
                        mMainView.isSuccess(phoneBean);
                    }
                });

    }
}
