package com.Eric.ventaeventos.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Zona
 *
 * Representa un sector dentro del recinto (VIP, Preferencial, General, etc.)
 */
public class Zona {

    private String idZona;
    private String nombre;           // VIP, Preferencial, General, Oriental, etc.
    private int capacidad;
    private double precioBase;

    // Una zona puede tener muchos asientos (si son numerados)
    private List<Asiento> asientos;

    public Zona() {
        this.asientos = new ArrayList<>();
    }

    public Zona(String idZona, String nombre, int capacidad, double precioBase) {
        this();
        this.idZona = idZona;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.precioBase = precioBase;
    }

    // Getters y Setters
    public String getIdZona() { return idZona; }
    public void setIdZona(String idZona) { this.idZona = idZona; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public double getPrecioBase() { return precioBase; }
    public void setPrecioBase(double precioBase) { this.precioBase = precioBase; }

    public List<Asiento> getAsientos() { return asientos; }

    // Métodos útiles
    public void agregarAsiento(Asiento asiento) {
        this.asientos.add(asiento);
    }

    public int getAsientosDisponibles() {
        // Más adelante implementaremos la lógica real
        return capacidad - asientos.size();
    }

    @Override
    public String toString() {
        return nombre + " (Capacidad: " + capacidad + ", Precio: $" + precioBase + ")";
    }
}