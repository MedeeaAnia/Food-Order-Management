module com.example.pt2022_30424_iaz_ania_assigment4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.fasterxml.jackson.databind;

    opens com.example.pt2022_30424_iaz_ania_assigment4 to javafx.fxml;
    exports com.example.pt2022_30424_iaz_ania_assigment4;
    exports com.example.pt2022_30424_iaz_ania_assigment4.presentationLayer;
    exports com.example.pt2022_30424_iaz_ania_assigment4.model;
    opens com.example.pt2022_30424_iaz_ania_assigment4.model to javafx.fxml;
    opens com.example.pt2022_30424_iaz_ania_assigment4.presentationLayer to javafx.fxml;
}