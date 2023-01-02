package com.ha_store.dto;

import java.math.BigDecimal;
import java.text.ParseException;

public class HoaDonDTO extends BaseDTO{
    private Integer id;
    private Integer quan_tri_vien_id;
    private BigDecimal tong_tien_da_thanh_toan;
    private Integer trang_thai;
    private BigDecimal ngay_khoi_tao;

    public HoaDonDTO(Integer id, Integer quan_tri_vien_id, BigDecimal tong_tien_da_thanh_toan, Integer trang_thai, BigDecimal ngay_khoi_tao) {
        this.id = id;
        this.quan_tri_vien_id = quan_tri_vien_id;
        this.tong_tien_da_thanh_toan = tong_tien_da_thanh_toan;
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

    public BigDecimal getTong_tien_da_thanh_toan() {
        return tong_tien_da_thanh_toan;
    }

    public void setTong_tien_da_thanh_toan(BigDecimal tong_tien_da_thanh_toan) {
        this.tong_tien_da_thanh_toan = tong_tien_da_thanh_toan;
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
