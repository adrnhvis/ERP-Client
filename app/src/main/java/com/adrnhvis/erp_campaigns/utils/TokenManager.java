package com.adrnhvis.erp_campaigns.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenManager {
    private static final String PREF_NAME = "erp_campaigns_prefs";
    private static final String KEY_TOKEN = "auth_token";
    private final SharedPreferences prefs;

    // Constructor membutuhkan Context untuk mengakses SharedPreferences
    public TokenManager(Context context) {
        prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    // Fungsi untuk menyimpan token saat login berhasil
    public void saveToken(String token) {
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_TOKEN, token);
        editor.apply(); // apply() berjalan secara asinkron (lebih aman untuk UI)
    }

    // Fungsi untuk mengambil token (misalnya untuk diletakkan di Header API)
    public String getToken() {
        return prefs.getString(KEY_TOKEN, null); // Mengembalikan null jika token belum ada
    }

    // Fungsi untuk menghapus token saat pengguna Logout
    public void clearToken() {
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(KEY_TOKEN);
        editor.apply();
    }
}