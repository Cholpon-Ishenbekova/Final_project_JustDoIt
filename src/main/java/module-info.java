module com.example.justtodoit {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.justtodoit to javafx.fxml;
    exports com.example.justtodoit;
}