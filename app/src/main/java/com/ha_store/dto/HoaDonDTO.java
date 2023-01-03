package com.ha_store.dto;

import java.math.BigDecimal;
import java.text.ParseException;

public class HoaDonDTO extends BaseDTO{
    private Integer id;
    private Integer quan_tri_vien_id;
    private Integer trang_thai;
    private BigDecimal ngay_khoi_tao;

    public HoaDonDTO(Integer id, Integer quan_tri_vien_id, Integer trang_thai, BigDecimal ngay_khoi_tao) {
        this.id = id;
        this.quan_tri_vien_id = quan_tri_vien_id;
        this.trang_thai = trang_thai;
        this.ngay_khoi_tao = ngay_khoi_tao;
    }

    public HoaDonDTO(Integer quan_tri_vien_id, Integer trang_thai, BigDecimal ngay_khoi_tao) {
        this.quan_tri_vien_id = quan_tri_vien_id;
        this.trang_thai = trang_thai;
        this.ngay_khoi_tao = ngay_khoi_tao;
    }

    public Integer getId() {
        return id;
    }

    public Integer getQuan_tri_vien_id() {
        return quan_tri_vien_id;
    }

    public void setQuan_tri_vien_id(Integer quan_tri_vien_id) {
        this.quan_tri_vien_id = quan_tri_vien_id;
    }

    public Integer getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(Integer trang_thai) {
        this.trang_thai = trang_thai;
    }

    public BigDecimal getNgay_khoi_tao() {
        return ngay_khoi_tao;
    }

    public void setNgay_khoi_tao(BigDecimal ngay_khoi_tao) {
        this.ngay_khoi_tao = ngay_khoi_tao;
    }
    public String getNgay_khoi_tao_chu() throws ParseException {
        return convert_big_decimal_to_date(ngay_khoi_tao);
    }
}
