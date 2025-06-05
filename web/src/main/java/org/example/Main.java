package org.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import org.example.utils.DatabaseConnection;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Kiểm tra kết nối database trước khi khởi tạo UI
        if (!DatabaseConnection.testConnection()) {
            showAlertAndExit("Lỗi nghiêm trọng", "Không thể kết nối database",
                    "Ứng dụng sẽ tự đóng. Vui lòng kiểm tra:\n"
                            + "1. File database qlbanhang.db có tồn tại\n"
                            + "2. Có quyền truy cập file database");
            return;
        }

        try {
            // Tạo giao diện tab
            TabPane tabPane = new TabPane();

            // Tab quản lý nhân viên
            Tab nhanVienTab = new Tab("Nhân viên");
            nhanVienTab.setContent(FXMLLoader.load(getClass().getResource("/fxml/QuanLyNhanVien.fxml")));
            nhanVienTab.setClosable(false);

            // Tab quản lý khách hàng
            Tab khachHangTab = new Tab("Khách hàng");
            khachHangTab.setContent(FXMLLoader.load(getClass().getResource("/fxml/QuanLyKhachHang.fxml")));
            khachHangTab.setClosable(false);

            tabPane.getTabs().addAll(nhanVienTab, khachHangTab);

            Scene scene = new Scene(tabPane, 900, 600);
            scene.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());

            primaryStage.setTitle("Quản Lý Bán Hàng");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlertAndExit("Lỗi khởi động", "Không thể tải giao diện",
                    e.getMessage());
        }
    }

    private void showAlertAndExit(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
        Platform.exit();
        System.exit(1);
    }

    public static void main(String[] args) {
        launch(args);
    }
}