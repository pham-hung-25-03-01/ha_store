package com.ha_store.dto;

import java.math.BigDecimal;

public class ChiTietDonDatHangDTO extends BaseDTO{
    private Integer san_pham_id;
    private Integer kich_thuoc_id;
    private Integer don_dat_hang_id;
    private BigDecimal gia_ban;
    private Float phan_tram_khuyen_mai;
    private Integer so_luong;

    public ChiTietDonDatHangDTO(Integer san_pham_id, Integer kich_thuoc_id, Integer don_dat_hang_id, BigDecimal gia_ban, Float phan_tram_khuyen_mai, Integer so_luong) {
        this.san_pham_id = san_pham_id;
        this.kich_thuoc_id = kich_thuoc_id;
        this.don_dat_hang_id = don_dat_hang_id;
        this.gia_ban = gia_ban;
        this.phan_tram_khuyen_mai = phan_tram_khuyen_mai;
        this.so_luong = so_luong;
    }

    public Integer getSan_pham_id() {
        return san_pham_id;
    }

    public void setSan_pham_id(Integer san_pham_id) {
        this.san_pham_id = san_pham_id;
    }

    public Integer getKich_thuoc_id() {
        return kich_thuoc_id;
    }

    public void setKich_thuoc_id(Integer kich_thuoc_id) {
        this.kich_thuoc_id = kich_thuoc_id;
    }

    public Integer getDon_dat_hang_id() {
        return don_dat_hang_id;
    }

    public void setDon_dat_hang_id(Integer don_dat_hang_id) {
        this.don_dat_hang_id = don_dat_hang_id;
    }

    public BigDecimal getGia_ban() {
        return gia_ban;
    }

    public void setGia_ban(BigDecimal gia_ban) {
        this.gia_ban = gia_ban;
    }

    public Float getPhan_tram_khuyen_mai() {
        return phan_tram_khuyen_mai;
    }

    public void setPhan_tram_khuyen_mai(Float phan_tram_khuyen_mai) {
        this.phan_tram_khuyen_mai = phan_tram_khuyen_mai;
    }

    public Integer getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(Integer so_luong) {
        this.so_luong = so_luong;
    }
}
