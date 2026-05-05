package com.Eric.ventaeventos.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Recinto
 *
 * Representa el lugar físico donde se realiza el evento
 * (Estadio, Teatro, Auditorio, etc.)
 */
public class Recinto {

    private String idRecinto;
    private String nombre;
    private String direccion;
    private String ciudad;

    // Un recinto tiene varias zonas
    private List<Zona> zonas;

    public Recinto() {
        this.zonas = new ArrayList<>();
    }

    public Recinto(String idRecinto, String nombre, String direccion, String ciudad) {
        this();
        this.idRecinto = idRecinto;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
    }

    // Getters y Setters
    public String getIdRecinto() { return idRecinto; }
    public void setIdRecinto(String idRecinto) { this.idRecinto = idRecinto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public List<Zona> getZonas() { return zonas; }

    // Método para agregar zonas
    public void agregarZona(Zona zona) {
        this.zonas.add(zona);
    }

    @Override
    public String toString() {
        return "Recinto: " + nombre + " - " + ciudad;
    }
}