package com.adrnhvis.erp_campaigns.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public record CampaignsListResponse(
        @SerializedName("data") List<Campaigns> data
) {}