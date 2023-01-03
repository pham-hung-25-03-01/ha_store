package com.ha_store.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.imageview.ShapeableImageView;
import com.ha_store.R;
import com.ha_store.bus.CartActivity;
import com.ha_store.bus.DetailActivity;
import com.ha_store.bus.LoginActivity;
import com.ha_store.dao.GioHangDAO;
import com.ha_store.dao.KhachHangDAO;
import com.ha_store.dao.KhoDAO;
import com.ha_store.dao.SanPhamDAO;
import com.ha_store.dto.AnhSanPhamDTO;
import com.ha_store.dto.GioHangDTO;
import com.ha_store.dto.KichThuocDTO;
import com.ha_store.dto.Option;
import com.ha_store.dto.SanPhamDTO;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {
    List<GioHangDTO> ds_gio_hang;
    List<AnhSanPhamDTO> list_anh;
    SanPhamDAO sp_dao;
    KhoDAO kho_dao;
    GioHangDAO gh_dao;
    int tong_tien = 0;
    DecimalFormat format;
    int sl = 0;
    public GioHangAdapter(List<GioHangDTO> ds_gio_hang) {
        this.ds_gio_hang = ds_gio_hang;
        list_anh = new ArrayList<>();
        sp_dao = new SanPhamDAO();
        kho_dao = new KhoDAO();
        gh_dao = new GioHangDAO();
        format = new DecimalFormat("###,###,###");
    }

    @NonNull
    @Override
    public GioHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View categoryView = inflater.inflate(R.layout.item_cart,parent,false);
        ViewHolder viewHolder = new ViewHolder(categoryView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangAdapter.ViewHolder holder, int position) {
        GioHangDTO item_gh = ds_gio_hang.get(position);

        SanPhamDTO sp = sp_dao.LayThongTinSanPhamTheoId(item_gh.getSan_pham_id());
        KichThuocDTO kt = kho_dao.LayThongTinKichThuocTheoId(item_gh.getKich_thuoc_id());
        int so_luong_ton = kho_dao.LaySoLuongSanPham(item_gh.getSan_pham_id(),item_gh.getKich_thuoc_id());
        DecimalFormat format = new DecimalFormat("###,###,###");
        list_anh = sp_dao.LayDanhSachAnhSanPham(item_gh.getSan_pham_id());
        if(list_anh.size()>0){
            Glide.with(holder.context).load(list_anh.get(0).getAnh_san_pham_url().toString()).placeholder(R.drawable.ic_launcher_background).into(holder.cart_item_img);
        }else{
            Glide.with(holder.context).load("https://thumbs.dreamstime.com/b/icono-de-color-rgb-marcador-posici%C3%B3n-galer%C3%ADa-im%C3%A1genes-miniatura-la-foto-%C3%A1lbum-disponible-medios-digitales-archivo-multimedia-187369540.jpg").placeholder(R.drawable.ic_launcher_background).into(holder.cart_item_img);
        }
        String ten_sp = sp.get_ten_san_pham();
        if(ten_sp.length()>50){
            holder.cart_item_name.setText(ten_sp.substring(0,50)+"...");
        }else {
            holder.cart_item_name.setText(ten_sp);
        }
        holder.cart_item_size.setText("Size: "+kt.getTen_kich_thuoc());
        int gia_giam = (int)(sp.get_gia_ban().doubleValue()*(1-sp.get_phan_tram_khuyen_mai()));
        holder.cart_item_price.setText(format.format(gia_giam).toString()+"đ");
        holder.cart_item_qty.setText(String.valueOf(item_gh.getSo_luong()));
        holder.btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.btn_plus.setEnabled(true);
                if(item_gh.getSo_luong() == 1){
                    xoaItemKhoiGH(item_gh,holder.context,holder);
                }else{
                    giamSoLuongItemGH(item_gh,holder.context);
                    holder.cart_item_qty.setText(String.valueOf(item_gh.getSo_luong()));
                }
            }
        });
        holder.btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(item_gh.getSo_luong() == so_luong_ton){
                    holder.btn_plus.setEnabled(false);
                }else{
                    tangSoLuongItemGH(item_gh,holder.context);
                    holder.cart_item_qty.setText(String.valueOf(item_gh.getSo_luong()));
                }
            }
        });
        }

    private void tangSoLuongItemGH(GioHangDTO item_gh, Context context) {
        boolean da_tang_sl = false;
        item_gh.setSo_luong(item_gh.getSo_luong()+1);
        da_tang_sl = gh_dao.CapNhatSLTrongGioHang(item_gh.getKhach_hang_id(),item_gh.getSan_pham_id(),item_gh.getKich_thuoc_id(),item_gh.getSo_luong());
        if (da_tang_sl)
            Toast.makeText(context, "Da cap nhat so luong", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Them loi", Toast.LENGTH_SHORT).show();
        setTongTien();
    }

    private void xoaItemKhoiGH(GioHangDTO item_gh, Context context, ViewHolder holder) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle("Thông báo");
        builder.setMessage("Xóa sản phẩm khỏi giỏ hàng");
        builder.setPositiveButton("Xóa",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean da_xoa = false;
                        da_xoa = gh_dao.XoaSanPhamTrongGioHang(item_gh.getKhach_hang_id(),item_gh.getSan_pham_id(),item_gh.getKich_thuoc_id());
                        if (da_xoa)
                        {
                            sl = gh_dao.LaySoLuongSanPhamTrongGioHang(item_gh.getKhach_hang_id());
                            Toast.makeText(context, "Da xoa, sl:"+sl, Toast.LENGTH_SHORT).show();
                            setTongTien();
                        }
                        else
                            Toast.makeText(context, "xoa that bai", Toast.LENGTH_SHORT).show();
                        ds_gio_hang.remove(holder.getAdapterPosition());
                        notifyDataSetChanged();
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void giamSoLuongItemGH(GioHangDTO item_gh ,Context context){
        boolean da_giam_sl = false;
        item_gh.setSo_luong(item_gh.getSo_luong()-1);
        da_giam_sl = gh_dao.CapNhatSLTrongGioHang(item_gh.getKhach_hang_id(),item_gh.getSan_pham_id(),item_gh.getKich_thuoc_id(),item_gh.getSo_luong());
        if (da_giam_sl)
            Toast.makeText(context, "Da giam", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "Giam loi", Toast.LENGTH_SHORT).show();
        setTongTien();
    }

    private void setTongTien() {
        int tong = 0;
        for (GioHangDTO i: ds_gio_hang) {
            SanPhamDTO sp = sp_dao.LayThongTinSanPhamTheoId(i.getSan_pham_id());
            tong+= (i.getSo_luong()*(int)(sp.get_gia_ban().doubleValue()*(1-sp.get_phan_tram_khuyen_mai())));
        }
        CartActivity.txt_tong_tien.setText(format.format(tong).toString()+"đ");
    }

    @Override
    public int getItemCount() {
        return ds_gio_hang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ShapeableImageView cart_item_img;
        TextView cart_item_name,cart_item_size,cart_item_price,cart_item_qty;
        AppCompatButton btn_sub, btn_plus;
        Context context;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cart_item_img = itemView.findViewById(R.id.cart_item_img);
            cart_item_name = itemView.findViewById(R.id.cart_item_name);
            cart_item_size = itemView.findViewById(R.id.cart_item_size);
            cart_item_price = itemView.findViewById(R.id.cart_item_price);
            cart_item_qty = itemView.findViewById(R.id.cart_item_qty);
            btn_sub = itemView.findViewById(R.id.btn_sub);
            btn_plus = itemView.findViewById(R.id.btn_plus);

            context = itemView.getContext();
        }
    }
}
