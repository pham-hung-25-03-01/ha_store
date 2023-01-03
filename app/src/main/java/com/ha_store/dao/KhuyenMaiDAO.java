package com.ha_store.dao;

import android.database.Cursor;

import com.ha_store.dto.KhuyenMaiDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class KhuyenMaiDAO extends BaseDAO{
    public KhuyenMaiDAO(){ super(); }
    public List<KhuyenMaiDTO> LayDanhSachKhuyenMai(){
        try{
            List<KhuyenMaiDTO> ds_km = new ArrayList<KhuyenMaiDTO>();
            String ngay_hien_tai = convert_date_to_big_decimal(new Date()).toString();
            String query = "SELECT * FROM tb_khuyen_mai WHERE ngay_bat_dau <= ? AND ngay_ket_thuc >= ? AND so_luong > 0";
            Cursor c = db.rawQuery(query, new String[]{ngay_hien_tai, ngay_hien_tai});
            while (c.moveToNext()){
                ds_km.add(new KhuyenMaiDTO(
                        c.getString(0),
                        c.getDouble(1),
                        BigDecimal.valueOf(c.getLong(2)),
                        BigDecimal.valueOf(c.getLong(3)),
                        c.getInt(4)
                ));
            }
            return ds_km;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public Integer LaySoLuongKhuyenMai(){
        try{
            Integer so_luong = 0;
            String ngay_hien_tai = convert_date_to_big_decimal(new Date()).toString();
            String query = "SELECT COUNT(*) FROM tb_khuyen_mai WHERE ngay_bat_dau <= ? AND ngay_ket_thuc >= ? AND so_luong > 0";
            Cursor c = db.rawQuery(query, new String[]{ngay_hien_tai, ngay_hien_tai});
            if (c.moveToNext()){
                so_luong = c.getInt(0);
            }
            return so_luong;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
