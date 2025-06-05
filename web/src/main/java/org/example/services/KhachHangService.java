package org.example.services;

import org.example.models.KhachHang;
import org.example.repositories.KhachHangRepository;

import java.sql.SQLException;
import java.util.List;

public class KhachHangService {
    private final KhachHangRepository khachHangRepository = new KhachHangRepository();

    public List<KhachHang> getAllKhachHang() throws SQLException {
        return khachHangRepository.getAll();
    }

    public void addKhachHang(KhachHang khachHang) throws SQLException {
        khachHangRepository.add(khachHang);
    }

    public void updateKhachHang(KhachHang khachHang) throws SQLException {
        khachHangRepository.update(khachHang);
    }

    public void deleteKhachHang(String maKhachHang) throws SQLException {
        khachHangRepository.delete(maKhachHang);
    }

    public KhachHang findKhachHangById(String maKhachHang) throws SQLException {
        return khachHangRepository.findById(maKhachHang);
    }

    public boolean isMaKhachHangExists(String maKhachHang) throws SQLException {
        return khachHangRepository.isMaKhachHangExists(maKhachHang);
    }
}