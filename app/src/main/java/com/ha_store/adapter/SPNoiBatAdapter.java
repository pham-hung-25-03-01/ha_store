package com.ha_store.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.ha_store.R;
import com.ha_store.bus.DetailActivity;
import com.ha_store.dao.SanPhamDAO;
import com.ha_store.dto.AnhSanPhamDTO;
import com.ha_store.dto.SanPhamDTO;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SPNoiBatAdapter extends RecyclerView.Adapter<SPNoiBatAdapter.ViewHolder> {
    List<SanPhamDTO> ds_sp_noi_bat;
    List<AnhSanPhamDTO> list_anh;
    SanPhamDAO sp_dao;
    public SPNoiBatAdapter(List<SanPhamDTO> ds_sp_noi_bat) {
        this.ds_sp_noi_bat = ds_sp_noi_bat;
        sp_dao = new SanPhamDAO();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view_sp = inflater.inflate(R.layout.item_sp_noi_bat,parent,false);
        ViewHolder viewHolder = new ViewHolder(view_sp);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SanPhamDTO sp = ds_sp_noi_bat.get(position);
        list_anh = new ArrayList<>();

        list_anh = sp_dao.LayDanhSachAnhSanPham(sp.get_id());

        if(list_anh.size()>0){
            Glide.with(holder.context).load(list_anh.get(0).getAnh_san_pham_url().toString()).placeholder(R.drawable.ic_launcher_background).into(holder.sp_hinh);
        }else{
            Glide.with(holder.context).load("https://thumbs.dreamstime.com/b/icono-de-color-rgb-marcador-posici%C3%B3n-galer%C3%ADa-im%C3%A1genes-miniatura-la-foto-%C3%A1lbum-disponible-medios-digitales-archivo-multimedia-187369540.jpg").placeholder(R.drawable.ic_launcher_background).into(holder.sp_hinh);
        }
        holder.txt_ten_sp.setText(sp.get_ten_san_pham().substring(0,18)+"...");
        Context context = holder.context;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = sp.get_id();
                Intent intent = new Intent(holder.context,
                        DetailActivity.class);
                intent.putExtra("id", id);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(ds_sp_noi_bat!= null) {
            return ds_sp_noi_bat.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView sp_hinh;
        TextView txt_ten_sp;
        Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            sp_hinh = itemView.findViewById(R.id.sp_hinh);
            txt_ten_sp = itemView.findViewById(R.id.txt_ten_sp);
        }
    }
}
