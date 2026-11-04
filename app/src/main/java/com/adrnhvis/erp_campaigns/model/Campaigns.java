package com.adrnhvis.erp_campaigns.model;

import com.google.gson.annotations.SerializedName;

public record Campaigns(
        @SerializedName("id") int id,
        @SerializedName("name") String name,
        @SerializedName("channel") String channel,
        @SerializedName("start_date") String startDate,
        @SerializedName("end_date") String endDate,
        @SerializedName("budget") double budget,
        @SerializedName("status") String status,
        @SerializedName("description") String description
) {}