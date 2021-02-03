package club.differentialmanifold.dy.core.spring.boot.autoconfigure.utils.http;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.Objects;

public class DyCoreOkhttpUtils {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private OkHttpClient okHttpClient;
    private DyCoreUtilsHttpProperties properties;

    DyCoreOkhttpUtils(OkHttpClient okHttpClient, DyCoreUtilsHttpProperties properties) {
        this.okHttpClient = okHttpClient;
        this.properties = properties;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    /**
     * get
     *
     * @param url     请求的url
     * @param queries 请求的参数，在浏览器？后面的数据，没有可以传null
     * @return
     */
    public String get(String url, Map<String, String> queries, Map<String, String> headers) {
        String responseBody = "";
        StringBuilder sb = new StringBuilder(getRequestUrl(url));
        if (!CollectionUtils.isEmpty(queries)) {
            boolean firstFlag = true;
            for (Map.Entry<String, String> entry : queries.entrySet()) {
                if (firstFlag) {
                    sb.append("?")
                            .append(entry.getKey())
                            .append("=")
                            .append(entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&")
                            .append(entry.getKey())
                            .append("=")
                            .append(entry.getValue());
                }
            }
        }
        Request.Builder requestBuilder = new Request.Builder();

        if (!CollectionUtils.isEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder = requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }

        Request request = requestBuilder.url(sb.toString()).build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            //int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            logger.error("okhttp3 put error >> ex = {0}", e);
        }
        return responseBody;
    }

    public String get(String url) {
        return get(url, null, null);
    }

    public String get(String url, Map<String, String> queries) {
        return get(url, queries, null);
    }

    /**
     * Post请求发送JSON数据....{"name":"zhangsan","pwd":"123456"} 参数一：请求Url 参数二：请求的JSON
     * 参数三：请求回调
     */
    public String post(String url, String jsonParams, Map<String, String> headers) {
        String responseBody = "";

        Request.Builder requestBuilder = new Request.Builder();

        if (StringUtils.hasLength(jsonParams)) {
            RequestBody requestBody = RequestBody.create(jsonParams, MediaType.parse("application/json; charset=utf-8"));
            requestBuilder = requestBuilder
                    .post(requestBody);
        }

        if (!CollectionUtils.isEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder = requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }

        Request request = requestBuilder
                .url(getRequestUrl(url))
                .build();
        try (Response response = okHttpClient.newCall(request).execute()) {
            //            int status = response.code();
            if (response.isSuccessful()) {
                return Objects.requireNonNull(response.body()).string();
            }
        } catch (Exception e) {
            logger.error("okhttp3 post error >> ex = {0}", e);
        }
        return responseBody;
    }

    public String post(String url) {
        return post(url, null, null);
    }

    public String post(String url, String jsonParams) {
        return post(url, jsonParams, null);
    }

    private String getRequestUrl(String url) {
        if (url.startsWith("http")) {
            return url;
        }
        return properties.getBasicUrl() + url;
    }

}
