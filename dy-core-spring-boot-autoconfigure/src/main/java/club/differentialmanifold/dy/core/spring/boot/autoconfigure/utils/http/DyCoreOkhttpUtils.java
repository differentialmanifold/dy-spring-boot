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
            logger.error("okhttp3 put error >> ex = {}", e);
        }
        return responseBody;
    }

    public String get(String url) {
        return get(url, null, null);
    }

    public String get(String url, Map<String, String> queries) {
        return get(url, queries, null);
    }


    public String postJson(String url, String jsonParams, Map<String, String> headers) {
        String responseBody = "";

        Request.Builder requestBuilder = new Request.Builder();

        if (!CollectionUtils.isEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder = requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
        Request request = requestBuilder
                .url(getRequestUrl(url))
                .post(requestBody)
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

    public String postJson(String url, String jsonParams) {
        return postJson(url, jsonParams, null);
    }

    public String postForm(String url, Map<String, String> params, Map<String, String> headers) {
        String responseBody = "";

        Request.Builder requestBuilder = new Request.Builder();

        if (!CollectionUtils.isEmpty(headers)) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder = requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }
        }

        FormBody.Builder formBodyBuilder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            formBodyBuilder = formBodyBuilder.add(entry.getKey(), entry.getValue());
        }

        RequestBody formBody = formBodyBuilder.build();

        Request request = requestBuilder
                .url(getRequestUrl(url))
                .post(formBody)
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

    public String postForm(String url, Map<String, String> params) {
        return postForm(url, params, null);
    }

    private String getRequestUrl(String url) {
        if (url.startsWith("http")) {
            return url;
        }
        return properties.getBasicUrl() + url;
    }

}
