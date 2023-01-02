package com.ha_store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.ha_store.R;
import com.ha_store.dto.AnhSanPhamDTO;
import com.ha_store.dto.SanPhamDTO;

import java.util.ArrayList;
import java.util.List;

public class SPBanChayAdapter extends RecyclerView.Adapter<SPBanChayAdapter.ViewHolder> {
    List<SanPhamDTO> ds_sp_ban_chay;

    public SPBanChayAdapter(List<SanPhamDTO> ds_sp_ban_chay) {
        this.ds_sp_ban_chay = ds_sp_ban_chay;
    }

    @NonNull
    @Override
    public SPBanChayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view_sp = inflater.inflate(R.layout.item_sp_ban_chay,parent,false);
        SPBanChayAdapter.ViewHolder viewHolder = new SPBanChayAdapter.ViewHolder(view_sp);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SPBanChayAdapter.ViewHolder holder, int position) {
        SanPhamDTO sp = ds_sp_ban_chay.get(position);
        //List<AnhSanPhamDTO> list_anh  = new ArrayList<>();

        //Glide.with(holder.context).load(sp.).placeholder(R.drawable.ic_launcher_background).into(holder.option_img);

        holder.sp_gia_goc.setText(sp.get_gia_ban().toString());
        double gia_giam = sp.get_gia_ban().doubleValue()*sp.get_phan_tram_khuyen_mai();
        holder.sp_gia_giam.setText(String.valueOf(gia_giam));

    }

    @Override
    public int getItemCount() {
        if(ds_sp_ban_chay!= null) {
            return ds_sp_ban_chay.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView sp_hinh;
        TextView sp_gia_giam, sp_gia_goc;
        Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sp_hinh = itemView.findViewById(R.id.sp_hinh);
            sp_gia_giam = itemView.findViewById(R.id.sp_gia_giam);
            sp_gia_goc = itemView.findViewById(R.id.sp_gia_goc);

        }
    }
}
