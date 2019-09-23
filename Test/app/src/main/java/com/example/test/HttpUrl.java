package com.example.test;
import okhttp3.OkHttpClient;
import okhttp3.Request;
public class HttpUrl {
    public static void sendOkHttp(String address,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }
}
