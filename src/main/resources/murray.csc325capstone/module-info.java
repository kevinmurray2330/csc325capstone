module murray.csc325capstone {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens murray.csc325capstone to javafx.fxml;
    exports murray.csc325capstone;
}