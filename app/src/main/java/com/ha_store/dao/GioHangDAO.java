package com.ha_store.dao;

import android.database.Cursor;

import com.ha_store.dto.GioHangDTO;
import com.ha_store.dto.SanPhamDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class GioHangDAO extends BaseDAO{
    public GioHangDAO(){ super(); }
    public int LaySoLuongSanPhamTrongGioHang(Integer khach_hang_id){
        try{
            String query = "SELECT COUNT(*) FROM tb_gio_hang WHERE khach_hang_id = ?";
            Cursor c = db.rawQuery(query, new String[]{String.valueOf(khach_hang_id)});
            Integer count = 0;
            if (c.moveToNext()) {
                count = c.getInt(0);
            }
            return count;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    public List<GioHangDTO> LayGioHangTheoKhachHangId(Integer khach_hang_id){
        try{
            List<GioHangDTO> ds_gh = new ArrayList<GioHangDTO>();
            String query = "SELECT * FROM tb_gio_hang WHERE khach_hang_id = ?";
            Cursor c = db.rawQuery(query, new String[]{String.valueOf(khach_hang_id)});
            while (c.moveToNext()) {
                ds_gh.add(new GioHangDTO(
                        c.getInt(0),
                        c.getInt(1),
                        c.getInt(2),
                        c.getInt(3)
                ));
            }
            return ds_gh;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public Boolean XoaSanPhamTrongGioHang(Integer khach_hang_id, Integer san_pham_id, Integer kich_thuoc_id){
        try {
            String query = "DELETE FROM tb_gio_hang WHERE khach_hang_id = ? AND san_pham_id = ? AND kich_thuoc_id = ?";
            db.execSQL(query,new Object[]{khach_hang_id,san_pham_id,kich_thuoc_id});
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public Boolean ThemSanPhamVaoGioHang(GioHangDTO gh){
        try{
            String query = "INSERT INTO tb_gio_hang(khach_hang_id, san_pham_id, kich_thuoc_id, so_luong) "+
                    "VALUES(?,?,?,?)";
            db.execSQL(query, new Object[]{
                    gh.getKhach_hang_id(),
                    gh.getSan_pham_id(),
                    gh.getKich_thuoc_id(),
                    gh.getSo_luong()
            });
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public Boolean CapNhatSLTrongGioHang(Integer khach_hang_id, Integer san_pham_id, Integer kich_thuoc_id, Integer so_luong){
        try {
            String query = "UPDATE tb_gio_hang SET so_luong = ? WHERE khach_hang_id = ? AND san_pham_id = ? AND kich_thuoc_id = ?";
            db.execSQL(query,new Object[]{so_luong,khach_hang_id,san_pham_id,kich_thuoc_id});
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public Boolean KiemTraTonTaiTrongGioHang(Integer khach_hang_id, Integer san_pham_id, Integer kich_thuoc_id){
        try {
            String query = "SELECT * FROM tb_gio_hang WHERE khach_hang_id = ? AND san_pham_id = ? AND kich_thuoc_id = ? LIMIT 1";
            Cursor c = db.rawQuery(query,new String[]{String.valueOf(khach_hang_id), String.valueOf(san_pham_id), String.valueOf(kich_thuoc_id)});
            if(c.moveToNext())
                return true;
            else
                return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public int LaySoLuongMoiItemTrongGioHang(Integer khach_hang_id, Integer san_pham_id, Integer kich_thuoc_id){
        try{
            String query = "SELECT * FROM tb_gio_hang WHERE khach_hang_id = ? AND san_pham_id = ? AND kich_thuoc_id = ? LIMIT 1";
            Cursor c = db.rawQuery(query,new String[]{String.valueOf(khach_hang_id), String.valueOf(san_pham_id), String.valueOf(kich_thuoc_id)});
            Integer so_luong = 0;
            if(c.moveToNext()){
                so_luong = c.getInt(3);
            }
            return so_luong;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
