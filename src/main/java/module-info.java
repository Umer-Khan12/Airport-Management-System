module com.example.cmpt370group41 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.cmpt370group41 to javafx.fxml;
    exports com.example.cmpt370group41;
}