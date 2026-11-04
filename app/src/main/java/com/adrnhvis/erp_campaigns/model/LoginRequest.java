package com.adrnhvis.erp_campaigns.model;

import com.google.gson.annotations.SerializedName;

public record LoginRequest(
        @SerializedName("email") String email,
        @SerializedName("password") String password
) {}