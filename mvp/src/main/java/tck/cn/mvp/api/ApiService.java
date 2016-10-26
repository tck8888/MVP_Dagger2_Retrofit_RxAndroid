package tck.cn.mvp.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import tck.cn.mvp.model.PhoneBean;

/**
 * Description :
 * <p>
 * <p>
 * Created by tck on 2016/10/26.
 */

public interface ApiService {

    String HOST = "http://apis.juhe.cn/";
    String KEY = "b82fcb9ebbe077e8c02a3b66cb0d762e";

    @GET("mobile/get")
    Observable<PhoneBean> getPhone(@Query("phone") String phone, @Query("key") String key);
}
