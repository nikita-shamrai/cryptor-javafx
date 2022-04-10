module com.example.cryptorjfxv2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cryptorjfxv2 to javafx.fxml;
    exports com.example.cryptorjfxv2;
}