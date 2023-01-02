package com.ha_store.dto;

public class AnhSanPhamDTO extends BaseDTO{
    private Integer id;
    private Integer san_pham_id;
    private String anh_san_pham_url;

    public AnhSanPhamDTO(Integer id, Integer san_pham_id, String anh_san_pham_url) {
        this.id = id;
        this.san_pham_id = san_pham_id;
        this.anh_san_pham_url = anh_san_pham_url;
    }

    public Integer getId() {
        return id;
    }

    public Integer getSan_pham_id() {
        return san_pham_id;
    }

    public void setSan_pham_id(Integer san_pham_id) {
        this.san_pham_id = san_pham_id;
    }

    public String getAnh_san_pham_url() {
        return anh_san_pham_url;
    }

    public void setAnh_san_pham_url(String anh_san_pham_url) {
        this.anh_san_pham_url = anh_san_pham_url;
    }
}
