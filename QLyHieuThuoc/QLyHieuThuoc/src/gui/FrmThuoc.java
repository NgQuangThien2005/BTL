package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import dao.ThuocDAO;
import model.Thuoc;

public class FrmThuoc extends JFrame implements ActionListener {
    public JPanel contentPane;
    public JPanel pnlChucNang;
    private JTextField txtMaThuoc;
    private JTextField txtTenThuoc;
    private JTextField txtDonGia;
    private JTextField txtHamLuong;
    private JTextField txtTimKiem;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnThem;
    private JButton btnLuu;
    private JButton btnXoa;
    private JButton btnSua;
    private JButton btnTimKiem;

    private ThuocDAO thuocDAO = new ThuocDAO();

    public FrmThuoc() {
        setTitle("QUẢN LÝ THUỐC");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1200, 700);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // Panel thông tin thuốc
        JPanel pnlThongTin = new JPanel();
        pnlThongTin.setBorder(new TitledBorder("Thông tin thuốc"));
        contentPane.add(pnlThongTin, BorderLayout.NORTH);
        pnlThongTin.setLayout(null);

        JLabel lblMaThuoc = new JLabel("Mã thuốc:");
        lblMaThuoc.setBounds(20, 30, 80, 20);
        pnlThongTin.add(lblMaThuoc);

        txtMaThuoc = new JTextField();
        txtMaThuoc.setBounds(110, 30, 200, 20);
        txtMaThuoc.setEditable(false);
        pnlThongTin.add(txtMaThuoc);
        txtMaThuoc.setColumns(10);

        JLabel lblTenThuoc = new JLabel("Tên thuốc:");
        lblTenThuoc.setBounds(20, 70, 80, 20);
        pnlThongTin.add(lblTenThuoc);

        txtTenThuoc = new JTextField();
        txtTenThuoc.setBounds(110, 70, 200, 20);
        pnlThongTin.add(txtTenThuoc);
        txtTenThuoc.setColumns(10);

        JLabel lblDonGia = new JLabel("Đơn giá:");
        lblDonGia.setBounds(350, 30, 80, 20);
        pnlThongTin.add(lblDonGia);

        txtDonGia = new JTextField();
        txtDonGia.setBounds(440, 30, 200, 20);
        pnlThongTin.add(txtDonGia);
        txtDonGia.setColumns(10);

        JLabel lblHamLuong = new JLabel("Hàm lượng:");
        lblHamLuong.setBounds(350, 70, 80, 20);
        pnlThongTin.add(lblHamLuong);

        txtHamLuong = new JTextField();
        txtHamLuong.setBounds(440, 70, 200, 20);
        pnlThongTin.add(txtHamLuong);
        txtHamLuong.setColumns(10);

        // Panel chức năng
        pnlChucNang = new JPanel();
        pnlChucNang.setBorder(new TitledBorder("Chức năng"));
        contentPane.add(pnlChucNang, BorderLayout.CENTER);

        btnThem = new JButton("Thêm");
        btnThem.setIcon(new ImageIcon("images/add.png"));
        btnThem.addActionListener(this);
        pnlChucNang.add(btnThem);

        btnLuu = new JButton("Lưu");
        btnLuu.setIcon(new ImageIcon("images/save.png"));
        btnLuu.addActionListener(this);
        pnlChucNang.add(btnLuu);

        btnXoa = new JButton("Xóa");
        btnXoa.setIcon(new ImageIcon("images/delete.png"));
        btnXoa.addActionListener(this);
        pnlChucNang.add(btnXoa);

        btnSua = new JButton("Sửa");
        btnSua.setIcon(new ImageIcon("images/edit.png"));
        btnSua.addActionListener(this);
        pnlChucNang.add(btnSua);

        // Panel tìm kiếm
        JPanel pnlTimKiem = new JPanel();
        pnlTimKiem.setBorder(new TitledBorder("Tìm kiếm"));
        contentPane.add(pnlTimKiem, BorderLayout.SOUTH);

        txtTimKiem = new JTextField();
        txtTimKiem.setColumns(20);
        pnlTimKiem.add(txtTimKiem);

        btnTimKiem = new JButton("Tìm kiếm");
        btnTimKiem.setIcon(new ImageIcon("images/search.png"));
        btnTimKiem.addActionListener(this);
        pnlTimKiem.add(btnTimKiem);

