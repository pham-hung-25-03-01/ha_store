package com.ha_store.dao;

import android.database.Cursor;

import com.ha_store.dto.BinhLuanDTO;
import com.ha_store.dto.XepHangDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BinhLuanDAO extends BaseDAO{
    public BinhLuanDAO(){ super(); }
    public List<BinhLuanDTO> LayDanhSachBinhLuanTheoSanPhamId(Integer san_pham_id){
        try{
            List<BinhLuanDTO> ds_bl = new ArrayList<BinhLuanDTO>();
            String query = "SELECT * FROM tb_binh_luan WHERE san_pham_id = ?";
            Cursor c = db.rawQuery(query, new String[]{String.valueOf(san_pham_id)});
            while (c.moveToNext()) {
                ds_bl.add(new BinhLuanDTO(
                        c.getInt(0),
                        c.getInt(1),
                        c.getInt(2),
                        c.getString(3),
                        BigDecimal.valueOf(c.getLong(4))
                ));
            }
            return ds_bl;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public Boolean ThemBinhLuan(BinhLuanDTO bl){
        try{
            String query = "INSERT INTO tb_binh_luan(khach_hang_id, san_pham_id, noi_dung, ngay_binh_luan) VALUES(?, ?, ?, ?)";
            db.execSQL(query, new Object[]{
                    bl.getKhach_hang_id(),
                    bl.getSan_pham_id(),
                    bl.getNoi_dung(),
                    bl.getNgay_binh_luan()
            });
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public Boolean XoaBinhLuan(Integer binh_luan_id){
        try{
            String query = "DELETE FROM tb_binh_luan WHERE id = ?";
            db.execSQL(query, new Object[]{binh_luan_id});
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
