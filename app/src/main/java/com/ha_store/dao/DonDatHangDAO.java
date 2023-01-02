package com.ha_store.dao;

import android.database.Cursor;

import com.ha_store.dto.DonDatHangDTO;

import java.math.BigDecimal;

public class DonDatHangDAO extends BaseDAO{
    public DonDatHangDAO (){ super(); }
    public Boolean ThemDonDatHang(DonDatHangDTO ddh){
        try{
            String query = "INSERT INTO tb_don_dat_hang(khach_hang_id, khuyen_mai_id, ngay_giao, so_nha, phuong_xa, quan_huyen, tinh_thanh, tong_tien, trang_thai, ngay_khoi_tao) "+
                    "VALUES(?,?,?,?,?,?,?,?,?,?)";
            db.execSQL(query, new Object[]{
                    ddh.getKhach_hang_id(),
                    ddh.getKhuyen_mai_id(),
                    ddh.getNgay_giao(),
                    ddh.getSo_nha(),
                    ddh.getPhuong_xa(),
                    ddh.getQuan_huyen(),
                    ddh.getTinh_thanh(),
                    ddh.getTong_tien(),
                    ddh.getTrang_thai(),
                    ddh.getNgay_khoi_tao()
            });
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public Boolean HuyDonDatHang(Integer don_dat_hang_id){
        try{
            String query = "UPDATE tb_don_dat_hang SET trang_thai = 0 WHERE id = ?";
            db.execSQL(query, new Object[]{don_dat_hang_id});
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public DonDatHangDTO LayThongTinDonDatHang(Integer don_dat_hang_id){
        try{
            DonDatHangDTO ddh = null;
            String query = "SELECT * FROM tb_don_dat_hang WHERE id = ? LIMIT 1";
            Cursor c = db.rawQuery(query, new String[]{String.valueOf(don_dat_hang_id)});
            if (c.moveToNext()){
                ddh  = new DonDatHangDTO(
                        c.getInt(0),
                        c.getInt(1),
                        c.getString(2),
                        BigDecimal.valueOf(c.getLong(3)),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getString(7),
                        BigDecimal.valueOf(c.getLong(8)),
                        c.getInt(9),
                        BigDecimal.valueOf(c.getLong(10))
                );
            }
            return ddh;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
