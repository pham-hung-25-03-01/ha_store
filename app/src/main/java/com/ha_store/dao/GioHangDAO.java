package com.ha_store.dao;

import android.database.Cursor;

import com.ha_store.dto.GioHangDTO;

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
            db.execSQL(query);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
