package com.mycompany.sistemaReservas;

import java.io.Serializable;

public class Habitacion implements Serializable{
    private static final long serialVersionUID = 1L;
    private int numero;
    private String tipo;
    private boolean ocupada;

    public Habitacion(int numero, String tipo, boolean ocupada) {
        this.numero = numero;
        this.tipo = tipo;
        this.ocupada = ocupada;
    }

    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isOcupada() {
        return ocupada;
    }

    public void setOcupada(boolean ocupada) {
        this.ocupada = ocupada;
    }

    @Override
    public String toString() {
        return numero +" "+  tipo;
    }
    
    
}
