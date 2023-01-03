package com.ha_store.dao;

import android.database.Cursor;

import com.ha_store.dto.ChiTietDonDatHangDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ChiTietDonDatHangDAO extends BaseDAO{
    public ChiTietDonDatHangDAO(){ super(); }
    public Boolean ThemChiTietDonDatHang(ChiTietDonDatHangDTO ctddh){
        try{
            String query = "INSERT INTO tb_chi_tiet_don_dat_hang(san_pham_id, kich_thuoc_id, don_dat_hang_id, gia_ban, phan_tram_khuyen_mai, so_luong) "+
                    "VALUES(?,?,?,?,?,?)";
            db.execSQL(query, new Object[]{
                    ctddh.getSan_pham_id(),
                    ctddh.getKich_thuoc_id(),
                    ctddh.getDon_dat_hang_id(),
                    ctddh.getGia_ban(),
                    ctddh.getPhan_tram_khuyen_mai(),
                    ctddh.getSo_luong()
            });
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public List<ChiTietDonDatHangDTO> LayDanhSachChiTietDonDatHang(Integer don_dat_hang_id){
        try{
            List<ChiTietDonDatHangDTO> ds_ctddh = new ArrayList<ChiTietDonDatHangDTO>();
            String query = "SELECT * FROM tb_chi_tiet_don_dat_hang WHERE don_dat_hang_id = ?";
            Cursor c = db.rawQuery(query, new String[]{String.valueOf(don_dat_hang_id)});
            while (c.moveToNext()){
                ds_ctddh.add(new ChiTietDonDatHangDTO(
                        c.getInt(0),
                        c.getInt(1),
                        c.getInt(2),
                        BigDecimal.valueOf(c.getLong(3)),
                        c.getFloat(4),
                        c.getInt(5)
                ));
            }
            return ds_ctddh;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
