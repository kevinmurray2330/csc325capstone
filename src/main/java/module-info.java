module aydin.demo02042025 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens aydin.demo02042025 to javafx.fxml;
    exports aydin.demo02042025;
}