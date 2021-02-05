package club.differentialmanifold.dy.core.spring.boot.autoconfigure.utils.http;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import club.differentialmanifold.dy.core.spring.boot.autoconfigure.utils.http.builder.DyOkGetBuilder;
import club.differentialmanifold.dy.core.spring.boot.autoconfigure.utils.http.builder.DyOkPostBuilder;
import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

public class DyOkhttpUtils {

    private static DyOkhttpUtils dyOkhttpUtils;
    private OkHttpClient okHttpClient;

    //防止网络重复请求的tagList;
    private ArrayList<String> onesTag;

    private DyOkhttpUtils() {
        onesTag = new ArrayList<>();
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    public static DyOkhttpUtils getInstance() {
        if (dyOkhttpUtils == null) {
            synchronized (DyOkhttpUtils.class) {
                if (dyOkhttpUtils == null) {
                    dyOkhttpUtils = new DyOkhttpUtils();
                }
            }
        }
        return dyOkhttpUtils;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public ArrayList<String> getOnesTag() {
        return onesTag;
    }

    public static DyOkGetBuilder get() {
        return new DyOkGetBuilder();
    }

    public static DyOkPostBuilder post() {
        return new DyOkPostBuilder();
    }
}
