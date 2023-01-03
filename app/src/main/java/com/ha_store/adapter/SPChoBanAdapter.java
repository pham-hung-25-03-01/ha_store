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

public class SPChoBanAdapter extends RecyclerView.Adapter<SPChoBanAdapter.ViewHolder> {
    List<SanPhamDTO> ds_sp_cho_ban;
    List<AnhSanPhamDTO> list_anh;
    SanPhamDAO sp_dao;
    public SPChoBanAdapter(List<SanPhamDTO> ds_sp_cho_ban) {
        this.ds_sp_cho_ban = ds_sp_cho_ban;
        sp_dao = new SanPhamDAO();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view_sp = inflater.inflate(R.layout.item_product,parent,false);
        ViewHolder viewHolder = new ViewHolder(view_sp);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SanPhamDTO sp = ds_sp_cho_ban.get(position);
        DecimalFormat format = new DecimalFormat("###,###,###");
        list_anh = new ArrayList<>();

        list_anh = sp_dao.LayDanhSachAnhSanPham(sp.get_id());

        if(list_anh.size()>0){
            Glide.with(holder.context).load(list_anh.get(0).getAnh_san_pham_url().toString()).placeholder(R.drawable.ic_launcher_background).into(holder.product_img);
        }else{
            Glide.with(holder.context).load("https://thumbs.dreamstime.com/b/icono-de-color-rgb-marcador-posici%C3%B3n-galer%C3%ADa-im%C3%A1genes-miniatura-la-foto-%C3%A1lbum-disponible-medios-digitales-archivo-multimedia-187369540.jpg").placeholder(R.drawable.ic_launcher_background).into(holder.product_img);
        }
        String ten_sp = sp.get_ten_san_pham();
        if(ten_sp.length()>50){
            holder.product_name.setText(ten_sp.substring(0,50)+"...");
        }else {
            holder.product_name.setText(ten_sp);
        }
        int gia_giam = (int)(sp.get_gia_ban().doubleValue()*(1-sp.get_phan_tram_khuyen_mai()));
        holder.product_price.setText(format.format(gia_giam).toString()+"đ");
        String khuyen_mai = String.valueOf((int)(sp.get_phan_tram_khuyen_mai()*100));
        holder.product_discount.setText("-"+khuyen_mai+"%");
        holder.product_evaluate.setText(sp.get_diem_xep_hang().toString());
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
        if(ds_sp_cho_ban!= null) {
            return ds_sp_cho_ban.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView product_img;
        TextView product_name, product_price,product_discount,product_evaluate;
        Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            product_img = itemView.findViewById(R.id.product_img);
            product_name = itemView.findViewById(R.id.product_name);
            product_price = itemView.findViewById(R.id.product_price);
            product_discount = itemView.findViewById(R.id.product_discount);
            product_evaluate = itemView.findViewById(R.id.product_evaluate);


        }
    }
}
