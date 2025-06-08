package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmThemDiaChiKhachHang extends JFrame {
    // Các thành phần giao diện
    public JTextField txtSoNha, txtTenDuong, txtPhuong, txtQuan, txtThanhPho, txtQuocGia;
    private JButton btnLuu, btnHuy;

    public FrmThemDiaChiKhachHang() {
        setTitle("THÊM ĐỊA CHỈ KHÁCH HÀNG");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel chứa form
        JPanel pnForm = new JPanel(new GridLayout(6, 2, 10, 10));
        pnForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Thêm các trường nhập liệu
        pnForm.add(new JLabel("Số nhà:"));
        txtSoNha = new JTextField();
        pnForm.add(txtSoNha);

        pnForm.add(new JLabel("Tên đường:"));
        txtTenDuong = new JTextField();
        pnForm.add(txtTenDuong);

        pnForm.add(new JLabel("Phường/Xã:"));
        txtPhuong = new JTextField();
        pnForm.add(txtPhuong);

        pnForm.add(new JLabel("Quận/Huyện:"));
        txtQuan = new JTextField();
        pnForm.add(txtQuan);

        pnForm.add(new JLabel("Thành phố/Tỉnh:"));
        txtThanhPho = new JTextField();
        pnForm.add(txtThanhPho);

        pnForm.add(new JLabel("Quốc gia:"));
        txtQuocGia = new JTextField();
        pnForm.add(txtQuocGia);

        add(pnForm, BorderLayout.CENTER);

        // Panel chứa nút
        JPanel pnButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        btnLuu = new JButton("Lưu");
        btnLuu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                luuDiaChi();
            }
        });

        btnHuy = new JButton("Hủy");
        btnHuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        pnButton.add(btnLuu);
        pnButton.add(btnHuy);

        add(pnButton, BorderLayout.SOUTH);
    }

    private void luuDiaChi() {
        // Kiểm tra dữ liệu nhập
        if (txtSoNha.getText().isEmpty() || txtTenDuong.getText().isEmpty() ||
                txtPhuong.getText().isEmpty() || txtQuan.getText().isEmpty() ||
                txtThanhPho.getText().isEmpty() || txtQuocGia.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin địa chỉ!");
            return;
        }

        // Đóng form sau khi lưu thành công
        JOptionPane.showMessageDialog(this, "Lưu địa chỉ thành công!");
        dispose();
    }

    // Phương thức xóa dữ liệu trên form
    public void xoaDuLieu() {
        txtSoNha.setText("");
        txtTenDuong.setText("");
        txtPhuong.setText("");
        txtQuan.setText("");
        txtThanhPho.setText("");
        txtQuocGia.setText("");
    }
}