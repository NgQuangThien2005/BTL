package org.example.services;

import org.example.models.SanPham;
import org.example.repositories.SanPhamRepository;

import java.sql.SQLException;
import java.util.List;

public class SanPhamService {
    private final SanPhamRepository sanPhamRepository = new SanPhamRepository();

    public List<SanPham> getAllSanPham() throws SQLException {
        return sanPhamRepository.getAll();
    }

    public void addSanPham(SanPham sanPham) throws SQLException {
        sanPhamRepository.add(sanPham);
    }

    public void updateSanPham(SanPham sanPham) throws SQLException {
        sanPhamRepository.update(sanPham);
    }

    public void deleteSanPham(String maSanPham) throws SQLException {
        sanPhamRepository.delete(maSanPham);
    }

    public SanPham findSanPhamById(String maSanPham) throws SQLException {
        return sanPhamRepository.findById(maSanPham);
    }

    public boolean isMaSanPhamExists(String maSanPham) throws SQLException {
        return sanPhamRepository.isMaSanPhamExists(maSanPham);
    }

    public void updateSoLuongSanPham(String maSanPham, int soLuong) throws SQLException {
        sanPhamRepository.updateSoLuong(maSanPham, soLuong);
    }
}