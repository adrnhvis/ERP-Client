package com.adrnhvis.erp_campaigns.network;

import com.adrnhvis.erp_campaigns.model.Campaigns;
import com.adrnhvis.erp_campaigns.model.CampaignsListResponse;
import com.adrnhvis.erp_campaigns.model.CampaignsResponse;
import com.adrnhvis.erp_campaigns.model.LoginRequest;
import com.adrnhvis.erp_campaigns.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("campaigns")
    Call<CampaignsListResponse> getCampaigns(@Query("page") int page);

    @POST("campaigns")
    Call<CampaignsResponse> createCampaign(@Body Campaigns campaign);

    @PATCH("campaigns/{id}")
    Call<CampaignsResponse> updateCampaign(@Path("id") int id, @Body Campaigns campaign);

    @DELETE("campaigns/{id}")
    Call<Void> deleteCampaign(@Path("id") int id);

    // Tambahkan di dalam interface ApiService
    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest request);
}