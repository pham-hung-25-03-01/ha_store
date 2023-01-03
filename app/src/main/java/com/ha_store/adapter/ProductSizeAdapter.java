package com.ha_store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ha_store.R;
import com.ha_store.dto.KichThuocDTO;
import com.ha_store.dto.Option;

import java.util.List;

public class ProductSizeAdapter extends RecyclerView.Adapter<ProductSizeAdapter.ViewHolder> {
    List<KichThuocDTO> ds_chon_size;
    private int selectedPosition = -1;

    public ProductSizeAdapter(List<KichThuocDTO> ds_chon_size) {
        this.ds_chon_size = ds_chon_size;
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
