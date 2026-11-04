package com.adrnhvis.erp_campaigns.model;

import com.google.gson.annotations.SerializedName;

public record LoginResponse(
        @SerializedName("message") String message,
        @SerializedName("token") String token
) {}