package com.ha_store.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ha_store.R;
import com.ha_store.dao.KhachHangDAO;
import com.ha_store.dto.BinhLuanDTO;
import com.ha_store.dto.KhachHangDTO;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.ViewHolder> {
    List<BinhLuanDTO> binh_luan;
    KhachHangDAO kh_dao;
    public RatingAdapter(List<BinhLuanDTO> binh_luan) {
        this.binh_luan = binh_luan;
        kh_dao = new KhachHangDAO();
    }

    @NonNull
    @Override
    public RatingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View categoryView = inflater.inflate(R.layout.item_review,parent,false);
        ViewHolder viewHolder = new ViewHolder(categoryView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RatingAdapter.ViewHolder holder, int position) {
        BinhLuanDTO bl = binh_luan.get(position);
        KhachHangDTO kh = kh_dao.LayThongTinKhachHangTheoId(bl.getKhach_hang_id());
        holder.txt_name.setText(kh.get_ho()+" " +kh.get_ten());
        String ngay = "";
        try {
           ngay  = convert_big_decimal_to_date(bl.getNgay_binh_luan());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.txt_ngay.setText(ngay);
        holder.txt_binh_luan.setText(bl.getNoi_dung());
    }
    private String convert_big_decimal_to_date(BigDecimal big_decimal) throws ParseException {
        DateFormat d_format = new SimpleDateFormat("ddMMyyyy");
        Date date = d_format.parse(big_decimal.toString());
        SimpleDateFormat s_format = new SimpleDateFormat("dd/MM/yyyy");
        return s_format.format(date);
    }
    @Override
    public int getItemCount() {
        return binh_luan.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_name, txt_ngay,txt_binh_luan;
        Context context;
        RatingBar rb_rating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rb_rating = itemView.findViewById(R.id.rb_rating);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_ngay = itemView.findViewById(R.id.txt_ngay);
            txt_binh_luan = itemView.findViewById(R.id.txt_binh_luan);
            context = itemView.getContext();
        }
    }
}
