package com.ha_store.dto;

public class LoaiSanPhamDTO extends BaseDTO{
    private Integer id;
    private String ten_loai_san_pham;

    public LoaiSanPhamDTO(Integer id, String ten_loai_san_pham) {
        this.id = id;
        this.ten_loai_san_pham = ten_loai_san_pham;
    }

    public Integer getId() {
        return id;
    }

    public String getTen_loai_san_pham() {
        return ten_loai_san_pham;
    }

    public void setTen_loai_san_pham(String ten_loai_san_pham) {
        this.ten_loai_san_pham = ten_loai_san_pham;
    }
}
