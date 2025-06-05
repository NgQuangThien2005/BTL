package org.example.models;

import java.time.LocalDate;
import java.util.List;

public class HoaDon {
    private String maHoaDon;
    private String maKhachHang;
    private String maNhanVien;
    private LocalDate ngayLap;
    private double tongTien;
    private List<ChiTietHoaDon> chiTietHoaDons;

    public HoaDon() {}

    public HoaDon(String maHoaDon, String maKhachHang, String maNhanVien,
                  LocalDate ngayLap, double tongTien) {
        this.maHoaDon = maHoaDon;
        this.maKhachHang = maKhachHang;
        this.maNhanVien = maNhanVien;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
    }

    // Getters and Setters
    public String getMaHoaDon() { return maHoaDon; }
    public void setMaHoaDon(String maHoaDon) { this.maHoaDon = maHoaDon; }

    public String getMaKhachHang() { return maKhachHang; }
    public void setMaKhachHang(String maKhachHang) { this.maKhachHang = maKhachHang; }

    public String getMaNhanVien() { return maNhanVien; }
    public void setMaNhanVien(String maNhanVien) { this.maNhanVien = maNhanVien; }

    public LocalDate getNgayLap() { return ngayLap; }
    public void setNgayLap(LocalDate ngayLap) { this.ngayLap = ngayLap; }

    public double getTongTien() { return tongTien; }
    public void setTongTien(double tongTien) { this.tongTien = tongTien; }

    public List<ChiTietHoaDon> getChiTietHoaDons() { return chiTietHoaDons; }
    public void setChiTietHoaDons(List<ChiTietHoaDon> chiTietHoaDons) {
        this.chiTietHoaDons = chiTietHoaDons;
    }

    @Override
    public String toString() {
        return "HD" + maHoaDon + " - " + ngayLap;
    }
}