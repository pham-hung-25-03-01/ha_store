package com.ha_store.dto;

public class HinhThucThanhToanDTO extends BaseDTO{
    private Integer id;
    private String ten_hinh_thuc_thanh_toan;

    public HinhThucThanhToanDTO(Integer id, String ten_hinh_thuc_thanh_toan) {
        this.id = id;
        this.ten_hinh_thuc_thanh_toan = ten_hinh_thuc_thanh_toan;
    }

    public Integer getId() {
        return id;
    }

    public String getTen_hinh_thuc_thanh_toan() {
        return ten_hinh_thuc_thanh_toan;
    }

    public void setTen_hinh_thuc_thanh_toan(String ten_hinh_thuc_thanh_toan) {
        this.ten_hinh_thuc_thanh_toan = ten_hinh_thuc_thanh_toan;
    }
}
