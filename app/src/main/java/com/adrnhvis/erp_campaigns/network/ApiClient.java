package com.adrnhvis.erp_campaigns.network;

import android.content.Context;
import com.adrnhvis.erp_campaigns.utils.TokenManager;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://10.0.2.2:8000/api/";
    private static Retrofit retrofit;

    // Tambahkan parameter Context
    public static synchronized Retrofit getClient(Context context) {
        if (retrofit == null) {
            // Inisialisasi TokenManager
            TokenManager tokenManager = new TokenManager(context);

            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(chain -> {
                // Ambil token terbaru dari SharedPreferences
                String token = tokenManager.getToken();
                String bearerToken = (token != null) ? "Bearer " + token : "";

                Request newRequest = chain.request().newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Authorization", bearerToken) // Token disuntikkan secara dinamis
                        .build();
                return chain.proceed(newRequest);
            }).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}