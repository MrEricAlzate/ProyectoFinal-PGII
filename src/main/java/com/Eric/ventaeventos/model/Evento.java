package com.Eric.ventaeventos.model;

import java.time.LocalDateTime;

/**
 * Clase Evento
 *
 * Esta clase representa cada evento de la plataforma (conciertos, teatro, conferencias...).
 * Es una de las más importantes del proyecto.
 */
public class Evento {

    // Atributos que pide el PDF (página 2)
    private String idEvento;
    private String nombre;
    private String categoria;           // Ej: Concierto, Teatro, Conferencia
    private String descripcion;
    private String ciudad;
    private LocalDateTime fechaHora;
    private String estado;              // Borrador, Publicado, Pausado, Cancelado, Finalizado

    private Recinto recinto;            // El lugar donde se hace el evento

    // Políticas del evento (cancelación y reembolso)
    private String politicaCancelacion;
    private String politicaReembolso;

    // Constructor vacío
    public Evento() {
        this.estado = "Borrador";       // Estado inicial que pide el PDF
    }

    // Constructor más usado
    public Evento(String idEvento, String nombre, String categoria,
                  String ciudad, LocalDateTime fechaHora, Recinto recinto) {
        this();
        this.idEvento = idEvento;
        this.nombre = nombre;
        this.categoria = categoria;
        this.ciudad = ciudad;
        this.fechaHora = fechaHora;
        this.recinto = recinto;
    }

    // Getters y Setters (los necesito para JavaFX después)
    public String getIdEvento() { return idEvento; }
    public void setIdEvento(String idEvento) { this.idEvento = idEvento; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Recinto getRecinto() { return recinto; }
    public void setRecinto(Recinto recinto) { this.recinto = recinto; }

    // Métodos importantes según el PDF
    public void publicar() {
        this.estado = "Publicado";
        System.out.println("Evento publicado: " + nombre);
    }

    public void pausar() {
        this.estado = "Pausado";
        System.out.println("Evento pausado: " + nombre);
    }

    public void cancelar() {
        this.estado = "Cancelado";
        System.out.println("Evento cancelado: " + nombre);
    }

    @Override
    public String toString() {
        return "Evento: " + nombre + " - " + fechaHora + " (" + estado + ")";
    }
}