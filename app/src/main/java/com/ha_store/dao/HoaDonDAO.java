package com.ha_store.dao;

import android.database.Cursor;

import com.ha_store.dto.HoaDonDTO;

import java.math.BigDecimal;

public class HoaDonDAO extends BaseDAO{
    public HoaDonDAO() {
        super();
    }
    public boolean ThemHoaDon(HoaDonDTO hoaDonDTO){
        try{
            String query = "INSERT INTO tb_hoa_don(quan_tri_vien_id, trang_thai, ngay_khoi_tao) "+
                    "VALUES(?,?,?)";
            db.execSQL(query, new Object[]{
                    hoaDonDTO.getQuan_tri_vien_id(),
                    hoaDonDTO.getTrang_thai(),
                    hoaDonDTO.getNgay_khoi_tao()
            });
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public HoaDonDTO LayHoaDonMoi(){
        try{
            HoaDonDTO hoaDon = null;
            String query = "SELECT * FROM tb_hoa_don ORDER BY id DESC LIMIT 1";
            Cursor c = db.rawQuery(query,null);
            if (c.moveToNext()){
                hoaDon = new HoaDonDTO(
                        c.getInt(0),
                        c.getInt(1),
                        c.getInt(2),
                        BigDecimal.valueOf(c.getLong(3))
                        );
            }
            return hoaDon;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
