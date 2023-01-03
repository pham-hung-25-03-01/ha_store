package com.ha_store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ha_store.R;
import com.ha_store.bus.DetailActivity;
import com.ha_store.dao.KhoDAO;
import com.ha_store.dto.KichThuocDTO;

import java.util.List;

public class ProductSizeAdapter extends RecyclerView.Adapter<ProductSizeAdapter.ViewHolder> {
    List<KichThuocDTO> ds_chon_size;
    KhoDAO kho_dao;
    int sp_id;
    private int selectedPosition = -1;

    public ProductSizeAdapter(int id, List<KichThuocDTO> ds_chon_size) {
        this.ds_chon_size = ds_chon_size;
        sp_id = id;
        kho_dao = new KhoDAO();
    }

    @NonNull
    @Override
    public ProductSizeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View categoryView = inflater.inflate(R.layout.item_product_size,parent,false);
        ViewHolder viewHolder = new ViewHolder(categoryView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductSizeAdapter.ViewHolder holder, int position) {
        KichThuocDTO kt = ds_chon_size.get(position);
        holder.product_size.setText(kt.getTen_kich_thuoc());

        if (selectedPosition == position) {
            holder.itemView.setSelected(true); //using selector drawable
        } else {
            holder.itemView.setSelected(false);
        }
//        holder.product_size.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    selectedPosition = holder.getAdapterPosition();
//                }
//            }
//        });
        holder.itemView.setOnClickListener(v -> {
            if (selectedPosition >= 0)
                notifyItemChanged(selectedPosition);
            selectedPosition = holder.getAdapterPosition();
            notifyItemChanged(selectedPosition);
            int so_luong = kho_dao.LaySoLuongSanPham(sp_id, kt.getId());
            DetailActivity.txt_so_luong.setText("Số lượng ("+String.valueOf(so_luong)+")");
            DetailActivity.txt_size.setText(kt.getTen_kich_thuoc().toString()+" >");
            DetailActivity.so_luong = so_luong;
            DetailActivity.id_kich_thuoc = kt.getId();
        });
    }

    @Override
    public int getItemCount() {
        return ds_chon_size.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RadioButton product_size;
        Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_size = itemView.findViewById(R.id.product_size);
            context = itemView.getContext();

        }
    }
}
