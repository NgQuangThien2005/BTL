package org.example.repositories;

import org.example.models.NhanVien;
import org.example.utils.DatabaseConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class NhanVienRepository {
    public List<NhanVien> getAll() throws SQLException {
        List<NhanVien> nhanViens = new ArrayList<>();
        String sql = "SELECT * FROM NHANVIEN";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(rs.getString("MANHANVIEN"));
                nv.setHoVaTen(rs.getString("HOVATEN"));
                nv.setGioiTinh(rs.getString("GIOITINH"));
                nv.setSdt(rs.getString("SDT"));

                String ngaySinhStr = rs.getString("NGAYSINH");
                if (ngaySinhStr != null) {
                    nv.setNgaySinh(LocalDate.parse(ngaySinhStr));
                }

                nv.setDiaChi(rs.getString("DIACHI"));
                nv.setMaChucVu(rs.getString("MACHUCVU"));
                nhanViens.add(nv);
            }
        }
        return nhanViens;
    }

    public void add(NhanVien nhanVien) throws SQLException {
        String sql = "INSERT INTO NHANVIEN (MANHANVIEN, HOVATEN, GIOITINH, SDT, NGAYSINH, DIACHI, MACHUCVU) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nhanVien.getMaNhanVien());
            stmt.setString(2, nhanVien.getHoVaTen());
            stmt.setString(3, nhanVien.getGioiTinh());
            stmt.setString(4, nhanVien.getSdt());
            stmt.setString(5, nhanVien.getNgaySinh() != null ? nhanVien.getNgaySinh().toString() : null);
            stmt.setString(6, nhanVien.getDiaChi());
            stmt.setString(7, nhanVien.getMaChucVu());

            stmt.executeUpdate();
        }
    }

    public void update(NhanVien nhanVien) throws SQLException {
        String sql = "UPDATE NHANVIEN SET HOVATEN = ?, GIOITINH = ?, SDT = ?, " +
                "NGAYSINH = ?, DIACHI = ?, MACHUCVU = ? WHERE MANHANVIEN = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nhanVien.getHoVaTen());
            stmt.setString(2, nhanVien.getGioiTinh());
            stmt.setString(3, nhanVien.getSdt());
            stmt.setString(4, nhanVien.getNgaySinh() != null ? nhanVien.getNgaySinh().toString() : null);
            stmt.setString(5, nhanVien.getDiaChi());
            stmt.setString(6, nhanVien.getMaChucVu());
            stmt.setString(7, nhanVien.getMaNhanVien());

            stmt.executeUpdate();
        }
    }

    public void delete(String maNhanVien) throws SQLException {
        String sql = "DELETE FROM NHANVIEN WHERE MANHANVIEN = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maNhanVien);
            stmt.executeUpdate();
        }
    }

    public NhanVien findById(String maNhanVien) throws SQLException {
        String sql = "SELECT * FROM NHANVIEN WHERE MANHANVIEN = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maNhanVien);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNhanVien(rs.getString("MANHANVIEN"));
                nv.setHoVaTen(rs.getString("HOVATEN"));
                nv.setGioiTinh(rs.getString("GIOITINH"));
                nv.setSdt(rs.getString("SDT"));

                String ngaySinhStr = rs.getString("NGAYSINH");
                if (ngaySinhStr != null) {
                    nv.setNgaySinh(LocalDate.parse(ngaySinhStr));
                }

                nv.setDiaChi(rs.getString("DIACHI"));
                nv.setMaChucVu(rs.getString("MACHUCVU"));
                return nv;
            }
        }
        return null;
    }

    public boolean isMaNhanVienExists(String maNhanVien) throws SQLException {
        String sql = "SELECT 1 FROM NHANVIEN WHERE MANHANVIEN = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maNhanVien);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }
}