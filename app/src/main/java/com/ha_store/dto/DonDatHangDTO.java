package com.ha_store.dto;

import java.math.BigDecimal;
import java.text.ParseException;

public class DonDatHangDTO extends BaseDTO{
    private Integer id;
    private Integer khach_hang_id;
    private String khuyen_mai_id;
    private BigDecimal ngay_giao;
	private String so_nha;
	private String phuong_xa;
	private String quan_huyen;
	private String tinh_thanh;
	private BigDecimal tong_tien;
	private Integer trang_thai;
	private BigDecimal ngay_khoi_tao;

	public DonDatHangDTO(Integer id, Integer khach_hang_id, String khuyen_mai_id, BigDecimal ngay_giao, String so_nha, String phuong_xa, String quan_huyen, String tinh_thanh, BigDecimal tong_tien, Integer trang_thai, BigDecimal ngay_khoi_tao) {
		this.id = id;
		this.khach_hang_id = khach_hang_id;
		this.khuyen_mai_id = khuyen_mai_id;
		this.ngay_giao = ngay_giao;
		this.so_nha = so_nha;
		this.phuong_xa = phuong_xa;
		this.quan_huyen = quan_huyen;
		this.tinh_thanh = tinh_thanh;
		this.tong_tien = tong_tien;
		this.trang_thai = trang_thai;
		this.ngay_khoi_tao = ngay_khoi_tao;
	}

	public DonDatHangDTO(Integer khach_hang_id, String khuyen_mai_id, BigDecimal ngay_giao, String so_nha, String phuong_xa, String quan_huyen, String tinh_thanh, BigDecimal tong_tien, Integer trang_thai, BigDecimal ngay_khoi_tao) {
		this.khach_hang_id = khach_hang_id;
		this.khuyen_mai_id = khuyen_mai_id;
		this.ngay_giao = ngay_giao;
		this.so_nha = so_nha;
		this.phuong_xa = phuong_xa;
		this.quan_huyen = quan_huyen;
		this.tinh_thanh = tinh_thanh;
		this.tong_tien = tong_tien;
		this.trang_thai = trang_thai;
		this.ngay_khoi_tao = ngay_khoi_tao;
	}

	public Integer getId() {
		return id;
	}

	public Integer getKhach_hang_id() {
		return khach_hang_id;
	}

	public void setKhach_hang_id(Integer khach_hang_id) {
		this.khach_hang_id = khach_hang_id;
	}

	public String getKhuyen_mai_id() {
		return khuyen_mai_id;
	}

	public void setKhuyen_mai_id(String khuyen_mai_id) {
		this.khuyen_mai_id = khuyen_mai_id;
	}

	public BigDecimal getNgay_giao() {
		return ngay_giao;
	}

	public void setNgay_giao(BigDecimal ngay_giao) {
		this.ngay_giao = ngay_giao;
	}

	public String getSo_nha() {
		return so_nha;
	}

	public void setSo_nha(String so_nha) {
		this.so_nha = so_nha;
	}

	public String getPhuong_xa() {
		return phuong_xa;
	}

	public void setPhuong_xa(String phuong_xa) {
		this.phuong_xa = phuong_xa;
	}

	public String getQuan_huyen() {
		return quan_huyen;
	}

	public void setQuan_huyen(String quan_huyen) {
		this.quan_huyen = quan_huyen;
	}

	public String getTinh_thanh() {
		return tinh_thanh;
	}

	public void setTinh_thanh(String tinh_thanh) {
		this.tinh_thanh = tinh_thanh;
	}

	public BigDecimal getTong_tien() {
		return tong_tien;
	}

	public void setTong_tien(BigDecimal tong_tien) {
		this.tong_tien = tong_tien;
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

	public String getNgay_giao_chu() throws ParseException {
		return convert_big_decimal_to_date(ngay_giao);
	}
	public String getNgay_khoi_tao_chu() throws ParseException {
		return convert_big_decimal_to_date(ngay_khoi_tao);
	}
	public String getDiaChi(){
		return String.format("%s, %s, %s, %s", so_nha, phuong_xa, quan_huyen, tinh_thanh);
	}
}
