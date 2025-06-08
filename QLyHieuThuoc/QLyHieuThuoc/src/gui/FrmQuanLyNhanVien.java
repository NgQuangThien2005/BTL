package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class FrmQuanLyNhanVien {
    // Panel đại diện để add vào tabbedPane
    public JPanel pnlTabQLNV = new JPanel();

    public FrmQuanLyNhanVien() {
        pnlTabQLNV.setLayout(null);
        pnlTabQLNV.setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("Quản lý nhân viên");
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblTitle.setBounds(30, 30, 300, 40);

        pnlTabQLNV.add(lblTitle);

        // Bạn có thể code thêm phần bảng, form nhập, các nút chức năng ở đây sau
    }
}