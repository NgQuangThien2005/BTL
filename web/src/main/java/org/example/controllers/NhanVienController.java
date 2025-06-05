package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.models.NhanVien;
import org.example.services.NhanVienService;
import java.sql.SQLException;
import java.time.LocalDate;

public class NhanVienController {
    @FXML private TableView<NhanVien> nhanVienTable;
    @FXML private TableColumn<NhanVien, String> maNhanVienColumn;
    @FXML private TableColumn<NhanVien, String> hoVaTenColumn;
    @FXML private TableColumn<NhanVien, String> gioiTinhColumn;
    @FXML private TableColumn<NhanVien, String> sdtColumn;
    @FXML private TableColumn<NhanVien, LocalDate> ngaySinhColumn;
    @FXML private TableColumn<NhanVien, String> diaChiColumn;
    @FXML private TableColumn<NhanVien, String> maChucVuColumn;

    @FXML private TextField maNhanVienField;
    @FXML private TextField hoVaTenField;
    @FXML private ComboBox<String> gioiTinhComboBox;
    @FXML private TextField sdtField;
    @FXML private DatePicker ngaySinhPicker;
    @FXML private TextArea diaChiArea;
    @FXML private TextField maChucVuField;

    @FXML private Button themButton;
    @FXML private Button suaButton;
    @FXML private Button xoaButton;
    @FXML private Button lamMoiButton;

    private final NhanVienService nhanVienService = new NhanVienService();
    private final ObservableList<NhanVien> nhanVienList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTableColumns();
        loadNhanVienData();
        setupGenderComboBox();
        setupSelectionListener();
    }

    private void setupTableColumns() {
        maNhanVienColumn.setCellValueFactory(new PropertyValueFactory<>("maNhanVien"));
        hoVaTenColumn.setCellValueFactory(new PropertyValueFactory<>("hoVaTen"));
        gioiTinhColumn.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        sdtColumn.setCellValueFactory(new PropertyValueFactory<>("sdt"));
        ngaySinhColumn.setCellValueFactory(new PropertyValueFactory<>("ngaySinh"));
        diaChiColumn.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        maChucVuColumn.setCellValueFactory(new PropertyValueFactory<>("maChucVu"));
    }

    private void loadNhanVienData() {
        try {
            nhanVienList.setAll(nhanVienService.getAllNhanVien());
            nhanVienTable.setItems(nhanVienList);
        } catch (SQLException e) {
            showAlert("Lỗi", "Không thể tải dữ liệu nhân viên", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void setupGenderComboBox() {
        gioiTinhComboBox.getItems().addAll("Nam", "Nữ", "Khác");
    }

    private void setupSelectionListener() {
        nhanVienTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        fillFormWithSelectedNhanVien(newSelection);
                        themButton.setDisable(true);
                        suaButton.setDisable(false);
                        xoaButton.setDisable(false);
                    } else {
                        clearForm();
                        themButton.setDisable(false);
                        suaButton.setDisable(true);
                        xoaButton.setDisable(true);
                    }
                });
    }

    private void fillFormWithSelectedNhanVien(NhanVien nhanVien) {
        maNhanVienField.setText(nhanVien.getMaNhanVien());
        hoVaTenField.setText(nhanVien.getHoVaTen());
        gioiTinhComboBox.setValue(nhanVien.getGioiTinh());
        sdtField.setText(nhanVien.getSdt());
        ngaySinhPicker.setValue(nhanVien.getNgaySinh());
        diaChiArea.setText(nhanVien.getDiaChi());
        maChucVuField.setText(nhanVien.getMaChucVu());
    }

    @FXML
    private void handleThemNhanVien() {
        try {
            NhanVien nhanVien = getNhanVienFromForm();

            if (nhanVien.getMaNhanVien().isEmpty() || nhanVien.getHoVaTen().isEmpty()) {
                showAlert("Cảnh báo", "Thông tin bắt buộc",
                        "Mã nhân viên và Họ tên không được để trống", Alert.AlertType.WARNING);
                return;
            }

            if (nhanVienService.isMaNhanVienExists(nhanVien.getMaNhanVien())) {
                showAlert("Cảnh báo", "Mã nhân viên đã tồn tại",
                        "Vui lòng chọn mã nhân viên khác", Alert.AlertType.WARNING);
                return;
            }

            nhanVienService.addNhanVien(nhanVien);
            nhanVienList.add(nhanVien);
            clearForm();
            showAlert("Thành công", "Thêm nhân viên thành công",
                    "Nhân viên đã được thêm vào hệ thống", Alert.AlertType.INFORMATION);
        } catch (SQLException e) {
            showAlert("Lỗi", "Không thể thêm nhân viên", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleSuaNhanVien() {
        NhanVien selectedNhanVien = nhanVienTable.getSelectionModel().getSelectedItem();
        if (selectedNhanVien == null) {
            showAlert("Cảnh báo", "Không có nhân viên được chọn",
                    "Vui lòng chọn một nhân viên để sửa", Alert.AlertType.WARNING);
            return;
        }

        try {
            NhanVien updatedNhanVien = getNhanVienFromForm();
            nhanVienService.updateNhanVien(updatedNhanVien);

            // Cập nhật trong ObservableList
            int selectedIndex = nhanVienTable.getSelectionModel().getSelectedIndex();
            nhanVienList.set(selectedIndex, updatedNhanVien);

            showAlert("Thành công", "Cập nhật nhân viên thành công",
                    "Thông tin nhân viên đã được cập nhật", Alert.AlertType.INFORMATION);
        } catch (SQLException e) {
            showAlert("Lỗi", "Không thể cập nhật nhân viên", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleXoaNhanVien() {
        NhanVien selectedNhanVien = nhanVienTable.getSelectionModel().getSelectedItem();
        if (selectedNhanVien == null) {
            showAlert("Cảnh báo", "Không có nhân viên được chọn",
                    "Vui lòng chọn một nhân viên để xóa", Alert.AlertType.WARNING);
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Xác nhận xóa");
        confirmAlert.setHeaderText("Bạn có chắc chắn muốn xóa nhân viên này?");
        confirmAlert.setContentText(selectedNhanVien.getHoVaTen() + " (" + selectedNhanVien.getMaNhanVien() + ")");

        if (confirmAlert.showAndWait().get() == ButtonType.OK) {
            try {
                nhanVienService.deleteNhanVien(selectedNhanVien.getMaNhanVien());
                nhanVienList.remove(selectedNhanVien);
                clearForm();
                showAlert("Thành công", "Xóa nhân viên thành công",
                        "Nhân viên đã được xóa khỏi hệ thống", Alert.AlertType.INFORMATION);
            } catch (SQLException e) {
                showAlert("Lỗi", "Không thể xóa nhân viên", e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    private void handleLamMoi() {
        clearForm();
        nhanVienTable.getSelectionModel().clearSelection();
    }

    private NhanVien getNhanVienFromForm() {
        return new NhanVien(
                maNhanVienField.getText(),
                hoVaTenField.getText(),
                gioiTinhComboBox.getValue(),
                sdtField.getText(),
                ngaySinhPicker.getValue(),
                diaChiArea.getText(),
                maChucVuField.getText()
        );
    }

    private void clearForm() {
        maNhanVienField.clear();
        hoVaTenField.clear();
        gioiTinhComboBox.getSelectionModel().clearSelection();
        sdtField.clear();
        ngaySinhPicker.setValue(null);
        diaChiArea.clear();
        maChucVuField.clear();
    }

    private void showAlert(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}