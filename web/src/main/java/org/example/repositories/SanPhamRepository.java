package org.example.repositories;

import org.example.models.SanPham;
import org.example.utils.DatabaseConnection;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SanPhamRepository {
    public List<SanPham> getAll() throws SQLException {
        List<SanPham> sanPhams = new ArrayList<>();
        String sql = "SELECT * FROM SANPHAM";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSanPham(rs.getString("MASP"));
                sp.setTenSanPham(rs.getString("TENSP"));
                sp.setMaLoai(rs.getString("MALOAI"));
                sp.setDonGia(rs.getDouble("DONGIA"));
                sp.setSoLuong(rs.getInt("SOLUONG"));

                String ngaySXStr = rs.getString("NGAYSX");
                if (ngaySXStr != null) {
                    sp.setNgaySanXuat(LocalDate.parse(ngaySXStr));
                }

                String hanSDStr = rs.getString("HANSD");
                if (hanSDStr != null) {
                    sp.setHanSuDung(LocalDate.parse(hanSDStr));
                }

                sanPhams.add(sp);
            }
        }
        return sanPhams;
    }

    public void add(SanPham sanPham) throws SQLException {
        String sql = "INSERT INTO SANPHAM (MASP, TENSP, MALOAI, DONGIA, SOLUONG, NGAYSX, HANSD) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, sanPham.getMaSanPham());
            stmt.setString(2, sanPham.getTenSanPham());
            stmt.setString(3, sanPham.getMaLoai());
            stmt.setDouble(4, sanPham.getDonGia());
            stmt.setInt(5, sanPham.getSoLuong());
            stmt.setString(6, sanPham.getNgaySanXuat() != null ? sanPham.getNgaySanXuat().toString() : null);
            stmt.setString(7, sanPham.getHanSuDung() != null ? sanPham.getHanSuDung().toString() : null);

            stmt.executeUpdate();
        }
    }

    public void update(SanPham sanPham) throws SQLException {
        String sql = "UPDATE SANPHAM SET TENSP = ?, MALOAI = ?, DONGIA = ?, " +
                "SOLUONG = ?, NGAYSX = ?, HANSD = ? WHERE MASP = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, sanPham.getTenSanPham());
            stmt.setString(2, sanPham.getMaLoai());
            stmt.setDouble(3, sanPham.getDonGia());
            stmt.setInt(4, sanPham.getSoLuong());
            stmt.setString(5, sanPham.getNgaySanXuat() != null ? sanPham.getNgaySanXuat().toString() : null);
            stmt.setString(6, sanPham.getHanSuDung() != null ? sanPham.getHanSuDung().toString() : null);
            stmt.setString(7, sanPham.getMaSanPham());

            stmt.executeUpdate();
        }
    }

    public void delete(String maSanPham) throws SQLException {
        String sql = "DELETE FROM SANPHAM WHERE MASP = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maSanPham);
            stmt.executeUpdate();
        }
    }

    public SanPham findById(String maSanPham) throws SQLException {
        String sql = "SELECT * FROM SANPHAM WHERE MASP = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maSanPham);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                SanPham sp = new SanPham();
                sp.setMaSanPham(rs.getString("MASP"));
                sp.setTenSanPham(rs.getString("TENSP"));
                sp.setMaLoai(rs.getString("MALOAI"));
                sp.setDonGia(rs.getDouble("DONGIA"));
                sp.setSoLuong(rs.getInt("SOLUONG"));

                String ngaySXStr = rs.getString("NGAYSX");
                if (ngaySXStr != null) {
                    sp.setNgaySanXuat(LocalDate.parse(ngaySXStr));
                }

                String hanSDStr = rs.getString("HANSD");
                if (hanSDStr != null) {
                    sp.setHanSuDung(LocalDate.parse(hanSDStr));
                }

                return sp;
            }
        }
        return null;
    }

    public boolean isMaSanPhamExists(String maSanPham) throws SQLException {
        String sql = "SELECT 1 FROM SANPHAM WHERE MASP = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, maSanPham);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public void updateSoLuong(String maSanPham, int soLuong) throws SQLException {
        String sql = "UPDATE SANPHAM SET SOLUONG = SOLUONG + ? WHERE MASP = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, soLuong);
            stmt.setString(2, maSanPham);
            stmt.executeUpdate();
        }
    }
}