package com.ha_store.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ha_store.R;
import com.ha_store.bus.DetailActivity;
import com.ha_store.dao.SanPhamDAO;
import com.ha_store.dto.ChiTietDonDatHangDTO;
import com.ha_store.dto.SanPhamDTO;

import java.text.DecimalFormat;
import java.util.List;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder>{
    List<ChiTietDonDatHangDTO> ds_ctddh;
    Context context;
    SanPhamDAO sanPhamDAO;
    public OrderDetailAdapter(List<ChiTietDonDatHangDTO> ds_ctddh) {
        this.ds_ctddh = ds_ctddh;
        sanPhamDAO = new SanPhamDAO();
    }
    @NonNull
    @Override
    public OrderDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View categoryView = inflater.inflate(R.layout.item_order_detail,parent,false);
        OrderDetailAdapter.ViewHolder viewHolder = new OrderDetailAdapter.ViewHolder(categoryView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderDetailAdapter.ViewHolder holder, int position) {
        ChiTietDonDatHangDTO ctddh = ds_ctddh.get(position);
        SanPhamDTO san_pham = sanPhamDAO.LayThongTinSanPhamTheoId(ctddh.getSan_pham_id());
        DecimalFormat format = new DecimalFormat("###,###,###");
        holder.txt_ten_san_pham.setText(san_pham.get_ten_san_pham());
        holder.txt_chat_lieu.setText(san_pham.get_chat_lieu());
        holder.txt_gia.setText(format.format(ctddh.getGia_ban().doubleValue() * (1 - ctddh.getPhan_tram_khuyen_mai())).toString() + "đ");
        holder.txt_so_luong.setText(ctddh.getSo_luong().toString());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = san_pham.get_id();
                Intent intent = new Intent(holder.context, DetailActivity.class);
                intent.putExtra("id", id);
                view.getContext().startActivity(intent);
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ds_ctddh.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_ten_san_pham;
        TextView txt_chat_lieu;
        TextView txt_gia;
        TextView txt_so_luong;
        Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            txt_ten_san_pham = itemView.findViewById(R.id.txt_ten_san_pham);
            txt_chat_lieu = itemView.findViewById(R.id.txt_chat_lieu);
            txt_gia = itemView.findViewById(R.id.txt_gia);
            txt_so_luong = itemView.findViewById(R.id.txt_so_luong);
        }
    }
}
