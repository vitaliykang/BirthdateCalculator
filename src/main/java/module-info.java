module com.example.birthdatecalculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.birthdatecalculator to javafx.fxml;
    exports com.example.birthdatecalculator;
}