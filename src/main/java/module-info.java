module com.currency.converter {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;

    opens com.currency.converter to javafx.fxml;
    exports com.currency.converter;
    exports com.currency.converter.controller;
    opens com.currency.converter.controller to javafx.fxml;
}
