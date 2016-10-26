package tck.cn.mvp.contract;

import tck.cn.mvp.model.PhoneBean;

/**
 * Description :
 * <p>
 * Created by tck on 2016/10/26.
 */

public interface MainContract {

    public interface MainView {

        void hideLoading();

        void isSuccess(PhoneBean phoneBean);
    }

    public interface MainPresenter {
        void getWheather(String phone, String key);
    }
}
