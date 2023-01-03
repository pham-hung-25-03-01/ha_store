package com.ha_store.dao;

import android.database.Cursor;

import com.ha_store.dto.KhachHangDTO;
import com.ha_store.sql_db_helper.Session;

import org.mindrot.jbcrypt.BCrypt;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO extends BaseDAO{
    public static Session khach_hang_hien_tai = new Session();
    public KhachHangDAO(){
        super();
    }
    public boolean dang_nhap(String email, String mat_khau){
        try {
            khach_hang_hien_tai.set_da_dang_nhap(false);
            String query = "SELECT * FROM tb_khach_hang WHERE email = ? LIMIT 1;";
            Cursor c = db.rawQuery(query, new String[]{email});
            if(c.moveToNext()) {
                if(is_valid(mat_khau, c.getString(8))){
                    khach_hang_hien_tai.set_id(c.getInt(0));
                    khach_hang_hien_tai.set_email(c.getString(7));
                    khach_hang_hien_tai.set_da_dang_nhap(true);
                    return true;
                }
                return false;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean dang_xuat(){
        try {
            khach_hang_hien_tai.set_da_dang_nhap(false);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public boolean dang_ky(KhachHangDTO khach_hang){
        try {
            String query = "INSERT INTO tb_khach_hang" +
                    "(ho, ten, anh_dai_dien_url, gioi_tinh, ngay_sinh, so_dien_thoai, email, " +
                    "mat_khau, so_nha, phuong_xa, quan_huyen, tinh_thanh, so_du_vi, ngay_khoi_tao) VALUES" +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            db.execSQL(query, new Object[]{
                    khach_hang.get_ho(),
                    khach_hang.get_ten(),
                    khach_hang.get_anh_dai_dien_url(),
                    khach_hang.get_gioi_tinh(),
                    khach_hang.get_ngay_sinh(),
                    khach_hang.get_so_dien_thoai(),
                    khach_hang.get_email(),
                    khach_hang.get_mat_khau(),
                    khach_hang.get_so_nha(),
                    khach_hang.get_phuong_xa(),
                    khach_hang.get_quan_huyen(),
                    khach_hang.get_tinh_thanh(),
                    khach_hang.get_so_du_vi(),
                    khach_hang.get_ngay_khoi_tao(),
            });
            query = "SELECT * FROM tb_khach_hang ORDER BY id DESC LIMIT 1";
            Cursor c = db.rawQuery(query, null);
            if(c.moveToNext()){
                khach_hang_hien_tai.set_id(c.getInt(0));
                khach_hang_hien_tai.set_email(c.getString(7));
                khach_hang_hien_tai.set_da_dang_nhap(true);
            }
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public KhachHangDTO LayThongTinKhachHangTheoEmail(String email){
        try{
            KhachHangDTO kh = null;
            String query = "SELECT * FROM tb_khach_hang WHERE email = ? LIMIT 1";
            Cursor c = db.rawQuery(query, new String[]{email});
            if(c.moveToNext()){
                kh = new KhachHangDTO(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getInt(4),
                        BigDecimal.valueOf(c.getLong(5)),
                        c.getString(6),
                        c.getString(7),
                        c.getString(8),
                        c.getString(9),
                        c.getString(10),
                        c.getString(11),
                        c.getString(12),
                        BigDecimal.valueOf(c.getLong(13)),
                        BigDecimal.valueOf(c.getLong(14))
                );
            }
            return kh;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public List<KhachHangDTO> LayDanhSachKhachHang(){
        try {
            List<KhachHangDTO> ds_kh = new ArrayList<KhachHangDTO>();
            String query = "SELECT * FROM tb_khach_hang";
            Cursor c = db.rawQuery(query, null);
            while (c.moveToNext()) {
                ds_kh.add(new KhachHangDTO(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getInt(4),
                        BigDecimal.valueOf(c.getLong(5)),
                        c.getString(6),
                        c.getString(7),
                        c.getString(8),
                        c.getString(9),
                        c.getString(10),
                        c.getString(11),
                        c.getString(12),
                        BigDecimal.valueOf(c.getLong(13)),
                        BigDecimal.valueOf(c.getLong(14))
                ));
            }
            return ds_kh;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public KhachHangDTO LayThongTinKhachHangTheoId(Integer khach_hang_id){
        try{
            KhachHangDTO kh = null;
            String query = "SELECT * FROM tb_khach_hang WHERE id = ? LIMIT 1";
            Cursor c = db.rawQuery(query, new String[]{String.valueOf(khach_hang_id)});
            if(c.moveToNext()){
                kh = new KhachHangDTO(
                        c.getInt(0),
                        c.getString(1),
                        c.getString(2),
                        c.getString(3),
                        c.getInt(4),
                        BigDecimal.valueOf(c.getLong(5)),
                        c.getString(6),
                        c.getString(7),
                        c.getString(8),
                        c.getString(9),
                        c.getString(10),
                        c.getString(11),
                        c.getString(12),
                        BigDecimal.valueOf(c.getLong(13)),
                        BigDecimal.valueOf(c.getLong(14))
                );
            }
            return kh;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public Boolean CapNhatThongTinKhachHang(KhachHangDTO kh){
        try{
            String query = "UPDATE tb_khach_hang SET ho = ?, ten = ?, anh_dai_dien_url = ?, gioi_tinh = ?, ngay_sinh = ?, so_dien_thoai = ?, email = ?, mat_khau = ?, so_nha = ?, phuong_xa = ?, quan_huyen = ?, tinh_thanh = ?, so_du_vi = ?, ngay_khoi_tao = ? WHERE id = ?";
            db.execSQL(query, new Object[]{
                    kh.get_ho(),
                    kh.get_ten(),
                    kh.get_anh_dai_dien_url(),
                    kh.get_gioi_tinh(),
                    kh.get_ngay_sinh(),
                    kh.get_so_dien_thoai(),
                    kh.get_email(),
                    kh.get_mat_khau(),
                    kh.get_so_nha(),
                    kh.get_phuong_xa(),
                    kh.get_quan_huyen(),
                    kh.get_tinh_thanh(),
                    kh.get_so_du_vi(),
                    kh.get_ngay_khoi_tao(),
                    kh.get_id()
            });
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    public Boolean KiemTraSoDienThoaiTonTai(String so_dien_thoai){
        try{
            String query = "SELECT COUNT(*) FROM tb_khach_hang WHERE so_dien_thoai = ?";
            Cursor c = db.rawQuery(query, new String[]{so_dien_thoai});
            if(c.moveToNext() && c.getInt(0) > 0){
                return true;
            }
            return false;
        }catch (Exception e){
            e.printStackTrace();
            return true;
        }
    }

    private boolean is_valid(String clearTextPassword, String hashedPass) {
        // returns true if password matches hash
        return BCrypt.checkpw(clearTextPassword, hashedPass);
    }
}
