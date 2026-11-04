package com.adrnhvis.erp_campaigns.model;

import com.google.gson.annotations.SerializedName;

public record CampaignsResponse(
        @SerializedName("data") Campaigns data,
        @SerializedName("message") String message
) {}