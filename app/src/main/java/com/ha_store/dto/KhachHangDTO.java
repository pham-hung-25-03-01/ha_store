package com.ha_store.dto;

import org.mindrot.jbcrypt.BCrypt;

import java.math.BigDecimal;
import java.sql.Time;
import java.text.ParseException;
import java.util.Date;

public class KhachHangDTO extends BaseDTO{
    private Integer id;
    private String ho;
    private String ten;
    private String anh_dai_dien_url;
    private Integer gioi_tinh;
    private BigDecimal ngay_sinh;
    private String so_dien_thoai;
    private String email;
    private String mat_khau;
    private String so_nha;
    private String phuong_xa;
    private String quan_huyen;
    private String tinh_thanh;
    private BigDecimal so_du_vi;
    private BigDecimal ngay_khoi_tao;

    public KhachHangDTO(){
        this.ngay_khoi_tao = convert_date_to_big_decimal(new Date());
    }

    public KhachHangDTO(Integer id, String ho, String ten, String anh_dai_dien_url, Integer gioi_tinh, BigDecimal ngay_sinh, String so_dien_thoai, String email, String mat_khau, String so_nha, String phuong_xa, String quan_huyen, String tinh_thanh, BigDecimal so_du_vi, BigDecimal ngay_khoi_tao) {
        this.id = id;
        this.ho = ho;
        this.ten = ten;
        this.anh_dai_dien_url = anh_dai_dien_url;
        this.gioi_tinh = gioi_tinh;
        this.ngay_sinh = ngay_sinh;
        this.so_dien_thoai = so_dien_thoai;
        this.email = email;
        this.mat_khau = mat_khau;
        this.so_nha = so_nha;
        this.phuong_xa = phuong_xa;
        this.quan_huyen = quan_huyen;
        this.tinh_thanh = tinh_thanh;
        this.so_du_vi = so_du_vi;
        this.ngay_khoi_tao = ngay_khoi_tao;
    }

    public Integer get_id() {
        return id;
    }
    public String get_ho(){ return ho; }
    public String get_ten(){
        return ten;
    }
    public String ho_ten(){
        return String.format("%s %s", ho, ten);
    }
    public String get_anh_dai_dien_url() {
        return anh_dai_dien_url;
    }

    public Integer get_gioi_tinh() { return gioi_tinh ; }
    public String get_gioi_tinh_chu(){
        switch (gioi_tinh){
            case 0: return "Nữ";
            case 1: return "Nam";
            default: return "Khác";
        }
    }

    public String get_ngay_sinh_chu() throws ParseException {
        return convert_big_decimal_to_date(ngay_sinh);
    }
    public BigDecimal get_ngay_sinh() { return ngay_sinh; }

    public String get_so_dien_thoai() {
        return so_dien_thoai;
    }

    public String get_email() {
        return email;
    }

    public String get_mat_khau() {
        return mat_khau;
    }

    public String get_so_nha() {
        return so_nha;
    }

    public String get_phuong_xa() {
        return phuong_xa;
    }

    public String get_quan_huyen() {
        return quan_huyen;
    }

    public String get_tinh_thanh() {
        return tinh_thanh;
    }

    public BigDecimal get_so_du_vi() {
        return so_du_vi;
    }

    public String get_ngay_khoi_tao_chu() throws ParseException {
        return convert_big_decimal_to_date(ngay_khoi_tao);
    }
    public BigDecimal get_ngay_khoi_tao() { return ngay_khoi_tao; }
    
    public void set_ho(String ho) {
        this.ho = ho;
    }

    public void set_ten(String ten) {
        this.ten = ten;
    }

    public void set_anh_dai_dien_url(String anh_dai_dien_url) {
        this.anh_dai_dien_url = anh_dai_dien_url;
    }

    public void set_gioi_tinh(Integer gioi_tinh) {
        this.gioi_tinh = gioi_tinh;
    }

    public void set_ngay_sinh(Date ngay_sinh) {
        this.ngay_sinh = convert_date_to_big_decimal(ngay_sinh);
    }

    public void set_so_dien_thoai(String so_dien_thoai) {
        this.so_dien_thoai = so_dien_thoai;
    }

    public void set_email(String email) {
        this.email = email;
    }

    public void set_mat_khau(String mat_khau) {
        this.mat_khau = generate_hashed_pass(mat_khau);
    }

    public void set_so_nha(String so_nha) {
        this.so_nha = so_nha;
    }

    public void set_phuong_xa(String phuong_xa) {
        this.phuong_xa = phuong_xa;
    }

    public void set_quan_huyen(String quan_huyen) {
        this.quan_huyen = quan_huyen;
    }

    public void set_tinh_thanh(String tinh_thanh) {
        this.tinh_thanh = tinh_thanh;
    }

    public void set_so_du_vi(BigDecimal so_du_vi) {
        this.so_du_vi = so_du_vi;
    }
    public static String generateHashedPass(String pass) {
        // hash a plaintext password using the typical log rounds (10)
        return BCrypt.hashpw(pass, BCrypt.gensalt());
    }
}
