package com.adrnhvis.erp_campaigns;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adrnhvis.erp_campaigns.ViewModel.CampaignsViewModel;
import com.adrnhvis.erp_campaigns.utils.TokenManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private CampaignsViewModel viewModel;
    private CampaignsAdapter adapter;
    private ProgressBar progressBar;
    private TokenManager tokenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tokenManager = new TokenManager(this);
        progressBar = findViewById(R.id.progressBar);

        RecyclerView rvCampaigns = findViewById(R.id.rvCampaigns);
        rvCampaigns.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CampaignsAdapter();
        rvCampaigns.setAdapter(adapter);

        viewModel = new ViewModelProvider(this, new CampaignsViewModel.Factory(this)).get(CampaignsViewModel.class);

        // Hanya mengamati (observe) data. Tidak mengambil data di sini.
        viewModel.getCampaignsLiveData().observe(this, campaigns -> {
            progressBar.setVisibility(View.GONE);
            if (campaigns != null) {
                adapter.updateData(campaigns);
            } else {
                Toast.makeText(this, "Gagal memuat daftar campaign", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);
        fabAdd.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, CreateCampaignActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Setiap kali kembali ke layar ini (misal habis nambah data), paksa refresh API
        progressBar.setVisibility(View.VISIBLE);
        viewModel.loadCampaigns(1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE, 1, Menu.NONE, "Logout").setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == 1) {
            tokenManager.clearToken();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}