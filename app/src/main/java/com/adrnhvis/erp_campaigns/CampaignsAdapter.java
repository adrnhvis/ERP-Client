package com.adrnhvis.erp_campaigns;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.adrnhvis.erp_campaigns.model.Campaigns;
import com.google.android.material.chip.Chip;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CampaignsAdapter extends RecyclerView.Adapter<CampaignsAdapter.ViewHolder> {

    private List<Campaigns> campaignsList = new ArrayList<>();

    public void updateData(List<Campaigns> newCampaigns) {
        this.campaignsList = newCampaigns;
        notifyDataSetChanged(); // Nanti bisa di-upgrade pakai DiffUtil agar lebih smooth
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_campaign, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Campaigns campaign = campaignsList.get(position);

        // Memanggil data dari Record
        holder.tvName.setText(campaign.name());
        holder.tvChannel.setText(campaign.channel());
        holder.tvStatus.setText(campaign.status());

        String dateRange = campaign.startDate() + " s/d " + campaign.endDate();
        holder.tvDate.setText(dateRange);

        String formatBudget = String.format(Locale.getDefault(), "Rp %,.0f", campaign.budget());
        holder.tvBudget.setText(formatBudget);
    }

    @Override
    public int getItemCount() {
        return campaignsList != null ? campaignsList.size() : 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvChannel, tvDate, tvBudget;
        Chip tvStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvChannel = itemView.findViewById(R.id.tvChannel);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvBudget = itemView.findViewById(R.id.tvBudget);
        }
    }
}