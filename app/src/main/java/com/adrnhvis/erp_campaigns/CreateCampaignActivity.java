package com.adrnhvis.erp_campaigns;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.adrnhvis.erp_campaigns.model.Campaigns;
import com.adrnhvis.erp_campaigns.model.CampaignsResponse;
import com.adrnhvis.erp_campaigns.network.ApiClient;
import com.adrnhvis.erp_campaigns.network.ApiService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCampaignActivity extends AppCompatActivity {

    private TextInputEditText etName, etStartDate, etEndDate, etBudget, etDescription;
    private AutoCompleteTextView acChannel, acStatus;
    private MaterialButton btnSave;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_campaign);

        // Inisialisasi API
        apiService = ApiClient.getClient(this).create(ApiService.class);

        // Inisialisasi View
        etName = findViewById(R.id.etName);
        etStartDate = findViewById(R.id.etStartDate);
        etEndDate = findViewById(R.id.etEndDate);
        etBudget = findViewById(R.id.etBudget);
        etDescription = findViewById(R.id.etDescription);
        acChannel = findViewById(R.id.acChannel);
        acStatus = findViewById(R.id.acStatus);
        btnSave = findViewById(R.id.btnSave);

        setupDropdowns();

        btnSave.setOnClickListener(v -> saveCampaign());
    }

    private void setupDropdowns() {
        // Setup dropdown Channel
        String[] channels = {"Instagram", "TikTok", "Website"};
        ArrayAdapter<String> channelAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, channels);
        acChannel.setAdapter(channelAdapter);

        // Setup dropdown Status
        String[] statuses = {"Draft", "Active", "Finished"};
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, statuses);
        acStatus.setAdapter(statusAdapter);
    }

    private void saveCampaign() {
        String name = etName.getText().toString().trim();
        String channel = acChannel.getText().toString().trim();
        String startDate = etStartDate.getText().toString().trim();
        String endDate = etEndDate.getText().toString().trim();
        String budgetStr = etBudget.getText().toString().trim();
        String status = acStatus.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        // Validasi dasar
        if (name.isEmpty() || channel.isEmpty() || startDate.isEmpty() || endDate.isEmpty() || budgetStr.isEmpty() || status.isEmpty()) {
            Toast.makeText(this, "Harap isi semua field wajib!", Toast.LENGTH_SHORT).show();
            return;
        }

        double budget = Double.parseDouble(budgetStr);

        // Gunakan sintaks record. Untuk pembuatan data baru (POST), id diatur 0.
        Campaigns newCampaign = new Campaigns(0, name, channel, startDate, endDate, budget, status, description);

        // Nonaktifkan tombol sementara
        btnSave.setEnabled(false);
        btnSave.setText("Menyimpan...");

        // Panggil Retrofit API POST
        apiService.createCampaign(newCampaign).enqueue(new Callback<CampaignsResponse>() {
            @Override
            public void onResponse(Call<CampaignsResponse> call, Response<CampaignsResponse> response) {
                btnSave.setEnabled(true);
                btnSave.setText("Simpan Campaign");

                if (response.isSuccessful()) {
                    Toast.makeText(CreateCampaignActivity.this, "Campaign berhasil ditambahkan!", Toast.LENGTH_SHORT).show();
                    finish(); // Tutup activity dan kembali ke halaman sebelumnya
                } else {
                    Toast.makeText(CreateCampaignActivity.this, "Gagal menambahkan data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CampaignsResponse> call, Throwable t) {
                btnSave.setEnabled(true);
                btnSave.setText("Simpan Campaign");
                Toast.makeText(CreateCampaignActivity.this, "Error koneksi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}