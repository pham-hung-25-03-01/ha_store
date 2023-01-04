package com.ha_store.dao;

import android.database.Cursor;

import com.ha_store.dto.BinhLuanDTO;
import com.ha_store.dto.SanPhamDTO;
import com.ha_store.dto.XepHangDTO;
import com.ha_store.dto.YeuThichDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
    public Integer LaySoLuongYeuThichTheoKhachHangId(Integer khach_hang_id){
        try{
            Integer so_luong = 0;
            String query = "SELECT COUNT(*) FROM tb_yeu_thich WHERE khach_hang_id = ?";
            Cursor c = db.rawQuery(query, new String[]{String.valueOf(khach_hang_id)});
            if (c.moveToNext()){
                so_luong = c.getInt(0);
            }
            return so_luong;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
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
    public List<SanPhamDTO> LayDanhSachSanPhamYeuThichTheoKhachHangId(Integer khach_hang_id){
        try{
            List<SanPhamDTO> ds_sp = new ArrayList<SanPhamDTO>();
            String query = "SELECT * FROM tb_san_pham WHERE id IN (SELECT san_pham_id FROM tb_yeu_thich WHERE khach_hang_id = ?)";
            Cursor c = db.rawQuery(query, new String[]{String.valueOf(khach_hang_id)});
            while (c.moveToNext()) {
                ds_sp.add(new SanPhamDTO(
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
            return ds_sp;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public YeuThichDTO LayYeuThich(Integer khach_hang_id, Integer san_pham_id){
        try{
            YeuThichDTO yt = null;
            String query = "SELECT * FROM tb_yeu_thich WHERE khach_hang_id = ? AND san_pham_id = ? LIMIT 1";
            Cursor c = db.rawQuery(query, new String[]{String.valueOf(khach_hang_id), String.valueOf(san_pham_id)});
            if (c.moveToNext()) {
                yt = new YeuThichDTO(c.getInt(0), c.getInt(1), c.getInt(2));
            }
            return yt;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
