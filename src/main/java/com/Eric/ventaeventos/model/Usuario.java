package com.Eric.ventaeventos.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Usuario
 *
 * Esta clase representa a cada persona que usa la plataforma.
 * Es la que se registra, inicia sesión y compra entradas.
 */
public class Usuario {

    // Atributos que pide el PDF
    private String idUsuario;
    private String nombreCompleto;
    private String correoElectronico;
    private String numeroTelefono;

    // Métodos de pago simulados (RF-021)
    private List<String> metodosDePago;

    // Constructor vacío
    public Usuario() {
        this.metodosDePago = new ArrayList<>();
    }

    // Constructor con los datos principales
    public Usuario(String idUsuario, String nombreCompleto,
                   String correoElectronico, String numeroTelefono) {
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = correoElectronico;
        this.numeroTelefono = numeroTelefono;
        this.metodosDePago = new ArrayList<>();
    }

    // Getters y Setters (los necesito para las pantallas de JavaFX)
    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public String getNumeroTelefono() { return numeroTelefono; }
    public void setNumeroTelefono(String numeroTelefono) { this.numeroTelefono = numeroTelefono; }

    public List<String> getMetodosDePago() { return metodosDePago; }

    // Métodos importantes
    public void agregarMetodoDePago(String metodo) {
        this.metodosDePago.add(metodo);
    }

    public void registrarCompra(Compra compra) {
        System.out.println("Compra registrada para: " + nombreCompleto);
    }

    @Override
    public String toString() {
        return "Usuario: " + nombreCompleto + " (" + correoElectronico + ")";
    }
}