module com.stempien.kantor {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.stempien.kantor to javafx.fxml;
    exports com.stempien.kantor;
}