package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

public class FrmXuatHD extends JFrame {
    // Các thành phần giao diện
    private JLabel lblTitle, lblMaHD, lblLoaiHD, lblNgayLap, lblTenKH, lblGTinh, lblDCKH, lblNamSinh, lblsdtkh;
    private JLabel lblMaHD1, lblLoaiHD1, lblNgayLap1, lblTenKH1, lblGTinh1, lblDCKH1, lblNamSinh1, lblsdtkh1;
    private JLabel lblTongTThuoc, lblTongT, lblNguoiBan, lblNguoiMuaHang, lblNguoiMuaHang1;
    private JLabel lblSL;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton btnIn, btnThoat;

    public FrmXuatHD() {
        setTitle("HÓA ĐƠN BÁN THUỐC");
        setSize(800, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        // Khởi tạo các thành phần
        initComponents();

        // Thiết lập sự kiện
        btnIn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                printingHoaDon();
            }
        });

        btnThoat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    private void initComponents() {
        // Tiêu đề hóa đơn
        lblTitle = new JLabel("HÓA ĐƠN BÁN THUỐC", JLabel.CENTER);
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 24));
        lblTitle.setForeground(Color.BLUE);
        lblTitle.setBounds(0, 10, 800, 30);
        add(lblTitle);

        // Thông tin hóa đơn
        JPanel pnInfo = new JPanel();
        pnInfo.setLayout(null);
        pnInfo.setBorder(new TitledBorder("Thông tin hóa đơn"));
        pnInfo.setBounds(20, 50, 750, 200);

        // Các label thông tin
        lblMaHD = new JLabel("Mã hóa đơn:");
        lblMaHD.setBounds(20, 30, 100, 20);

        lblLoaiHD = new JLabel("Loại hóa đơn:");
        lblLoaiHD.setBounds(20, 60, 100, 20);

        lblNgayLap = new JLabel("Ngày lập:");
        lblNgayLap.setBounds(20, 90, 100, 20);

        lblTenKH = new JLabel("Tên KH:");
        lblTenKH.setBounds(20, 120, 100, 20);

        lblGTinh = new JLabel("Giới tính:");
        lblGTinh.setBounds(20, 150, 100, 20);

        // Các label hiển thị giá trị
        lblMaHD1 = new JLabel();
        lblMaHD1.setBounds(130, 30, 200, 20);

        lblLoaiHD1 = new JLabel();
        lblLoaiHD1.setBounds(130, 60, 200, 20);

        lblNgayLap1 = new JLabel();
        lblNgayLap1.setBounds(130, 90, 200, 20);

        lblTenKH1 = new JLabel();
        lblTenKH1.setBounds(130, 120, 200, 20);

        lblGTinh1 = new JLabel();
        lblGTinh1.setBounds(130, 150, 200, 20);

        // Thêm vào panel
        pnInfo.add(lblMaHD);
        pnInfo.add(lblLoaiHD);
        pnInfo.add(lblNgayLap);
        pnInfo.add(lblTenKH);
        pnInfo.add(lblGTinh);
        pnInfo.add(lblMaHD1);
        pnInfo.add(lblLoaiHD1);
        pnInfo.add(lblNgayLap1);
        pnInfo.add(lblTenKH1);
        pnInfo.add(lblGTinh1);

        add(pnInfo);

        // Bảng chi tiết hóa đơn
        String[] columns = {"STT", "Tên thuốc", "Đơn vị", "Đơn giá", "Số lượng", "Giảm giá", "Thành tiền"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Times New Roman", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(new TitledBorder("Chi tiết hóa đơn"));
        scroll.setBounds(20, 270, 750, 200);
        add(scroll);

        // Tổng tiền
        JPanel pnTotal = new JPanel();
        pnTotal.setLayout(null);
        pnTotal.setBorder(new TitledBorder("Tổng thanh toán"));
        pnTotal.setBounds(20, 490, 750, 100);

        lblTongTThuoc = new JLabel("Tổng tiền thuốc:");
        lblTongTThuoc.setBounds(20, 20, 150, 20);

        lblTongT = new JLabel("Tổng thanh toán:");
        lblTongT.setBounds(20, 50, 150, 20);

        lblSL = new JLabel("Số lượng thuốc:");
        lblSL.setBounds(400, 20, 150, 20);

        pnTotal.add(lblTongTThuoc);
        pnTotal.add(lblTongT);
        pnTotal.add(lblSL);

        add(pnTotal);

        // Nút in và thoát
        btnIn = new JButton("In hóa đơn", new ImageIcon("images/print.png"));
        btnIn.setBounds(200, 600, 150, 40);

        btnThoat = new JButton("Thoát", new ImageIcon("images/exit.png"));
        btnThoat.setBounds(400, 600, 150, 40);

        add(btnIn);
        add(btnThoat);
    }

    // Phương thức in hóa đơn
    public void printingHoaDon() {
        try {
            // Tạo nội dung hóa đơn
            StringBuilder content = new StringBuilder();
            content.append("HÓA ĐƠN BÁN THUỐC\n\n");
            content.append("Mã hóa đơn: ").append(lblMaHD1.getText()).append("\n");
            content.append("Ngày lập: ").append(lblNgayLap1.getText()).append("\n");
            content.append("Khách hàng: ").append(lblTenKH1.getText()).append("\n\n");

            // Thêm chi tiết thuốc
            content.append("Chi tiết thuốc:\n");
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                content.append(tableModel.getValueAt(i, 1)).append(" - ")
                        .append(tableModel.getValueAt(i, 3)).append(" x ")
                        .append(tableModel.getValueAt(i, 4)).append(" = ")
                        .append(tableModel.getValueAt(i, 6)).append("\n");
            }

            content.append("\nTổng thanh toán: ").append(lblTongT.getText());

            // Hiển thị hóa đơn (ở đây chỉ in ra console, thực tế có thể in ra máy in)
            System.out.println(content.toString());
            JOptionPane.showMessageDialog(this, "Đã in hóa đơn thành công!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi in hóa đơn: " + e.getMessage());
        }
    }

    // Các phương thức setter để cập nhật thông tin từ form lập hóa đơn
    public void setMaHD(String maHD) {
        lblMaHD1.setText(maHD);
    }

    public void setLoaiHD(String loaiHD) {
        lblLoaiHD1.setText(loaiHD);
    }

    public void setNgayLap(String ngayLap) {
        lblNgayLap1.setText(ngayLap);
    }

    public void setTenKH(String tenKH) {
        lblTenKH1.setText(tenKH);
    }

    public void setGTinh(String gTinh) {
        lblGTinh1.setText(gTinh);
    }

    public void setTongTienThuoc(String tongTien) {
        lblTongTThuoc.setText(tongTien);
    }

    public void setTongThanhToan(String tongThanhToan) {
        lblTongT.setText(tongThanhToan);
    }

    public void setSoLuong(String soLuong) {
        lblSL.setText(soLuong);
    }

    public void addRowToTable(Object[] rowData) {
        tableModel.addRow(rowData);
    }

    public void clearTable() {
        tableModel.setRowCount(0);
    }
}