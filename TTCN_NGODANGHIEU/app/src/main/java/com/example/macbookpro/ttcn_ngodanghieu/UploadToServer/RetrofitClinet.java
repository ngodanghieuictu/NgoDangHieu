package com.example.macbookpro.ttcn_ngodanghieu.UploadToServer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClinet {
    public static Retrofit retrofit =null;
    public static Retrofit getClient(String baseurl){
        OkHttpClient builder = new OkHttpClient.Builder()
                                .readTimeout(5000, TimeUnit.MILLISECONDS)
                                .writeTimeout(5000,TimeUnit.MILLISECONDS)
                                .connectTimeout(10000,TimeUnit.MILLISECONDS)
                                .retryOnConnectionFailure(true)
                                .build();
        Gson gson =new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                    .baseUrl(baseurl)
                    .client(builder)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
//        if (retrofit==null){
//            retrofit = new Retrofit.Builder().baseUrl(baseurl)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
        return retrofit;
    }
}
