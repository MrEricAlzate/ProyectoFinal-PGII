package com.Eric.ventaeventos.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase Compra
 *
 * Esta clase representa cada vez que un usuario compra entradas.
 * Es una de las clases más importantes del proyecto.
 */
public class Compra {

    // Atributos según el PDF
    private String idCompra;
    private Usuario usuario;
    private Evento evento;
    private LocalDateTime fechaCreacion;
    private double total;
    private String estado;                    // Creada, Pagada, Confirmada, Cancelada...

    private List<Entrada> entradas;
    private List<String> serviciosAdicionales;   // VIP, seguro, merchandising, etc.

    // Constructor
    public Compra() {
        this.fechaCreacion = LocalDateTime.now();
        this.entradas = new ArrayList<>();
        this.serviciosAdicionales = new ArrayList<>();
        this.estado = "Creada";
    }

    public Compra(Usuario usuario, Evento evento) {
        this();
        this.usuario = usuario;
        this.evento = evento;
    }

    // Getters y Setters
    public String getIdCompra() { return idCompra; }
    public void setIdCompra(String idCompra) { this.idCompra = idCompra; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Evento getEvento() { return evento; }
    public void setEvento(Evento evento) { this.evento = evento; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public List<Entrada> getEntradas() { return entradas; }
    public List<String> getServiciosAdicionales() { return serviciosAdicionales; }

    // Métodos principales
    public void agregarServicioAdicional(String servicio) {
        this.serviciosAdicionales.add(servicio);
    }

    public void pagar() {
        this.estado = "Pagada";
        System.out.println("Compra pagada por $" + total);
    }

    public void cancelarCompra() {
        this.estado = "Cancelada";
        System.out.println("Compra cancelada");
    }

    @Override
    public String toString() {
        return "Compra " + idCompra + " - " + estado + " (Total: $" + total + ")";
    }
}