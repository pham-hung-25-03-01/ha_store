package com.ha_store.dto;

public class KichThuocDTO extends BaseDTO{
    private Integer id;
    private String ten_kich_thuoc;

    public KichThuocDTO(Integer id, String ten_kich_thuoc) {
        this.id = id;
        this.ten_kich_thuoc = ten_kich_thuoc;
    }

    public Integer getId() {
        return id;
    }

    public String getTen_kich_thuoc() {
        return ten_kich_thuoc;
    }

    public void setTen_kich_thuoc(String ten_kich_thuoc) {
        this.ten_kich_thuoc = ten_kich_thuoc;
    }
}
