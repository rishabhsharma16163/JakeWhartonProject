package com.jakewhartonproject.android.connection;

import android.content.Context;

import com.jakewhartonproject.android.BuildConfig;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    @SuppressWarnings("unused")
    private static final String TAG = "RetrofitClient";

    private static Retrofit sRetrofit = null;
    private static Context sContext;

    public static Retrofit getClient(Context context) {
        if (sRetrofit == null) {
            sContext = context.getApplicationContext();

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(logging);

            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient.build())
                    .build();
        }
        return sRetrofit;
    }

}