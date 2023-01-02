package com.ha_store.dto;

import java.math.BigDecimal;
import java.text.ParseException;

public class SuKienDTO extends BaseDTO{
    private Integer id;
    private String ten_su_kien;
    private BigDecimal thoi_gian_bat_dau;
    private BigDecimal thoi_gian_ket_thuc;

    public SuKienDTO(Integer id, String ten_su_kien, BigDecimal thoi_gian_bat_dau, BigDecimal thoi_gian_ket_thuc) {
        this.id = id;
        this.ten_su_kien = ten_su_kien;
        this.thoi_gian_bat_dau = thoi_gian_bat_dau;
        this.thoi_gian_ket_thuc = thoi_gian_ket_thuc;
    }

    public Integer getId() {
        return id;
    }

    public String getTen_su_kien() {
        return ten_su_kien;
    }

    public void setTen_su_kien(String ten_su_kien) {
        this.ten_su_kien = ten_su_kien;
    }

    public BigDecimal getThoi_gian_bat_dau() {
        return thoi_gian_bat_dau;
    }

    public void setThoi_gian_bat_dau(BigDecimal thoi_gian_bat_dau) {
        this.thoi_gian_bat_dau = thoi_gian_bat_dau;
    }

    public BigDecimal getThoi_gian_ket_thuc() {
        return thoi_gian_ket_thuc;
    }

    public void setThoi_gian_ket_thuc(BigDecimal thoi_gian_ket_thuc) {
        this.thoi_gian_ket_thuc = thoi_gian_ket_thuc;
    }
    public String getThoi_gian_bat_dau_chu() throws ParseException {
        return convert_big_decimal_to_date(thoi_gian_bat_dau);
    }
    public String getThoi_gian_ket_thuc_chu() throws ParseException {
        return convert_big_decimal_to_date(thoi_gian_bat_dau);
    }
}
