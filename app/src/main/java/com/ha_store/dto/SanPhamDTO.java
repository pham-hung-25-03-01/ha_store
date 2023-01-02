package com.ha_store.dto;

import java.math.BigDecimal;

public class SanPhamDTO extends BaseDTO{
    private Integer id;
    private Integer quan_tri_vien_id;
    private Integer loai_san_pham_id;
    private String ten_san_pham;
    private String chat_lieu;
    private String mo_ta;
    private String  xuat_xu;
    private Integer gioi_tinh;
    private BigDecimal gia_nhap;
    private BigDecimal gia_ban;
    private Float phan_tram_khuyen_mai;
    private Float diem_xep_hang;
    private Integer so_luot_xep_hang;

    public Integer get_id() {
        return id;
    }

    public Integer get_quan_tri_vien_id() {
        return quan_tri_vien_id;
    }

    public void set_quan_tri_vien_id(Integer quan_tri_vien_id) {
        this.quan_tri_vien_id = quan_tri_vien_id;
    }

    public Integer get_loai_san_pham_id() {
        return loai_san_pham_id;
    }

    public void set_loai_san_pham_id(Integer loai_san_pham_id) {
        this.loai_san_pham_id = loai_san_pham_id;
    }

    public String get_ten_san_pham() {
        return ten_san_pham;
    }

    public void set_ten_san_pham(String ten_san_pham) {
        this.ten_san_pham = ten_san_pham;
    }

    public String get_chat_lieu() {
        return chat_lieu;
    }

    public void set_chat_lieu(String chat_lieu) {
        this.chat_lieu = chat_lieu;
    }

    public String get_mo_ta() {
        return mo_ta;
    }

    public void set_mo_ta(String mo_ta) {
        this.mo_ta = mo_ta;
    }

    public String get_xuat_xu() {
        return xuat_xu;
    }

    public void set_xuat_xu(String xuat_xu) {
        this.xuat_xu = xuat_xu;
    }

    public String get_gioi_tinh() {
        switch (gioi_tinh){
            case 0: return "Nữ";
            case 1: return "Nam";
            default: return "Khác";
        }
    }

    public void set_gioi_tinh(Integer gioi_tinh) {
        this.gioi_tinh = gioi_tinh;
    }

    public BigDecimal get_gia_nhap() {
        return gia_nhap;
    }

    public void set_gia_nhap(BigDecimal gia_nhap) {
        this.gia_nhap = gia_nhap;
    }

    public BigDecimal get_gia_ban() {
        return gia_ban;
    }

    public void set_gia_ban(BigDecimal gia_ban) {
        this.gia_ban = gia_ban;
    }

    public Float get_phan_tram_khuyen_mai() {
        return phan_tram_khuyen_mai;
    }

    public void set_phan_tram_khuyen_mai(Float phan_tram_khuyen_mai) {
        this.phan_tram_khuyen_mai = phan_tram_khuyen_mai;
    }

    public Float get_diem_xep_hang() {
        return diem_xep_hang;
    }

    public void set_diem_xep_hang(Float diem_xep_hang) {
        this.diem_xep_hang = diem_xep_hang;
    }

    public Integer get_so_luot_xep_hang() {
        return so_luot_xep_hang;
    }

    public void set_so_luot_xep_hang(Integer so_luot_xep_hang) {
        this.so_luot_xep_hang = so_luot_xep_hang;
    }

    public SanPhamDTO(
            Integer id,
            Integer quan_tri_vien_id,
            Integer loai_san_pham_id,
            String ten_san_pham,
            String chat_lieu,
            String mo_ta,
            String xuat_xu,
            Integer gioi_tinh,
            BigDecimal gia_nhap,
            BigDecimal gia_ban,
            Float phan_tram_khuyen_mai,
            Float diem_xep_hang,
            Integer so_luot_xep_hang){
        this.id = id;
        this.quan_tri_vien_id = quan_tri_vien_id;
        this.loai_san_pham_id = loai_san_pham_id;
        this.ten_san_pham = ten_san_pham;
        this.chat_lieu = chat_lieu;
        this.mo_ta = mo_ta;
        this.xuat_xu = xuat_xu;
        this.gioi_tinh = gioi_tinh;
        this.gia_nhap = gia_nhap;
        this.gia_ban = gia_ban;
        this.phan_tram_khuyen_mai = phan_tram_khuyen_mai;
        this.diem_xep_hang = diem_xep_hang;
        this.so_luot_xep_hang = so_luot_xep_hang;
    }
}
