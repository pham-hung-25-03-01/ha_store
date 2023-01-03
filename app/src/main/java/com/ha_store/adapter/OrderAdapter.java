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
import com.ha_store.bus.OrderDetailActivity;
import com.ha_store.dto.DonDatHangDTO;
import com.ha_store.dto.SanPhamDTO;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>{
    List<DonDatHangDTO> ds_ddh;
    Context context;
    public OrderAdapter(List<DonDatHangDTO> ds_ddh) {
        this.ds_ddh = ds_ddh;
    }
    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View categoryView = inflater.inflate(R.layout.item_order,parent,false);
        OrderAdapter.ViewHolder viewHolder = new OrderAdapter.ViewHolder(categoryView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        DonDatHangDTO ddh = ds_ddh.get(position);
        try {
            DecimalFormat format = new DecimalFormat("###,###,###");
            holder.txt_id.setText(ddh.getId().toString());
            holder.txt_ngay_dat.setText(ddh.getNgay_khoi_tao_chu());
            holder.txt_ngay_giao.setText(ddh.getNgay_giao_chu());
            holder.txt_tong_tien.setText(format.format(ddh.getTong_tien()).toString()+"đ");
            holder.txt_dia_chi.setText(ddh.getDiaChi());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = ddh.getId();
                Intent intent = new Intent(holder.context, OrderDetailActivity.class);
                intent.putExtra("don_dat_hang_id", id);
                view.getContext().startActivity(intent);
                ((Activity)context).finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return ds_ddh.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_id;
        TextView txt_ngay_dat;
        TextView txt_ngay_giao;
        TextView txt_tong_tien;
        TextView txt_dia_chi;
        Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            context = itemView.getContext();
            txt_id = itemView.findViewById(R.id.txt_id);
            txt_ngay_dat = itemView.findViewById(R.id.txt_ngay_dat);
            txt_ngay_giao = itemView.findViewById(R.id.txt_ngay_giao);
            txt_tong_tien = itemView.findViewById(R.id.txt_tong_tien);
            txt_dia_chi = itemView.findViewById(R.id.txt_dia_chi);
        }
    }
}
