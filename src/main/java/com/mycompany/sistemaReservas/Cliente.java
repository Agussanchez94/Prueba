package com.mycompany.sistemaReservas;

import java.io.Serializable;

public class Cliente implements Serializable{
    private static final long serialVersionUID = 1L;
    private String nombre;
    private int dni;

    public Cliente(String nombre, int dni) {
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDni() {
        return dni;
    }

    @Override
    public String toString() {
        return nombre + " "+ dni;
    }
    
    
}
