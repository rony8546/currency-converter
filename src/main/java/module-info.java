module com.currency.converter {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.currency.converter to javafx.fxml;
    exports com.currency.converter;
}
