package com.ha_store.dto;

public class YeuThichDTO extends BaseDTO{
    private Integer khach_hang_id;
    private Integer san_pham_id;
    private Integer da_yeu_thich;

    public YeuThichDTO(Integer khach_hang_id, Integer san_pham_id, Integer da_yeu_thich) {
        this.khach_hang_id = khach_hang_id;
        this.san_pham_id = san_pham_id;
        this.da_yeu_thich = da_yeu_thich;
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

    public Integer getDa_yeu_thich() {
        return da_yeu_thich;
    }

    public void setDa_yeu_thich(Integer da_yeu_thich) {
        this.da_yeu_thich = da_yeu_thich;
    }
}
