package com.ha_store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ha_store.R;
import com.ha_store.dto.KhuyenMaiDTO;

import java.util.List;

public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.ViewHolder>{
    List<KhuyenMaiDTO> ds_km;
    public DiscountAdapter(List<KhuyenMaiDTO> ds_km) {
        this.ds_km = ds_km;
    }
    @NonNull
    @Override
    public DiscountAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View categoryView = inflater.inflate(R.layout.item_discount,parent,false);
        DiscountAdapter.ViewHolder viewHolder = new DiscountAdapter.ViewHolder(categoryView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountAdapter.ViewHolder holder, int position) {
        KhuyenMaiDTO km = ds_km.get(position);
        holder.txt_id.setText(km.getId());
        holder.txt_so_luong.setText(km.getSo_luong().toString());
    }

    @Override
    public int getItemCount() {
        return ds_km.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_id;
        TextView txt_so_luong;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_id = itemView.findViewById(R.id.txt_id);
            txt_so_luong = itemView.findViewById(R.id.txt_so_luong);
        }
    }
}
