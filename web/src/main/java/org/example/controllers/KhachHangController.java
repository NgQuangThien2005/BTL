package org.example.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.models.KhachHang;
import org.example.services.KhachHangService;
import java.sql.SQLException;

public class KhachHangController {
    @FXML private TableView<KhachHang> khachHangTable;
    @FXML private TableColumn<KhachHang, String> maKhachHangColumn;
    @FXML private TableColumn<KhachHang, String> tenKhachHangColumn;
    @FXML private TableColumn<KhachHang, String> sdtColumn;
    @FXML private TableColumn<KhachHang, String> gioiTinhColumn;
    @FXML private TableColumn<KhachHang, String> diaChiColumn;

    @FXML private TextField maKhachHangField;
    @FXML private TextField tenKhachHangField;
    @FXML private ComboBox<String> gioiTinhComboBox;
    @FXML private TextField sdtField;
    @FXML private TextArea diaChiArea;

    @FXML private Button themButton;
    @FXML private Button suaButton;
    @FXML private Button xoaButton;
    @FXML private Button lamMoiButton;

    private final KhachHangService khachHangService = new KhachHangService();
    private final ObservableList<KhachHang> khachHangList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        setupTableColumns();
        loadKhachHangData();
        setupGenderComboBox();
        setupSelectionListener();
    }

    private void setupTableColumns() {
        maKhachHangColumn.setCellValueFactory(new PropertyValueFactory<>("maKhachHang"));
        tenKhachHangColumn.setCellValueFactory(new PropertyValueFactory<>("tenKhachHang"));
        sdtColumn.setCellValueFactory(new PropertyValueFactory<>("sdt"));
        gioiTinhColumn.setCellValueFactory(new PropertyValueFactory<>("gioiTinh"));
        diaChiColumn.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
    }

    private void loadKhachHangData() {
        try {
            khachHangList.setAll(khachHangService.getAllKhachHang());
            khachHangTable.setItems(khachHangList);
        } catch (SQLException e) {
            showAlert("Lỗi", "Không thể tải dữ liệu khách hàng", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void setupGenderComboBox() {
        gioiTinhComboBox.getItems().addAll("Nam", "Nữ", "Khác");
    }

    private void setupSelectionListener() {
        khachHangTable.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldSelection, newSelection) -> {
                    if (newSelection != null) {
                        fillFormWithSelectedKhachHang(newSelection);
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

    private void fillFormWithSelectedKhachHang(KhachHang khachHang) {
        maKhachHangField.setText(khachHang.getMaKhachHang());
        tenKhachHangField.setText(khachHang.getTenKhachHang());
        gioiTinhComboBox.setValue(khachHang.getGioiTinh());
        sdtField.setText(khachHang.getSdt());
        diaChiArea.setText(khachHang.getDiaChi());
    }

    @FXML
    private void handleThemKhachHang() {
        try {
            KhachHang khachHang = getKhachHangFromForm();

            if (khachHang.getMaKhachHang().isEmpty() || khachHang.getTenKhachHang().isEmpty()) {
                showAlert("Cảnh báo", "Thông tin bắt buộc",
                        "Mã khách hàng và Tên khách hàng không được để trống", Alert.AlertType.WARNING);
                return;
            }

            if (khachHangService.isMaKhachHangExists(khachHang.getMaKhachHang())) {
                showAlert("Cảnh báo", "Mã khách hàng đã tồn tại",
                        "Vui lòng chọn mã khách hàng khác", Alert.AlertType.WARNING);
                return;
            }

            khachHangService.addKhachHang(khachHang);
            khachHangList.add(khachHang);
            clearForm();
            showAlert("Thành công", "Thêm khách hàng thành công",
                    "Khách hàng đã được thêm vào hệ thống", Alert.AlertType.INFORMATION);
        } catch (SQLException e) {
            showAlert("Lỗi", "Không thể thêm khách hàng", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleSuaKhachHang() {
        KhachHang selectedKhachHang = khachHangTable.getSelectionModel().getSelectedItem();
        if (selectedKhachHang == null) {
            showAlert("Cảnh báo", "Không có khách hàng được chọn",
                    "Vui lòng chọn một khách hàng để sửa", Alert.AlertType.WARNING);
            return;
        }

        try {
            KhachHang updatedKhachHang = getKhachHangFromForm();
            khachHangService.updateKhachHang(updatedKhachHang);

            // Cập nhật trong ObservableList
            int selectedIndex = khachHangTable.getSelectionModel().getSelectedIndex();
            khachHangList.set(selectedIndex, updatedKhachHang);

            showAlert("Thành công", "Cập nhật khách hàng thành công",
                    "Thông tin khách hàng đã được cập nhật", Alert.AlertType.INFORMATION);
        } catch (SQLException e) {
            showAlert("Lỗi", "Không thể cập nhật khách hàng", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleXoaKhachHang() {
        KhachHang selectedKhachHang = khachHangTable.getSelectionModel().getSelectedItem();
        if (selectedKhachHang == null) {
            showAlert("Cảnh báo", "Không có khách hàng được chọn",
                    "Vui lòng chọn một khách hàng để xóa", Alert.AlertType.WARNING);
            return;
        }

        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Xác nhận xóa");
        confirmAlert.setHeaderText("Bạn có chắc chắn muốn xóa khách hàng này?");
        confirmAlert.setContentText(selectedKhachHang.getTenKhachHang() + " (" + selectedKhachHang.getMaKhachHang() + ")");

        if (confirmAlert.showAndWait().get() == ButtonType.OK) {
            try {
                khachHangService.deleteKhachHang(selectedKhachHang.getMaKhachHang());
                khachHangList.remove(selectedKhachHang);
                clearForm();
                showAlert("Thành công", "Xóa khách hàng thành công",
                        "Khách hàng đã được xóa khỏi hệ thống", Alert.AlertType.INFORMATION);
            } catch (SQLException e) {
                showAlert("Lỗi", "Không thể xóa khách hàng", e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    private void handleLamMoi() {
        clearForm();
        khachHangTable.getSelectionModel().clearSelection();
    }

    private KhachHang getKhachHangFromForm() {
        return new KhachHang(
                maKhachHangField.getText(),
                tenKhachHangField.getText(),
                sdtField.getText(),
                gioiTinhComboBox.getValue(),
                diaChiArea.getText()
        );
    }

    private void clearForm() {
        maKhachHangField.clear();
        tenKhachHangField.clear();
        gioiTinhComboBox.getSelectionModel().clearSelection();
        sdtField.clear();
        diaChiArea.clear();
    }

    private void showAlert(String title, String header, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}