module org.example.app {
    // JavaFX modules
    requires javafx.controls;
    requires javafx.fxml;

    // Database modules
    requires java.sql;
    requires com.zaxxer.hikari;
    requires org.xerial.sqlitejdbc;

    // Logging modules
    requires org.slf4j;

    // Open packages for FXML loading and reflection
    opens org.example to javafx.fxml;
    opens org.example.controllers to javafx.fxml;
    opens org.example.models to javafx.base;

    // Export packages
    exports org.example;
    exports org.example.controllers;
    exports org.example.models;
    exports org.example.repositories;
    exports org.example.services;
    exports org.example.utils;
}