package com.ha_store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ha_store.R;
import com.ha_store.dto.SanPhamDTO;

import java.util.List;

public class SearchedAdapter extends RecyclerView.Adapter<SearchedAdapter.ViewHolder> {
    List<SanPhamDTO> sanPhamDTOs;

    public SearchedAdapter(List<SanPhamDTO> sanPhamDTOs) {
        this.sanPhamDTOs = sanPhamDTOs;
    }

    @NonNull
    @Override
    public SearchedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View categoryView = inflater.inflate(R.layout.item_search,parent,false);
        ViewHolder viewHolder = new ViewHolder(categoryView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchedAdapter.ViewHolder holder, int position) {
        SanPhamDTO sp = sanPhamDTOs.get(position);
        holder.key_search.setText(sp.get_ten_san_pham().toString().substring(0,20)+"...");
    }

    @Override
    public int getItemCount() {
        if (sanPhamDTOs!=null)
            return sanPhamDTOs.size();
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView key_search;
        Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            key_search = itemView.findViewById(R.id.key_search);
            context = itemView.getContext();
        }
    }
}
