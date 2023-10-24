package com.wacmob.foodhub.data.remote;

import com.google.gson.Gson;
import com.wacmob.foodhub.BuildConfig;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by KP on 7/17/2018.
 */

@Singleton
public class WebService {

    private Retrofit retrofit = null;

    @Inject
    public WebService() {

    }

    /**
     * This method creates a new instance of the API interface.
     *
     * @return The API interface
     */
    public ApiService getSearchAPI() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        String BASE_URL;

        if (BuildConfig.DEBUG) {
            BASE_URL = ServiceNames.DEV_BASE_URL;
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            httpClient.addInterceptor(logging);
        }else{
            BASE_URL = ServiceNames.PROD_BASE_URL;
        }

        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

        return retrofit.create(ApiService.class);
    }

    /**
     * Sync API
     * @return
     */
    public ApiService getMusicSyncAPI() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        String BASE_URL;

        if (BuildConfig.DEBUG) {
            BASE_URL = ServiceNames.DEV_BASE_URL;
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            httpClient.addInterceptor(logging);
            httpClient.readTimeout(180, TimeUnit.SECONDS);
        }else{
            BASE_URL = ServiceNames.PROD_BASE_URL;
            httpClient.readTimeout(180, TimeUnit.SECONDS);
        }

        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

        return retrofit.create(ApiService.class);
    }

    public ApiService getMusicMockyAPI() {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        String BASE_URL;

        if (BuildConfig.DEBUG) {
            BASE_URL = ServiceNames.MOCKY_BASE_URL;
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            httpClient.addInterceptor(logging);
        }else{
            BASE_URL = ServiceNames.MOCKY_BASE_URL;
        }

        if (retrofit == null) {
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

        return retrofit.create(ApiService.class);
    }

    public static Gson getGson(){
        return new Gson();
    }
}
