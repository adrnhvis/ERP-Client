package com.adrnhvis.erp_campaigns.ViewModel;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.adrnhvis.erp_campaigns.model.Campaigns;
import com.adrnhvis.erp_campaigns.repository.CampaignsRepository;
import java.util.List;

public class CampaignsViewModel extends ViewModel {
    private final CampaignsRepository repository;
    // Inisialisasi satu kali agar objek LiveData tetap abadi
    private final MutableLiveData<List<Campaigns>> campaignsLiveData = new MutableLiveData<>();

    public CampaignsViewModel(Context context) {
        repository = new CampaignsRepository(context);
    }

    // Metode ini hanya untuk di-observe oleh MainActivity (dipanggil 1x di onCreate)
    public LiveData<List<Campaigns>> getCampaignsLiveData() {
        return campaignsLiveData;
    }

    // Metode ini untuk men-trigger API agar memuat ulang data (dipanggil di onResume)
    public void loadCampaigns(int page) {
        repository.fetchCampaigns(page, campaignsLiveData);
    }

    // --- Factory ---
    public static class Factory implements ViewModelProvider.Factory {
        private final Context context;

        public Factory(Context context) {
            this.context = context.getApplicationContext();
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            if (modelClass.isAssignableFrom(CampaignsViewModel.class)) {
                return (T) new CampaignsViewModel(context);
            }
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}