package com.ha_store.dto;

import java.math.BigDecimal;

public class ChiTietThanhToanDTO extends BaseDTO{
    private Integer hinh_thuc_thanh_toan_id;
    private Integer hoa_don_id;
    private Integer don_dat_hang_id;
    private BigDecimal so_tien_da_thanh_toan;

    public ChiTietThanhToanDTO(Integer hinh_thuc_thanh_toan_id, Integer hoa_don_id, Integer don_dat_hang_id, BigDecimal so_tien_da_thanh_toan) {
        this.hinh_thuc_thanh_toan_id = hinh_thuc_thanh_toan_id;
        this.hoa_don_id = hoa_don_id;
        this.don_dat_hang_id = don_dat_hang_id;
        this.so_tien_da_thanh_toan = so_tien_da_thanh_toan;
    }

    public Integer getHinh_thuc_thanh_toan_id() {
        return hinh_thuc_thanh_toan_id;
    }

    public void setHinh_thuc_thanh_toan_id(Integer hinh_thuc_thanh_toan_id) {
        this.hinh_thuc_thanh_toan_id = hinh_thuc_thanh_toan_id;
    }

    public Integer getHoa_don_id() {
        return hoa_don_id;
    }

    public void setHoa_don_id(Integer hoa_don_id) {
        this.hoa_don_id = hoa_don_id;
    }

    public Integer getDon_dat_hang_id() {
        return don_dat_hang_id;
    }

    public void setDon_dat_hang_id(Integer don_dat_hang_id) {
        this.don_dat_hang_id = don_dat_hang_id;
    }

    public BigDecimal getSo_tien_da_thanh_toan() {
        return so_tien_da_thanh_toan;
    }

    public void setSo_tien_da_thanh_toan(BigDecimal so_tien_da_thanh_toan) {
        this.so_tien_da_thanh_toan = so_tien_da_thanh_toan;
    }
}
