package com.Eric.ventaeventos.model;

import java.util.ArrayList;
import java.util.List;

/**
  ================================================
  CLASE USUARIO - Entidad principal del proyecto
  ================================================
  Esta clase representa al "Usuario final" de la plataforma.
  Cumple exactamente con los requerimientos del PDF:
  - RF-001   Registrarse e iniciar sesión
  - RF-002   Gestionar perfil (nombre, correo, teléfono)
  - RF-020   Registrarse, iniciar sesión y modificar datos
  - RF-021   Gestionar métodos de pago simulados
  - RF-022   Consultar compras asociadas
   Esta clase SOLO se encarga de representar y gestionar los datos de un usuario.
 */
public class Usuario {

    // ================================================
    // ATRIBUTOS
    // ================================================
    private String idUsuario;           // Identificador único (ej: "USR-001")
    private String nombreCompleto;      // Nombre y apellido del usuario
    private String correoElectronico;   // Correo para login y notificaciones
    private String numeroTelefono;      // Teléfono para contacto

    // Métodos de pago simulados
    // Ejemplos: "Tarjeta Visa ****1234", "Nequi", "PayPal", etc.
    private List<String> metodosDePago;

    // ================================================
    // CONSTRUCTORES
    // ================================================

    /**
     * Constructor vacío (obligatorio para JavaFX y futuras mejoras)
     */
    public Usuario() {
        this.metodosDePago = new ArrayList<>();   // Inicializamos la lista vacía
    }

    /**
     * Constructor completo - Se usa cuando creamos un usuario nuevo
     */
    public Usuario(String idUsuario, String nombreCompleto,
                   String correoElectronico, String numeroTelefono) {
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = correoElectronico;
        this.numeroTelefono = numeroTelefono;
        this.metodosDePago = new ArrayList<>();
    }

    // ================================================
    // GETTERS Y SETTERS (muy importantes para JavaFX)
    // ================================================

    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public String getNumeroTelefono() { return numeroTelefono; }
    public void setNumeroTelefono(String numeroTelefono) { this.numeroTelefono = numeroTelefono; }

    public List<String> getMetodosDePago() { return metodosDePago; }

    // ================================================
    // MÉTODOS DE NEGOCIO (según los RF del PDF)
    // ================================================

    /**
     * RF-021 - Agregar un método de pago simulado
     */
    public void agregarMetodoDePago(String metodo) {
        this.metodosDePago.add(metodo);
        System.out.println("Método de pago agregado: " + metodo);
    }

    /**
     * RF-022 - Registrar una compra al historial del usuario
     * (Este método lo conectaremos más adelante con la clase Compra)
     */
    public void registrarCompra(Compra compra) {
        // Por ahora solo mostramos un mensaje (luego lo mejoraremos)
        System.out.println("✅ Compra registrada para el usuario: " + nombreCompleto);
    }

    /**
     * Método toString() para poder imprimir fácilmente el usuario
     * Muy útil para pruebas y para mostrar en tablas de JavaFX
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario='" + idUsuario + '\'' +
                ", nombre='" + nombreCompleto + '\'' +
                ", correo='" + correoElectronico + '\'' +
                ", teléfono='" + numeroTelefono + '\'' +
                ", métodos de pago=" + metodosDePago.size() +
                '}';
    }
}