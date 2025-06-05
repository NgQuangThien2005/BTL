package org.example.repositories;

import org.example.models.KhachHang;
import org.example.utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHangRepository {
    public List<KhachHang> getAll() throws SQLException {
        List<KhachHang> khachHangs = new ArrayList<>();
        String sql = "SELECT * FROM KHACHHANG";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKhachHang(rs.getString("MAKH"));
                kh.setTenKhachHang(rs.getString("TENKH"));
                kh.setSdt(rs.getString("SDT"));
                kh.setGioiTinh(rs.getString("GIOITINH"));
                kh.setDiaChi(rs.getString("DIACHI"));
                khachHangs.add(kh);
            }
        }
        return khachHangs;
    }

    public void add(KhachHang khachHang) throws SQLException {
        String sql = "INSERT INTO KHACHHANG (MAKH, TENKH, SDT, GIOITINH, DIACHI) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, khachHang.getMaKhachHang());
            stmt.setString(2, khachHang.getTenKhachHang());
            stmt.setString(3, khachHang.getSdt());
            stmt.setString(4, khachHang.getGioiTinh());
            stmt.setString(5, khachHang.getDiaChi());

            stmt.executeUpdate();
        }
    }

    public void update(KhachHang khachHang) throws SQLException {
        String sql = "UPDATE KHACHHANG SET TENKH = ?, SDT = ?, GIOITINH = ?, " +
                "DIACHI = ? WHERE MAKH = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, khachHang.getTenKhachHang());
            stmt.setString(2, khachHang.getSdt());
            stmt.setString(3, khachHang.getGioiTinh());
            stmt.setString(4, khachHang.getDiaChi());
            stmt.setString(5, khachHang.getMaKhachHang());

            stmt.executeUpdate();
        }
    }

    public void delete(String maKhachHang) throws SQLException {
        String sql = "DELETE FROM KHACHHANG WHERE MAKH = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maKhachHang);
            stmt.executeUpdate();
        }
    }

    public KhachHang findById(String maKhachHang) throws SQLException {
        String sql = "SELECT * FROM KHACHHANG WHERE MAKH = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maKhachHang);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKhachHang(rs.getString("MAKH"));
                kh.setTenKhachHang(rs.getString("TENKH"));
                kh.setSdt(rs.getString("SDT"));
                kh.setGioiTinh(rs.getString("GIOITINH"));
                kh.setDiaChi(rs.getString("DIACHI"));
                return kh;
            }
        }
        return null;
    }

    public boolean isMaKhachHangExists(String maKhachHang) throws SQLException {
        String sql = "SELECT 1 FROM KHACHHANG WHERE MAKH = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maKhachHang);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }
}