package com.ha_store.dto;

import java.math.BigDecimal;
import java.text.ParseException;

public class BinhLuanDTO extends BaseDTO{
    private Integer id;
    private Integer khach_hang_id;
    private Integer san_pham_id;
    private String noi_dung;
    private BigDecimal ngay_binh_luan;

    public BinhLuanDTO(Integer id, Integer khach_hang_id, Integer san_pham_id, String noi_dung, BigDecimal ngay_binh_luan) {
        this.id = id;
        this.khach_hang_id = khach_hang_id;
        this.san_pham_id = san_pham_id;
        this.noi_dung = noi_dung;
        this.ngay_binh_luan = ngay_binh_luan;
    }

    public Integer getId() {
        return id;
    }

    public Integer getKhach_hang_id() {
        return khach_hang_id;
    }

    public void setKhach_hang_id(Integer khach_hang_id) {
        this.khach_hang_id = khach_hang_id;
    }

    public Integer getSan_pham_id() {
        return san_pham_id;
    }

    public void setSan_pham_id(Integer san_pham_id) {
        this.san_pham_id = san_pham_id;
    }

    public String getNoi_dung() {
        return noi_dung;
    }

    public void setNoi_dung(String noi_dung) {
        this.noi_dung = noi_dung;
    }

    public BigDecimal getNgay_binh_luan() {
        return ngay_binh_luan;
    }

    public void setNgay_binh_luan(BigDecimal ngay_binh_luan) {
        this.ngay_binh_luan = ngay_binh_luan;
    }
    public String getNgay_binh_luan_chu() throws ParseException {
        return convert_big_decimal_to_date(ngay_binh_luan);
    }
}
