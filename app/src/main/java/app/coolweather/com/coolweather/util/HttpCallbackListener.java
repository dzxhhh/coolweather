package app.coolweather.com.coolweather.util;

/**
 * Created by dzx on 2016/10/28.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
