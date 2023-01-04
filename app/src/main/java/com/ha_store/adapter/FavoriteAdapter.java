package com.ha_store.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
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

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>{
    List<SanPhamDTO> ds_sp;
    List<AnhSanPhamDTO> list_anh;
    SanPhamDAO sp_dao;
    Context context;
    public FavoriteAdapter(List<SanPhamDTO> ds_sp) {
        this.ds_sp = ds_sp;
        sp_dao = new SanPhamDAO();
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View categoryView = inflater.inflate(R.layout.item_favorite,parent,false);
        FavoriteAdapter.ViewHolder viewHolder = new FavoriteAdapter.ViewHolder(categoryView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
        SanPhamDTO sp = ds_sp.get(position);
        DecimalFormat format = new DecimalFormat("###,###,###");
        holder.txt_name.setText(sp.get_ten_san_pham());
        holder.txt_material.setText(sp.get_chat_lieu());
        holder.txt_price.setText(format.format(sp.get_gia_ban().doubleValue() * (1-sp.get_phan_tram_khuyen_mai())).toString() + "đ");
        holder.txt_origin_price.setText(format.format(sp.get_gia_ban()).toString() + "đ");
        list_anh = new ArrayList<>();

        list_anh = sp_dao.LayDanhSachAnhSanPham(sp.get_id());

        if(list_anh.size()>0){
            Glide.with(holder.context).load(list_anh.get(0).getAnh_san_pham_url().toString()).placeholder(R.drawable.ic_launcher_background).into(holder.sp_hinh);
        }else{
            Glide.with(holder.context).load("https://thumbs.dreamstime.com/b/icono-de-color-rgb-marcador-posici%C3%B3n-galer%C3%ADa-im%C3%A1genes-miniatura-la-foto-%C3%A1lbum-disponible-medios-digitales-archivo-multimedia-187369540.jpg").placeholder(R.drawable.ic_launcher_background).into(holder.sp_hinh);
        }
        if(sp.get_phan_tram_khuyen_mai() > 0){
            holder.txt_origin_price.setVisibility(View.VISIBLE);
        }else{
            holder.txt_origin_price.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = sp.get_id();
                Intent intent = new Intent(holder.context, DetailActivity.class);
                intent.putExtra("id", id);
                view.getContext().startActivity(intent);
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ds_sp.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name;
        TextView txt_material;
        TextView txt_price;
        TextView txt_origin_price;
        ShapeableImageView sp_hinh;
        Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_material = itemView.findViewById(R.id.txt_material);
            txt_price = itemView.findViewById(R.id.txt_price);
            txt_origin_price = itemView.findViewById(R.id.txt_origin_price);
            txt_origin_price.setPaintFlags(txt_origin_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            sp_hinh = itemView.findViewById(R.id.sp_hinh);
        }
    }
}
