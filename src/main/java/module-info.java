module com.mycompany.sistemareservas {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.sistemaReservas to javafx.fxml;
    exports com.mycompany.sistemaReservas;
}
