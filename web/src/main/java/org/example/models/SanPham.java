package org.example.models;

import java.time.LocalDate;

public class SanPham {
    private String maSanPham;
    private String tenSanPham;
    private String maLoai;
    private double donGia;
    private int soLuong;
    private LocalDate ngaySanXuat;
    private LocalDate hanSuDung;

    public SanPham() {}

    public SanPham(String maSanPham, String tenSanPham, String maLoai,
                   double donGia, int soLuong, LocalDate ngaySanXuat,
                   LocalDate hanSuDung) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.maLoai = maLoai;
        this.donGia = donGia;
        this.soLuong = soLuong;
        this.ngaySanXuat = ngaySanXuat;
        this.hanSuDung = hanSuDung;
    }

    // Getters and Setters
    public String getMaSanPham() { return maSanPham; }
    public void setMaSanPham(String maSanPham) { this.maSanPham = maSanPham; }

    public String getTenSanPham() { return tenSanPham; }
    public void setTenSanPham(String tenSanPham) { this.tenSanPham = tenSanPham; }

    public String getMaLoai() { return maLoai; }
    public void setMaLoai(String maLoai) { this.maLoai = maLoai; }

    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public LocalDate getNgaySanXuat() { return ngaySanXuat; }
    public void setNgaySanXuat(LocalDate ngaySanXuat) { this.ngaySanXuat = ngaySanXuat; }

    public LocalDate getHanSuDung() { return hanSuDung; }
    public void setHanSuDung(LocalDate hanSuDung) { this.hanSuDung = hanSuDung; }

    @Override
    public String toString() {
        return tenSanPham + " (" + maSanPham + ")";
    }
}