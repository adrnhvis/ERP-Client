package com.adrnhvis.erp_campaigns.repository;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import com.adrnhvis.erp_campaigns.model.Campaigns;
import com.adrnhvis.erp_campaigns.model.CampaignsListResponse;
import com.adrnhvis.erp_campaigns.network.ApiClient;
import com.adrnhvis.erp_campaigns.network.ApiService;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CampaignsRepository {
    private final ApiService apiService;

    public CampaignsRepository(Context context) {
        apiService = ApiClient.getClient(context).create(ApiService.class);
    }

    // Menerima parameter liveData untuk diisi ulang
    public void fetchCampaigns(int page, MutableLiveData<List<Campaigns>> liveData) {
        apiService.getCampaigns(page).enqueue(new Callback<CampaignsListResponse>() {
            @Override
            public void onResponse(Call<CampaignsListResponse> call, Response<CampaignsListResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    liveData.setValue(response.body().data());
                } else {
                    liveData.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<CampaignsListResponse> call, Throwable t) {
                liveData.setValue(null);
            }
        });
    }
}