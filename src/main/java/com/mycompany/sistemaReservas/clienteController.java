package com.mycompany.sistemaReservas;

import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class clienteController {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDni;
    @FXML
    private Label lblMensaje;

    @FXML
    private void guardar() {
        if (txtNombre.getText().isEmpty()) {
            lblMensaje.setText("Error. Ingrese un nombre.");
        } else {
            try {
                int dni = Integer.parseInt(txtDni.getText());
                if (dni <= 0) {
                    lblMensaje.setText("Error. Ingrese un dni valido.");
                } else {
                    ClienteArchivo archivo = new ClienteArchivo();   // el que lee y guarda el archivo
                    ArrayList<Cliente> clientes = archivo.leer();    // a. leo los que ya había
                    Cliente nuevo = new Cliente(txtNombre.getText(), dni);               // b. el cliente nuevo (nombre y dni)
                    clientes.add(nuevo);                             // c. lo agrego a la lista
                    archivo.guardar(clientes);                       // d. guardo la lista completa
                    lblMensaje.setText("Cliente guardado correctamente");
                    txtNombre.setText("");
                    txtDni.setText("");
                }

            } catch (NumberFormatException e) {
                lblMensaje.setText("Error. Ingrese un dni valido.");
            }
        }

    }

    @FXML
    private void cerrar() {  
        Stage ventana = (Stage) txtNombre.getScene().getWindow();   // te devuelve el nombre del stage
        ventana.close();                                           // cierra solo esa ventana
    }

}
