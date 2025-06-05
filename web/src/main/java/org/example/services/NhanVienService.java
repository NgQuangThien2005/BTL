package org.example.services;

import org.example.models.NhanVien;
import org.example.repositories.NhanVienRepository;

import java.sql.SQLException;
import java.util.List;

public class NhanVienService {
    private final NhanVienRepository nhanVienRepository = new NhanVienRepository();

    public List<NhanVien> getAllNhanVien() throws SQLException {
        return nhanVienRepository.getAll();
    }

    public void addNhanVien(NhanVien nhanVien) throws SQLException {
        nhanVienRepository.add(nhanVien);
    }

    public void updateNhanVien(NhanVien nhanVien) throws SQLException {
        nhanVienRepository.update(nhanVien);
    }

    public void deleteNhanVien(String maNhanVien) throws SQLException {
        nhanVienRepository.delete(maNhanVien);
    }

    public NhanVien findNhanVienById(String maNhanVien) throws SQLException {
        return nhanVienRepository.findById(maNhanVien);
    }

    public boolean isMaNhanVienExists(String maNhanVien) throws SQLException {
        return nhanVienRepository.isMaNhanVienExists(maNhanVien);
    }
}