package com.ha_store.dto;

public class SanPhamSuKienDTO extends BaseDTO{
    private Integer san_pham_id;
    private Integer su_kien_id;

    public SanPhamSuKienDTO(Integer san_pham_id, Integer su_kien_id) {
        this.san_pham_id = san_pham_id;
        this.su_kien_id = su_kien_id;
    }

    public Integer getSan_pham_id() {
        return san_pham_id;
    }

    public void setSan_pham_id(Integer san_pham_id) {
        this.san_pham_id = san_pham_id;
    }

    public Integer getSu_kien_id() {
        return su_kien_id;
    }

    public void setSu_kien_id(Integer su_kien_id) {
        this.su_kien_id = su_kien_id;
    }
}
