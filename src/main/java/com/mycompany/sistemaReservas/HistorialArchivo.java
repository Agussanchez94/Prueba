package com.mycompany.sistemaReservas;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class HistorialArchivo {

    public ArrayList<Reserva> leer() {
        ArrayList<Reserva> leido = new ArrayList<>();
        File archivo = new File("historial.dat");	// representa el archivo
        if (archivo.exists()) {			// solo leo si el archivo existe
            ObjectInputStream ois = null;	// afuera del try para poder cerrarlo en el finally
            // variable donde guardo lo que leo

            try {
                FileInputStream fis = new FileInputStream("historial.dat");  // camino hasta el archivo
                ois = new ObjectInputStream(fis);	// arma el objeto a partir de los bytes
                leido = (ArrayList<Reserva>) ois.readObject();	// lee y convierte al tipo que guardé

                // acá uso lo leído: txt.setText(...), tabla.getItems().setAll(...), etc.
            } catch (FileNotFoundException e) {
                System.out.println("Error al leer: " + e.getMessage());// no encuentra el archivo
            } catch (IOException | ClassNotFoundException e) {	 // error al leer el archivo
                System.out.println("Error al leer: " + e.getMessage());
            } finally {
                try {
                    if (ois != null) {			// solo cierro si se llegó a abrir
                        ois.close();			// libera el archivo
                    }
                } catch (IOException e) {
                    System.out.println("Error al cerrar: " + e.getMessage());// error al cerrar
                }
            }
        }
        return leido;

    }

    public void guardar(ArrayList<Reserva> lista) {

        ObjectOutputStream oos = null;                      // afuera del try para poder cerrarlo en el finally
        try {
            FileOutputStream fos = new FileOutputStream("historial.dat");  // camino al archivo (lo crea o lo pisa)
            oos = new ObjectOutputStream(fos);                          // convierte el objeto en bytes

            oos.writeObject(lista);                                  // escribe el objeto en el archivo

        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());// error al guardar
        } finally {
            try {
                if (oos != null) {                          // solo cierro si se llegó a abrir
                    oos.close();                            // cierra y asegura que se guardó
                }
            } catch (IOException e) {
                System.out.println("Error al cerrar: " + e.getMessage()); // error al cerrar
            }
        }

    }
}
