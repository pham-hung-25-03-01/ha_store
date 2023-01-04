package com.ha_store.dao;

import android.database.Cursor;

import com.ha_store.dto.BinhLuanDTO;
import com.ha_store.dto.XepHangDTO;

import java.util.ArrayList;
import java.util.List;

public class XepHangDAO extends BaseDAO{
    public XepHangDAO(){ super(); }
    public List<XepHangDTO> LayDanhSachXepHangTheoSanPhamId(Integer san_pham_id){
        try{
            List<XepHangDTO> ds_xh = new ArrayList<XepHangDTO>();
            String query = "SELECT * FROM tb_xep_hang WHERE san_pham_id = ?";
            Cursor c = db.rawQuery(query, new String[]{String.valueOf(san_pham_id)});
            while (c.moveToNext()) {
                ds_xh.add(new XepHangDTO(
                        c.getInt(0),
                        c.getInt(1),
                        c.getInt(2)
                ));
            }
            return ds_xh;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public Boolean ThemXepHang(XepHangDTO xh){
        try{
            String query = "INSERT INTO tb_xep_hang(khach_hang_id, san_pham_id, diem_xep_hang) VALUES(?, ?, ?)";
            db.execSQL(query, new Object[]{
                    xh.getKhach_hang_id(),
                    xh.getSan_pham_id(),
                    xh.getDiem_xep_hang()
            });
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public Boolean CapNhatXepHang(XepHangDTO xh){
        try{
            String query = "UPDATE tb_xep_hang SET diem_xep_hang = ? WHERE san_pham_id = ? AND khach_hang_id = ?";
            db.execSQL(query, new Object[]{
                    xh.getDiem_xep_hang(),
                    xh.getSan_pham_id(),
                    xh.getKhach_hang_id()
            });
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public XepHangDTO LayXepHangTheoKhachHangId(Integer kh_id, Integer sp_id){
        try{
            XepHangDTO xh = null;
            String query = "SELECT * FROM tb_xep_hang WHERE khach_hang_id = ? AND san_pham_id = ? LIMIT 1";
            Cursor c = db.rawQuery(query, new String[]{String.valueOf(kh_id),String.valueOf(sp_id)});
            if(c.moveToNext()) {
                xh = new XepHangDTO(
                        c.getInt(0),
                        c.getInt(1),
                        c.getInt(2)
                );
            }
            return xh;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
