package com.ha_store.dao;

import android.database.Cursor;

import com.ha_store.dto.BinhLuanDTO;
import com.ha_store.dto.XepHangDTO;
import com.ha_store.dto.YeuThichDTO;

import java.util.ArrayList;
import java.util.List;

public class YeuThichDAO extends BaseDAO{
    public YeuThichDAO(){ super(); }
    public List<YeuThichDTO> LayDanhSachYeuThichTheoKhachHangId(Integer khach_hang_id){
        try{
            List<YeuThichDTO> ds_yt = new ArrayList<YeuThichDTO>();
            String query = "SELECT * FROM tb_yeu_thich WHERE khach_hang_id = ?";
            Cursor c = db.rawQuery(query, new String[]{String.valueOf(khach_hang_id)});
            while (c.moveToNext()) {
                ds_yt.add(new YeuThichDTO(
                        c.getInt(0),
                        c.getInt(1),
                        c.getInt(2)
                ));
            }
            return ds_yt;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public Boolean ThemYeuThich(YeuThichDTO yt){
        try{
            String query = "INSERT INTO tb_yeu_thich(khach_hang_id, san_pham_id, da_yeu_thich) VALUES(?, ?, ?)";
            db.execSQL(query, new Object[]{
                    yt.getKhach_hang_id(),
                    yt.getSan_pham_id(),
                    yt.getDa_yeu_thich()
            });
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public Boolean XoaYeuThich(Integer khach_hang_id, Integer san_pham_id){
        try{
            String query = "DELETE FROM tb_yeu_thich WHERE khach_hang_id = ? AND san_pham_id = ?";
            db.execSQL(query, new Object[]{khach_hang_id, san_pham_id});
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
