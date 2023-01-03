package com.ha_store.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.imageview.ShapeableImageView;
import com.ha_store.R;
import com.ha_store.bus.DetailActivity;
import com.ha_store.dto.SanPhamDTO;

import java.text.DecimalFormat;
import java.util.List;

public class SPTotNhatAdapter extends RecyclerView.Adapter<SPTotNhatAdapter.ViewHolder> {
    List<SanPhamDTO> ds_sp_tot_nhat;

    public SPTotNhatAdapter(List<SanPhamDTO> ds_sp_tot_nhat) {
        this.ds_sp_tot_nhat = ds_sp_tot_nhat;
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
        SanPhamDTO sp = ds_sp_tot_nhat.get(position);
        DecimalFormat format = new DecimalFormat("###,###,###");

        //List<AnhSanPhamDTO> list_anh  = new ArrayList<>();

        //Glide.with(holder.context).load(sp.).placeholder(R.drawable.ic_launcher_background).into(holder.option_img);
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
        if(ds_sp_tot_nhat!= null) {
            return ds_sp_tot_nhat.size();
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
