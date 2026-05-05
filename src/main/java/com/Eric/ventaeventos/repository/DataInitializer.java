package com.Eric.ventaeventos.repository;

import com.Eric.ventaeventos.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * DataInitializer
 *
 * Esta clase se encarga de crear datos de prueba
 * para probar todas las entidades del proyecto.
 */
public class DataInitializer {

    private static DataInitializer instancia;

    private List<Usuario> usuarios = new ArrayList<>();
    private List<Evento> eventos = new ArrayList<>();
    private List<Recinto> recintos = new ArrayList<>();

    // Constructor privado (para que sea Singleton)
    private DataInitializer() {
        cargarDatosIniciales();
    }

    // Método para obtener la única instancia
    public static DataInitializer getInstance() {
        if (instancia == null) {
            instancia = new DataInitializer();
        }
        return instancia;
    }

    private void cargarDatosIniciales() {

        // ==================== USUARIOS ====================
        Usuario u1 = new Usuario("USR001", "Eric Santiago", "eric.santiago@gmail.com", "3001234567");
        u1.agregarMetodoDePago("Tarjeta Visa ****4321");
        usuarios.add(u1);

        Usuario u2 = new Usuario("USR002", "Maria Lopez", "maria.lopez@hotmail.com", "3109876543");
        usuarios.add(u2);

        // ==================== RECINTOS ====================
        Recinto estadio = new Recinto("REC001", "Estadio Centenario", "Av. Siempre Viva", "Armenia");
        recintos.add(estadio);

        // ==================== ZONAS ====================
        Zona vip = new Zona("Z001", "VIP", 200, 250000);
        Zona preferencial = new Zona("Z002", "Preferencial", 800, 150000);
        Zona general = new Zona("Z003", "General", 3000, 80000);

        estadio.agregarZona(vip);
        estadio.agregarZona(preferencial);
        estadio.agregarZona(general);

        // ==================== EVENTOS ====================
        Evento e1 = new Evento("EV001", "Concierto Juanes", "Concierto",
                "Armenia", LocalDateTime.of(2026, 6, 15, 20, 0), estadio);
        e1.publicar();
        eventos.add(e1);

        Evento e2 = new Evento("EV002", "Obra de Teatro Hamlet", "Teatro",
                "Armenia", LocalDateTime.of(2026, 5, 20, 19, 0), estadio);
        e2.publicar();
        eventos.add(e2);

        System.out.println("✅ Datos de prueba cargados correctamente!");
        System.out.println("Eventos creados: " + eventos.size());
        System.out.println("Usuarios creados: " + usuarios.size());
    }

    // Getters para acceder a los datos
    public List<Usuario> getUsuarios() { return usuarios; }
    public List<Evento> getEventos() { return eventos; }
    public List<Recinto> getRecintos() { return recintos; }

    public void mostrarDatos() {
        System.out.println("\n=== DATOS DE PRUEBA DEL SISTEMA ===");
        for (Evento e : eventos) {
            System.out.println(e);
        }
    }
}