package com.ha_store.dto;

import java.math.BigDecimal;
import java.text.ParseException;

public class KhuyenMaiDTO extends BaseDTO{
    private String id;
    private Double phan_tram_khuyen_mai;
    private BigDecimal ngay_bat_dau;
    private BigDecimal ngay_ket_thuc;
    private Integer so_luong;

    public KhuyenMaiDTO(String id, Double phan_tram_khuyen_mai, BigDecimal ngay_bat_dau, BigDecimal ngay_ket_thuc, Integer so_luong) {
        this.id = id;
        this.phan_tram_khuyen_mai = phan_tram_khuyen_mai;
        this.ngay_bat_dau = ngay_bat_dau;
        this.ngay_ket_thuc = ngay_ket_thuc;
        this.so_luong = so_luong;
    }

    public String getId() {
        return id;
    }

    public Double getPhan_tram_khuyen_mai() {
        return phan_tram_khuyen_mai;
    }

    public void setPhan_tram_khuyen_mai(Double phan_tram_khuyen_mai) {
        this.phan_tram_khuyen_mai = phan_tram_khuyen_mai;
    }

    public BigDecimal getNgay_bat_dau() {
        return ngay_bat_dau;
    }

    public void setNgay_bat_dau(BigDecimal ngay_bat_dau) {
        this.ngay_bat_dau = ngay_bat_dau;
    }

    public BigDecimal getNgay_ket_thuc() {
        return ngay_ket_thuc;
    }

    public void setNgay_ket_thuc(BigDecimal ngay_ket_thuc) {
        this.ngay_ket_thuc = ngay_ket_thuc;
    }

    public Integer getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(Integer so_luong) {
        this.so_luong = so_luong;
    }
    public String getNgay_bat_dau_chu() throws ParseException {
        return convert_big_decimal_to_date(ngay_bat_dau);
    }
    public String getNgay_ket_thuc_chu() throws ParseException {
        return convert_big_decimal_to_date(ngay_ket_thuc);
    }
}
