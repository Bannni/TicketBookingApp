package com.example.ticketbookingapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ticketbookingapp.R;
import com.example.ticketbookingapp.data.Transport;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private List<Transport> list;
    private OnBuyClickListener listener;

    public interface OnBuyClickListener {
        void onBuy(Transport transport);
    }

    public SearchAdapter(List<Transport> list, OnBuyClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transport_buy, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transport t = list.get(position);
        holder.tvInfo.setText(t.type + " - " + t.airline + "\n" + t.route +
                "\nJadwal: " + t.schedule + "\nRp " + t.price);

        holder.btnBuy.setOnClickListener(v -> listener.onBuy(t));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvInfo;
        Button btnBuy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvInfo = itemView.findViewById(R.id.tvInfo);
            btnBuy = itemView.findViewById(R.id.btnBuy);
        }
    }
}
