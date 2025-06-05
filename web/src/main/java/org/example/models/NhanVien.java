package org.example.models;

import java.time.LocalDate;

public class NhanVien {
    private String maNhanVien;
    private String hoVaTen;
    private String gioiTinh;
    private String sdt;
    private LocalDate ngaySinh;
    private String diaChi;
    private String maChucVu;

    public NhanVien() {}

    public NhanVien(String maNhanVien, String hoVaTen, String gioiTinh, String sdt,
                    LocalDate ngaySinh, String diaChi, String maChucVu) {
        this.maNhanVien = maNhanVien;
        this.hoVaTen = hoVaTen;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.ngaySinh = ngaySinh;
        this.diaChi = diaChi;
        this.maChucVu = maChucVu;
    }

    // Getters and Setters
    public String getMaNhanVien() { return maNhanVien; }
    public void setMaNhanVien(String maNhanVien) { this.maNhanVien = maNhanVien; }

    public String getHoVaTen() { return hoVaTen; }
    public void setHoVaTen(String hoVaTen) { this.hoVaTen = hoVaTen; }

    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }

    public String getSdt() { return sdt; }
    public void setSdt(String sdt) { this.sdt = sdt; }

    public LocalDate getNgaySinh() { return ngaySinh; }
    public void setNgaySinh(LocalDate ngaySinh) { this.ngaySinh = ngaySinh; }

    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }

    public String getMaChucVu() { return maChucVu; }
    public void setMaChucVu(String maChucVu) { this.maChucVu = maChucVu; }

    @Override
    public String toString() {
        return hoVaTen + " (" + maNhanVien + ")";
    }
}