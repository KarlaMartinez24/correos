module org.kyane {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.kyane to javafx.fxml;
    exports org.kyane;
}