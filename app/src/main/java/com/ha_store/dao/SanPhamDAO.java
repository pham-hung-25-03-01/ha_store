package com.ha_store.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ha_store.dto.SanPhamDTO;
import com.ha_store.sql_db_helper.DBHelper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO extends BaseDAO{
    public  SanPhamDAO(){
        super();
    }
    public List<SanPhamDTO> LayDanhSachSanPhamMoi(Integer limit){
        try {
            List<SanPhamDTO> ds_san_pham = new ArrayList<>();
            String query = "SELECT * FROM tb_san_pham ORDER BY id DESC";
            Cursor c;
            if(limit != null){
                query += " LIMIT ?";
                c = db.rawQuery(query, new String[]{String.valueOf(limit)});
            }
            else{
                c = db.rawQuery(query, null);
            }
            while(c.moveToNext()){
                ds_san_pham.add(new SanPhamDTO(
                        c.getInt(0),
                        c.getInt(1),
                        c.getInt(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getInt(7),
                        BigDecimal.valueOf(c.getLong(8)),
                        BigDecimal.valueOf(c.getLong(9)),
                        c.getFloat(10),
                        c.getFloat(11),
                        c.getInt(12)
                ));
            }
            return ds_san_pham;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<SanPhamDTO> LayDanhSachSanPhamBanChay(Integer limit){
        try {
            List<SanPhamDTO> ds_san_pham = new ArrayList<>();
            String query = "SELECT * FROM tb_san_pham WHERE id IN " +
                    "(SELECT DISTINCT san_pham_id FROM tb_chi_tiet_don_dat_hang WHERE don_dat_hang_id IN " +
                    "(SELECT tb_chi_tiet_thanh_toan.don_dat_hang_id FROM tb_hoa_don INNER JOIN tb_chi_tiet_thanh_toan ON tb_hoa_don.id = tb_chi_tiet_thanh_toan.hoa_don_id WHERE tb_hoa_don.trang_thai = 2) " +
                    "GROUP BY san_pham_id ORDER BY SUM(so_luong) DESC)";
            Cursor c;
            if(limit != null){
                query += " LIMIT ?";
                c = db.rawQuery(query, new String[]{String.valueOf(limit)});
            }
            else{
                c = db.rawQuery(query, null);
            }
            while(c.moveToNext()){
                ds_san_pham.add(new SanPhamDTO(
                        c.getInt(0),
                        c.getInt(1),
                        c.getInt(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getInt(7),
                        BigDecimal.valueOf(c.getLong(8)),
                        BigDecimal.valueOf(c.getLong(9)),
                        c.getFloat(10),
                        c.getFloat(11),
                        c.getInt(12)
                ));
            }
            return ds_san_pham;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<SanPhamDTO> LayDanhSachSanPhamGiamGia(Integer limit){
        try {
            List<SanPhamDTO> ds_san_pham = new ArrayList<>();
            String query = "SELECT * FROM tb_san_pham WHERE phan_tram_khuyen_mai IS NOT NULL AND phan_tram_khuyen_mai > 0 ORDER BY phan_tram_khuyen_mai DESC";
            Cursor c;
            if(limit != null){
                query += " LIMIT ?";
                c = db.rawQuery(query, new String[]{String.valueOf(limit)});
            }
            else{
                c = db.rawQuery(query, null);
            }
            while(c.moveToNext()){
                ds_san_pham.add(new SanPhamDTO(
                        c.getInt(0),
                        c.getInt(1),
                        c.getInt(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getInt(7),
                        BigDecimal.valueOf(c.getLong(8)),
                        BigDecimal.valueOf(c.getLong(9)),
                        c.getFloat(10),
                        c.getFloat(11),
                        c.getInt(12)
                ));
            }
            return ds_san_pham;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public SanPhamDTO LayThongTinSanPhamTheoId(Integer id){
        try {
            SanPhamDTO san_pham = null;
            String query = "SELECT * FROM tb_san_pham WHERE id = ? LIMIT 1";
            Cursor c = db.rawQuery(query, new String[]{String.valueOf(id)});
            if(c.moveToNext()){
                san_pham = new SanPhamDTO(
                        c.getInt(0),
                        c.getInt(1),
                        c.getInt(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getInt(7),
                        BigDecimal.valueOf(c.getLong(8)),
                        BigDecimal.valueOf(c.getLong(9)),
                        c.getFloat(10),
                        c.getFloat(11),
                        c.getInt(12)
                );
            }
            return san_pham;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<SanPhamDTO> TimKiemSanPhamTheoTen(String ten_san_pham){
        try {
            List<SanPhamDTO> ds_san_pham = new ArrayList<>();
            String query = "SELECT * FROM tb_san_pham WHERE LOWER(ten_san_pham) LIKE LOWER(?) ORDER BY id DESC";
            Cursor c = db.rawQuery(query, new String[]{ten_san_pham});
            while(c.moveToNext()){
                ds_san_pham.add(new SanPhamDTO(
                        c.getInt(0),
                        c.getInt(1),
                        c.getInt(2),
                        c.getString(3),
                        c.getString(4),
                        c.getString(5),
                        c.getString(6),
                        c.getInt(7),
                        BigDecimal.valueOf(c.getLong(8)),
                        BigDecimal.valueOf(c.getLong(9)),
                        c.getFloat(10),
                        c.getFloat(11),
                        c.getInt(12)
                ));
            }
            return ds_san_pham;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