        // Bảng hiển thị danh sách thuốc
        String[] columns = {"Mã thuốc", "Tên thuốc", "Đơn giá", "Hàm lượng", "Đơn vị tính", "Phân loại"};
        tableModel = new DefaultTableModel(columns, 0);

        table = new JTable(tableModel);
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new TitledBorder("Danh sách thuốc"));
        contentPane.add(scrollPane, BorderLayout.CENTER);

        // Load dữ liệu ban đầu
        loadData();
    }

    private void loadData() {
        tableModel.setRowCount(0); // Xóa dữ liệu cũ

        List<Thuoc> listThuoc = ThuocDAO.getThuoc();
        for (Thuoc thuoc : listThuoc) {
            Object[] row = {
                    thuoc.getMaThuoc(),
                    thuoc.getTenThuoc(),
                    thuoc.getDonGia(),
                    thuoc.getHamLuong(),
                    thuoc.getDonViTinh(),
                    thuoc.getPhanLoai()
            };
            tableModel.addRow(row);
        }
    }

    private void clearFields() {
        txtMaThuoc.setText("");
        txtTenThuoc.setText("");
        txtDonGia.setText("");
        txtHamLuong.setText("");
    }

    private void themThuoc() {
        try {
            String tenThuoc = txtTenThuoc.getText();
            double donGia = Double.parseDouble(txtDonGia.getText());
            String hamLuong = txtHamLuong.getText();

            Thuoc thuoc = new Thuoc();
            thuoc.setTenThuoc(tenThuoc);
            thuoc.setDonGia(donGia);
            thuoc.setHamLuong(hamLuong);

            if (ThuocDAO.them(thuoc)) {
                JOptionPane.showMessageDialog(this, "Thêm thuốc thành công!");
                loadData();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thuốc thất bại!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }

    private void suaThuoc() {
        try {
            int maThuoc = Integer.parseInt(txtMaThuoc.getText());
            String tenThuoc = txtTenThuoc.getText();
            double donGia = Double.parseDouble(txtDonGia.getText());
            String hamLuong = txtHamLuong.getText();

            Thuoc thuoc = new Thuoc();
            thuoc.setMaThuoc(maThuoc);
            thuoc.setTenThuoc(tenThuoc);
            thuoc.setDonGia(donGia);
            thuoc.setHamLuong(hamLuong);

            if (thuocDAO.updateThuoc(thuoc)) {
                JOptionPane.showMessageDialog(this, "Cập nhật thuốc thành công!");
                loadData();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật thuốc thất bại!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }

    private void xoaThuoc() {
        try {
            int maThuoc = Integer.parseInt(txtMaThuoc.getText());

            if (ThuocDAO.deleteThuoc(maThuoc)) {
                JOptionPane.showMessageDialog(this, "Xóa thuốc thành công!");
                loadData();
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thuốc thất bại!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
        }
    }

    private void timKiemThuoc() {
        String keyword = txtTimKiem.getText();
        List<Thuoc> listThuoc = ThuocDAO.searchThuoc(keyword);

        tableModel.setRowCount(0);
        for (Thuoc thuoc : listThuoc) {
            Object[] row = {
                    thuoc.getMaThuoc(),
                    thuoc.getTenThuoc(),
                    thuoc.getDonGia(),
                    thuoc.getHamLuong(),
                    thuoc.getDonViTinh(),
                    thuoc.getPhanLoai()
            };
            tableModel.addRow(row);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnThem) {
            themThuoc();
        } else if (e.getSource() == btnLuu) {
            suaThuoc();
        } else if (e.getSource() == btnXoa) {
            xoaThuoc();
        } else if (e.getSource() == btnSua) {
            // Lấy dữ liệu từ bảng để sửa
            int row = table.getSelectedRow();
            if (row >= 0) {
                txtMaThuoc.setText(tableModel.getValueAt(row, 0).toString());
                txtTenThuoc.setText(tableModel.getValueAt(row, 1).toString());
                txtDonGia.setText(tableModel.getValueAt(row, 2).toString());
                txtHamLuong.setText(tableModel.getValueAt(row, 3).toString());
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn thuốc cần sửa!");
            }
        } else if (e.getSource() == btnTimKiem) {
            timKiemThuoc();
        }
    }
}