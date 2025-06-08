package gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class FrmXemThongTinCaNhan {
    // Panel đại diện để add vào tabbedPane
    public JPanel pnlTabXemThongTinCaNhan = new JPanel();

    public FrmXemThongTinCaNhan() {
        pnlTabXemThongTinCaNhan.setLayout(null);
        pnlTabXemThongTinCaNhan.setBackground(Color.WHITE);

        JLabel lblTitle = new JLabel("Xem thông tin cá nhân");
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblTitle.setBounds(30, 30, 350, 40);

        pnlTabXemThongTinCaNhan.add(lblTitle);

        // Bạn có thể code thêm phần hiển thị thông tin cá nhân ở đây sau
    }
}