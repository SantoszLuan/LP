module org.example.calculomedia {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;

    opens org.example to javafx.fxml, javafx.graphics;
    exports org.example.calculomedia;
}