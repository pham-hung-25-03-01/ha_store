package com.ha_store.dto;

public class XepHangDTO extends BaseDTO{
    private Integer san_pham_id;
    private Integer khach_hang_id;
    private Integer diem_xep_hang;

    public XepHangDTO(Integer san_pham_id, Integer khach_hang_id, Integer diem_xep_hang) {
        this.san_pham_id = san_pham_id;
        this.khach_hang_id = khach_hang_id;
        this.diem_xep_hang = diem_xep_hang;
    }

    public Integer getSan_pham_id() {
        return san_pham_id;
    }

    public void setSan_pham_id(Integer san_pham_id) {
        this.san_pham_id = san_pham_id;
    }

    public Integer getKhach_hang_id() {
        return khach_hang_id;
    }

    public void setKhach_hang_id(Integer khach_hang_id) {
        this.khach_hang_id = khach_hang_id;
    }

    public Integer getDiem_xep_hang() {
        return diem_xep_hang;
    }

    public void setDiem_xep_hang(Integer diem_xep_hang) {
        this.diem_xep_hang = diem_xep_hang;
    }
}
