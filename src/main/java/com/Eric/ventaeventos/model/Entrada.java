package com.Eric.ventaeventos.model;

/**
 * Clase Entrada
 *
 * Esta clase representa cada ticket que se vende.
 * Una compra puede tener varias entradas.
 */
public class Entrada {

    private String idEntrada;
    private Zona zona;              // Zona donde está la entrada (VIP, Preferencial, etc.)
    private Asiento asiento;        // Puede ser null si la zona no tiene asientos numerados
    private double precioFinal;
    private String estado;          // Activa, Usada, Anulada

    // Constructor vacío
    public Entrada() {
        this.estado = "Activa";
    }

    // Constructor principal
    public Entrada(Zona zona, double precioFinal) {
        this();
        this.zona = zona;
        this.precioFinal = precioFinal;
    }

    // Getters y Setters
    public String getIdEntrada() { return idEntrada; }
    public void setIdEntrada(String idEntrada) { this.idEntrada = idEntrada; }

    public Zona getZona() { return zona; }
    public void setZona(Zona zona) { this.zona = zona; }

    public Asiento getAsiento() { return asiento; }
    public void setAsiento(Asiento asiento) { this.asiento = asiento; }

    public double getPrecioFinal() { return precioFinal; }
    public void setPrecioFinal(double precioFinal) { this.precioFinal = precioFinal; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "Entrada " + idEntrada + " - " + (zona != null ? zona.getNombre() : "") + " ($" + precioFinal + ")";
    }
}