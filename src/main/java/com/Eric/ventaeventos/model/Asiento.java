package com.Eric.ventaeventos.model;

/**
 * Clase Asiento
 *
 * Representa cada silla numerada dentro de una zona
 * (solo se usa cuando la zona tiene asientos específicos)
 */
public class Asiento {

    private String idAsiento;
    private String fila;
    private String numero;
    private String estado;        // Disponible, Reservado, Vendido, Bloqueado

    public Asiento() {
        this.estado = "Disponible";   // Estado inicial
    }

    public Asiento(String idAsiento, String fila, String numero) {
        this();
        this.idAsiento = idAsiento;
        this.fila = fila;
        this.numero = numero;
    }

    // Getters y Setters
    public String getIdAsiento() { return idAsiento; }
    public void setIdAsiento(String idAsiento) { this.idAsiento = idAsiento; }

    public String getFila() { return fila; }
    public void setFila(String fila) { this.fila = fila; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    // Métodos útiles según el PDF (RF-032)
    public void reservar() {
        this.estado = "Reservado";
    }

    public void vender() {
        this.estado = "Vendido";
    }

    public void liberar() {
        this.estado = "Disponible";
    }

    @Override
    public String toString() {
        return "Asiento " + fila + numero + " (" + estado + ")";
    }
}