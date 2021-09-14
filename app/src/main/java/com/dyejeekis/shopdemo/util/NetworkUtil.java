package com.dyejeekis.shopdemo.util;

import android.util.Log;

import com.dyejeekis.shopdemo.data.remote.ApiHeader;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkUtil {

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private static final OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new LoggingInterceptor())
            .build();

    // synchronous http requests

    public static String get(String url, ApiHeader apiHeader) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader(ApiHeader.USER_ID, apiHeader.getUserId())
                .addHeader(ApiHeader.USER_TOKEN, apiHeader.getUserToken())
                .get()
                .build();
        Response response = client.newCall(request).execute();

        //Log.d("OkHttp", "Response body: " + response.body().string());
        return response.body().string();
    }

    public static String post(String url, ApiHeader apiHeader, String jsonBody) throws IOException {
        RequestBody body = RequestBody.create(JSON, jsonBody);
        Request request = new Request.Builder()
                .url(url)
                .addHeader(ApiHeader.USER_ID, apiHeader.getUserId())
                .addHeader(ApiHeader.USER_TOKEN, apiHeader.getUserToken())
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String put(String url, ApiHeader apiHeader, String jsonBody) throws IOException {
        RequestBody body = RequestBody.create(JSON, jsonBody);
        Request request = new Request.Builder()
                .url(url)
                .addHeader(ApiHeader.USER_ID, apiHeader.getUserId())
                .addHeader(ApiHeader.USER_TOKEN, apiHeader.getUserToken())
                .put(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String delete(String url, ApiHeader apiHeader) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .addHeader(ApiHeader.USER_ID, apiHeader.getUserId())
                .addHeader(ApiHeader.USER_TOKEN, apiHeader.getUserToken())
                .delete()
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static class LoggingInterceptor implements Interceptor {
        @Override public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
            Log.d("OkHttp", String.format("Sending request %s on %s%n%s",
                    request.url(), chain.connection(), request.headers()));

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
            Log.d("OkHttp", String.format("Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }

}
