package com.mycompany.sistemaReservas;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class reservasController {

    @FXML
    public void initialize() {
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));
        colOcupada.setCellValueFactory(new PropertyValueFactory<>("ocupada"));
        colHabitacion.setCellValueFactory(new PropertyValueFactory<>("habitacion"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("dia"));

        cargarClientes();
        HabitacionArchivo archivoHab = new HabitacionArchivo();
        ArrayList<Habitacion> habitaciones = archivoHab.leer();     // 1. leo las guardadas

        if (habitaciones.isEmpty()) {                               // 2. si no había ninguna (primera vez)
            habitaciones.add(new Habitacion(1, "Doble", false));// guardar la lista con archivoHab.guardar(habitaciones)
            habitaciones.add(new Habitacion(2, "Triple", false));
            habitaciones.add(new Habitacion(3, "Premium", false));
            habitaciones.add(new Habitacion(4, "Doble", false));
            habitaciones.add(new Habitacion(5, "Triple", false));
            archivoHab.guardar(habitaciones);

        }

        tablaHabitaciones.getItems().setAll(habitaciones);
        HistorialArchivo archivoHist = new HistorialArchivo();
        ArrayList<Reserva> reservas = archivoHist.leer();
        tablaHistorial.getItems().setAll(reservas);
    }
    
    @FXML
    private TableColumn<Habitacion, Number> colNumero;
    @FXML
    private TableColumn<Habitacion, String> colTipo;
    @FXML
    private TableColumn<Habitacion, Boolean> colOcupada;

    @FXML
    private TableColumn<Reserva, Habitacion> colHabitacion;
    @FXML
    private TableColumn<Reserva, Cliente> colCliente;
    @FXML
    private TableColumn<Reserva, String> colFecha;
    @FXML
    private TableView<Habitacion> tablaHabitaciones;
    @FXML
    private TableView<Reserva> tablaHistorial;
    @FXML
    private ComboBox<Cliente> cbxCliente;
    @FXML
    private TextField txtFecha;
    @FXML
    private Label lblMensaje;

    @FXML
    private void reservar() {
        Habitacion hab = tablaHabitaciones.getSelectionModel().getSelectedItem();   // la elegida (null si no eligió)

        if (hab == null) {
            lblMensaje.setText("Selecciones una habitacion.");
        } else if (cbxCliente.getValue() == null) {
            lblMensaje.setText("Seleccione un cliente.");
        } else if (txtFecha.getText().isEmpty()) {
            lblMensaje.setText("Ingrese una fecha");
        } else if (hab.isOcupada()) {
            lblMensaje.setText("Habitacion ocupada");
        } else {
            hab.setOcupada(true);
            tablaHabitaciones.refresh();
            guardarHabitaciones();
            lblMensaje.setText("Reserva exitosa");
           
            Reserva reserva=new Reserva(hab, cbxCliente.getValue(), txtFecha.getText());                          // 1. crear la Reserva con: la habitación elegida (hab), el cliente del combo y el texto de txtFecha
            tablaHistorial.getItems().add(reserva);                          // 2. agregarla a tablaHistorial.getItems()      (el mismo add de siempre)
            guardarHistorial();                          // 3. guardarHistorial();
        }       
    }
    private void guardarHistorial() {
        
    ArrayList<Reserva> lista = new ArrayList<>();
    for(int i=0;i<tablaHistorial.getItems().size();i++){
        lista.add(tablaHistorial.getItems().get(i));
    }
    HistorialArchivo archivo = new HistorialArchivo();
    archivo.guardar(lista);
    
}

    @FXML
    private void liberar() {
        Habitacion hab = tablaHabitaciones.getSelectionModel().getSelectedItem();   // la elegida (null si no eligió)
        if (hab == null) {
            lblMensaje.setText("Selecciones una habitacion.");
        } else {
            hab.setOcupada(false);
            tablaHabitaciones.refresh();
            guardarHabitaciones();
            lblMensaje.setText("Liberada");

        }
    }

    @FXML
    private void nuevoCliente() {
        try {
            Parent cliente = FXMLLoader.load(getClass().getResource("clientes.fxml"));//carga el contenido
            Scene scene = new Scene(cliente);//pone el contenido en una escena
            Stage stage = new Stage(); //crea una ventana vacia
            stage.setScene(scene);//pone el contenido en la ventana vacia
            stage.showAndWait();//muestra la ventana, y espera q que la cierres
            cargarClientes();
        } catch (IOException e) {
            lblMensaje.setText("Error al abrir Nuevo cliente");
        }
    }

    @FXML
    private void eliminarCliente() {

        if (cbxCliente.getSelectionModel().getSelectedItem() == null) {
            lblMensaje.setText("Seleccione cliente a eliminar");
        } else {
            Cliente aEliminar = cbxCliente.getSelectionModel().getSelectedItem();
            cbxCliente.getItems().remove(aEliminar);
            guardarClientes();
        }

    }

    private void guardarClientes() {

        ArrayList<Cliente> clientes = new ArrayList<>();

        clientes.addAll(cbxCliente.getItems());

        ClienteArchivo archivo = new ClienteArchivo();   // el que lee y guarda el archivo
        archivo.guardar(clientes);

    }

    private void cargarClientes() {

        ClienteArchivo archivo = new ClienteArchivo();
        ArrayList<Cliente> clientes = archivo.leer(); // los clientes guardados
        cbxCliente.getItems().setAll(clientes);              // reemplaza las opciones por esa lista
    }

    private void guardarHabitaciones() {
        ArrayList<Habitacion> hab = new ArrayList<>();

        for (int i = 0; i < tablaHabitaciones.getItems().size(); i++) {
            hab.add(tablaHabitaciones.getItems().get(i));
        }
        HabitacionArchivo archivo = new HabitacionArchivo();
        archivo.guardar(hab);

    }


}
