package com.ha_store.dao;

import android.database.Cursor;

import com.ha_store.dto.KichThuocDTO;

import java.util.ArrayList;
import java.util.List;

public class KhoDAO extends BaseDAO{
    public KhoDAO(){ super(); }
    public List<KichThuocDTO> LayDanhSachKichThuocTheoSanPhamId(Integer san_pham_id){
        try{
            List<KichThuocDTO> ds_kt = new ArrayList<KichThuocDTO>();
            String query = "SELECT DISTINCT kt.* FROM tb_kho AS k JOIN tb_kich_thuoc AS kt ON k.kich_thuoc_id = kt.id WHERE k.san_pham_id = ?";
            Cursor c = db.rawQuery(query, new String[]{String.valueOf(san_pham_id)});
            while (c.moveToNext()){
                ds_kt.add(new KichThuocDTO(
                        c.getInt(0),
                        c.getString(1)
                ));
            }
            return ds_kt;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}