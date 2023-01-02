package com.ha_store.dto;

public class KhoDTO extends BaseDTO{
    private Integer san_pham_id;
    private Integer kich_thuoc_id;
    private Integer so_luong;

    public KhoDTO(Integer san_pham_id, Integer kich_thuoc_id, Integer so_luong) {
        this.san_pham_id = san_pham_id;
        this.kich_thuoc_id = kich_thuoc_id;
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

    public Integer getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(Integer so_luong) {
        this.so_luong = so_luong;
    }
}
