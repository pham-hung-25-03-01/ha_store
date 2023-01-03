package com.ha_store.dao;

import android.database.Cursor;

import com.ha_store.dto.ChiTietThanhToanDTO;
import com.ha_store.dto.DonDatHangDTO;
import com.ha_store.dto.HinhThucThanhToanDTO;
import com.ha_store.dto.HoaDonDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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
    public List<DonDatHangDTO> LayDanhSachDonDatHang(Integer khach_hang_id, Integer trang_thai){
        try{
            List<DonDatHangDTO> ds_ddh = new ArrayList<DonDatHangDTO>();
            String query = "SELECT * FROM tb_don_dat_hang WHERE khach_hang_id = ?";
            Cursor c;
            if(trang_thai == null){
                c = db.rawQuery(query, new String[]{String.valueOf(khach_hang_id)});
            }else {
                query += " AND trang_thai = ?";
                c = db.rawQuery(query, new String[]{String.valueOf(khach_hang_id), String.valueOf(trang_thai)});
            }
            while (c.moveToNext()){
                ds_ddh.add(new DonDatHangDTO(
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
                ));
            }
            return ds_ddh;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public HinhThucThanhToanDTO LayHinhThucThanhToan(Integer don_dat_hang_id){
        try{
            HinhThucThanhToanDTO hinhThucThanhToanDTO = null;
            String query = "SELECT * FROM tb_hinh_thuc_thanh_toan WHERE id IN (SELECT hinh_thuc_thanh_toan_id FROM tb_chi_tiet_thanh_toan WHERE don_dat_hang_id = ? LIMIT 1)";
            Cursor c = db.rawQuery(query, new String[]{String.valueOf(don_dat_hang_id)});;
            if (c.moveToNext()){
                hinhThucThanhToanDTO = new HinhThucThanhToanDTO(
                        c.getInt(0),
                        c.getString(1)
                );
            }
            return hinhThucThanhToanDTO;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public ChiTietThanhToanDTO LayChiTietThanhToan(Integer don_dat_hang_id){
        try{
            ChiTietThanhToanDTO chiTietThanhToanDTO = null;
            String query = "SELECT * FROM tb_chi_tiet_thanh_toan WHERE don_dat_hang_id = ? LIMIT 1";
            Cursor c = db.rawQuery(query, new String[]{String.valueOf(don_dat_hang_id)});;
            if (c.moveToNext()){
                chiTietThanhToanDTO = new ChiTietThanhToanDTO(
                        c.getInt(0),
                        c.getInt(1),
                        c.getInt(2),
                        BigDecimal.valueOf(c.getLong(3))
                );
            }
            return chiTietThanhToanDTO;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public HoaDonDTO LayHoaDon(Integer id){
        try{
            HoaDonDTO hoaDonDTO = null;
            String query = "SELECT * FROM tb_hoa_don WHERE id = ? LIMIT 1";
            Cursor c = db.rawQuery(query, new String[]{String.valueOf(id)});;
            if (c.moveToNext()){
                hoaDonDTO = new HoaDonDTO(
                        c.getInt(0),
                        c.getInt(1),
                        c.getInt(2),
                        BigDecimal.valueOf(c.getLong(3))
                );
            }
            return hoaDonDTO;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
