package com.mycompany.sistemaReservas;

import java.io.Serializable;

public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    private Habitacion habitacion;
    private Cliente cliente;
    private String dia;

    public Reserva(Habitacion habitacion, Cliente cliente, String dia) {
        this.habitacion = habitacion;
        this.cliente = cliente;
        this.dia = dia;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public String getDia() {
        return dia;
    }

}
