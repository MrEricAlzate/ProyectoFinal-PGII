# Plataforma de Gestión de Eventos y Venta de Entradas
### Proyecto Final — Programación II (PGII) | Universidad del Quindío

---

## 👥 Integrantes del grupo

| Nombre completo | GitHub |
|---|---|
| Eric Santiago Correa Alzate | [@MrEricAlzate](https://github.com/MrEricAlzate) |
| [Nombre completo compañero] | [@usuario_compañero](https://github.com/usuario) |

---

## 📋 Descripción del proyecto

Plataforma de gestión de eventos y venta de entradas desarrollada en Java 21 con JavaFX. Permite a los usuarios explorar eventos (conciertos, teatro, conferencias), seleccionar zonas y asientos, comprar entradas y agregar servicios adicionales como acceso VIP o seguro de cancelación. Los administradores pueden gestionar el catálogo de eventos, recintos, zonas, asientos, registrar incidencias y exportar reportes en CSV y PDF.

El proyecto aplica principios **SOLID**, **9 patrones de diseño GOF** y está estructurado en capas con Maven.

---

## ⚙️ Requisitos para ejecutar

- Java 21 o superior
- Maven 3.8+
- IntelliJ IDEA (recomendado) o cualquier IDE con soporte Maven

---

## 🚀 Instrucciones para compilar y ejecutar

### 1. Clonar el repositorio
```bash
git clone https://github.com/MrEricAlzate/ProyectoFinal-PG2.git
cd ProyectoFinal-PG2
```

### 2. Compilar con Maven
```bash
mvn clean compile
```

### 3. Ejecutar la aplicación
```bash
mvn javafx:run
```

### 4. Ejecutar desde IntelliJ IDEA
1. Abrir el proyecto con `File → Open`
2. Esperar que Maven descargue las dependencias
3. Ejecutar la clase `Main.java` con el botón ▶

### Dependencias principales (pom.xml)
- JavaFX 23
- Apache PDFBox 3.0.2 (reportes PDF)
- Apache POI 5.3.0 (reportes Excel)

---

## 🏗️ Estructura del proyecto

```
src/main/java/com/Eric/ventaeventos/
├── model/          → Entidades: Usuario, Evento, Recinto, Zona, Asiento,
│                              Compra, Entrada, Pago, Incidencia
├── repository/     → DataInitializer (Singleton), IUsuarioRepository,
│                     IEventoRepository
├── service/        → Lógica de negocio y patrones de diseño
│   └── strategy/   → CancelacionStrategy, ReembolsoCompleto, ReembolsoParcial
└── view/           → Pantallas JavaFX (usuario y administrador)
```

---

## 🎨 Patrones de diseño implementados

### CREACIONALES

#### 1. Singleton — `DataInitializer`
**Requisito:** RF-049 — Gestión centralizada de datos de prueba y acceso global al sistema.

**Problema:** Si se crean múltiples instancias de DataInitializer, cada módulo tendría su propia lista de usuarios y eventos desincronizada.

**Propósito:** Garantizar una única instancia en toda la aplicación con un punto de acceso global.

**Solución:**
```java
public class DataInitializer implements IUsuarioRepository, IEventoRepository {

    private static DataInitializer instance;

    private DataInitializer() { cargarDatos(); }

    public static DataInitializer getInstance() {
        if (instance == null) {
            instance = new DataInitializer();
        }
        return instance;
    }
}
```

---

#### 2. Factory Method — `EventoFactory`
**Requisito:** RF-013, RF-049 — Crear eventos de distintas categorías con configuraciones específicas.

**Problema:** Cada tipo de evento (concierto, teatro, conferencia) tiene políticas de cancelación distintas. Sin Factory habría código repetido en cada lugar donde se crea un evento.

**Propósito:** Centralizar la creación de eventos con sus valores predeterminados según el tipo.

**Solución:**
```java
public class EventoFactory {

    public static Evento crearConcierto(String id, String nombre,
                                        String ciudad, LocalDateTime fecha,
                                        Recinto recinto) {
        Evento e = new Evento(id, nombre, "Concierto", ciudad, fecha, recinto);
        e.setPoliticaCancelacion("Reembolso 100% con más de 7 días.");
        e.setPoliticaReembolso("Reembolso parcial entre 3 y 7 días.");
        return e;
    }

    public static Evento crearTeatro(...) { ... }
    public static Evento crearConferencia(...) { ... }
}
```

---

#### 3. Builder — `CompraBuilder`
**Requisito:** RF-006, RF-009 — Crear compras con múltiples entradas y servicios adicionales opcionales.

**Problema:** Una Compra puede tener muchos campos opcionales (entradas, asientos, VIP, seguro). Un constructor con todos esos parámetros es ilegible.

**Propósito:** Construir una Compra paso a paso encadenando solo los pasos necesarios.

**Solución:**
```java
Compra compra = new CompraBuilder(usuario, evento)
        .conEntrada(zonaVIP)
        .conAsiento(zonaVIP, asientoA3)
        .conServicioVIP()
        .conSeguroCancelacion()
        .build();
```

---

### ESTRUCTURALES

#### 4. Decorator — `ServicioDecorator` (obligatorio)
**Requisito:** RF-009 — Agregar servicios adicionales a una compra: VIP, seguro, merchandising.

**Problema:** Agregar servicios modificando la clase Compra violaría OCP. Con herencia las combinaciones crecen exponencialmente.

**Propósito:** Extender el comportamiento de una compra en tiempo de ejecución sin modificar las clases existentes.

**Solución:**
```java
// cadena: CompraBase → ServicioVIP → SeguroCancelacion
CompraComponent base = new CompraBase(compra);     // $180.000
CompraComponent vip  = new ServicioVIP(base);      // +$50.000
CompraComponent seg  = new SeguroCancelacion(vip); // +$15.000
// seg.getTotal() = $245.000
// seg.getDescripcion() = "Compra base + VIP + Seguro Cancelación"
```

---

#### 5. Facade — `CompraFacade`
**Requisito:** RF-007, RF-050 — Simplificar el proceso de compra para la pantalla JavaFX.

**Problema:** Realizar una compra requería coordinar Compra, Pago, Entrada, Asiento y DataInitializer desde la pantalla, acoplando la UI con toda la lógica.

**Propósito:** Exponer un método simple que coordine internamente todos los pasos del proceso de compra.

**Solución:**
```java
// La pantalla JavaFX solo llama esto:
CompraFacade facade = new CompraFacade();
Compra c = facade.realizarCompra(usuario, evento, zona, "Nequi");

// Internamente coordina: Builder + Pago + DataInitializer
```

---

#### 6. Adapter — `ReporteCSVAdapter` / `ReportePDFAdapter`
**Requisito:** RF-046, RF-050 — Exportar reportes en CSV y PDF desde una interfaz común.

**Problema:** FileWriter y PDFBox tienen APIs completamente distintas. Sin Adapter el código de reportes quedaría acoplado a una librería específica.

**Propósito:** Definir un contrato único `ReporteExportador` para que la pantalla no dependa de la librería concreta.

**Solución:**
```java
// La pantalla usa la interfaz, no las librerías directamente:
ReporteExportador exportador = new ReporteCSVAdapter();
exportador.exportar(compras, "reporte.csv");

exportador = new ReportePDFAdapter();
exportador.exportar(compras, "reporte.pdf");
```

---

### DE COMPORTAMIENTO

#### 7. Strategy — `CancelacionStrategy` (obligatorio)
**Requisito:** RF-006, RF-036, RF-051 — Cancelar compras con diferentes políticas de reembolso.

**Problema:** Cada evento tiene reglas distintas de devolución. Sin Strategy habría un bloque if/else gigante que violaría OCP.

**Propósito:** Permitir cambiar el algoritmo de cancelación en tiempo de ejecución sin modificar CompraService.

**Solución:**
```java
CompraService servicio = new CompraService();

// política generosa por defecto
servicio.cancelarCompra(compra, 10); // reembolso 100%

// cambiar a política estricta en tiempo de ejecución
servicio.setCancelacionStrategy(new ReembolsoParcialStrategy());
servicio.cancelarCompra(compra, 3);  // reembolso 0%
```

---

#### 8. Observer — `EventoObserver`
**Requisito:** RF-008, RF-017, RF-051 — Notificar a usuarios cuando un evento cambia de estado.

**Problema:** Sin Observer, al cancelar un evento habría que recorrer manualmente todas las compras y notificar a cada usuario desde la clase Evento, acoplándola con la lógica de usuarios.

**Propósito:** Notificar automáticamente a todos los suscritos cuando el estado del evento cambia.

**Solución:**
```java
// suscribir usuarios al evento
evento.agregarObserver(new NotificacionUsuario(eric));
evento.agregarObserver(new NotificacionUsuario(maria));

// al cancelar, ambos reciben notificación automáticamente
evento.cancelar();
// → Notificacion para Eric: el evento 'Concierto Juanes' cambio a estado: Cancelado
// → Notificacion para Maria: el evento 'Concierto Juanes' cambio a estado: Cancelado
```

---

#### 9. Command — `PublicarEventoComando`
**Requisito:** RF-013, RF-017, RF-051 — Acciones del administrador ejecutables y reversibles.

**Problema:** Si el admin publica un evento por error no hay forma de deshacer la acción sin lógica adicional en cada pantalla.

**Propósito:** Encapsular cada acción como un objeto que sabe ejecutarse y deshacerse.

**Solución:**
```java
Comando cmd = new PublicarEventoComando(evento);

cmd.ejecutar();  // evento pasa a Publicado
// ... el admin se da cuenta del error
cmd.deshacer();  // evento vuelve a Borrador
```

---

## 🔷 Principios SOLID aplicados

### S — SRP: Responsabilidad única
**Problema:** La clase `Compra` tenía lógica de cancelación mezclada con los datos.

**Solución:** Se separó la lógica de negocio en `CompraService`. `Compra` solo guarda datos; `CompraService` decide qué se puede hacer con esos datos.

```java
// Compra.java → solo datos
public class Compra {
    private String estado;
    private double totalBase;
    private List<Entrada> entradas;
    // sin lógica de cancelación ni reembolso
}

// CompraService.java → lógica de negocio
public class CompraService {
    public double cancelarCompra(Compra compra, int diasAntesDelEvento) { ... }
    public void confirmarCompra(Compra compra) { ... }
}
```

---

### O — OCP: Abierto para extensión, cerrado para modificación
**Solución:** Strategy y Decorator permiten agregar comportamientos sin tocar clases existentes. Para agregar una nueva política de reembolso se crea una clase nueva que implemente `CancelacionStrategy` sin modificar `CompraService`.

```java
// nueva política sin tocar CompraService
public class ReembolsoEventoCancelado implements CancelacionStrategy {
    public double calcularReembolso(double monto, int dias) { return monto; }
    public String getDescripcionPolitica() { return "Evento cancelado: reembolso total"; }
}
```

---

### L — LSP: Sustitución de Liskov
**Solución:** `ReembolsoCompletoStrategy` y `ReembolsoParcialStrategy` implementan `CancelacionStrategy` y se pueden usar indistintamente en `CompraService`. `ServicioVIP` y `SeguroCancelacion` reemplazan a `ServicioDecorator` sin romper nada.

```java
// cualquier implementación funciona igual en CompraService
CancelacionStrategy politica = new ReembolsoCompletoStrategy();
// o
CancelacionStrategy politica = new ReembolsoParcialStrategy();
servicio.setCancelacionStrategy(politica); // funciona igual con cualquiera
```

---

### I — ISP: Segregación de interfaces
**Solución:** En vez de una interfaz gigante, se crearon interfaces pequeñas y específicas.

```java
// cada interfaz tiene solo lo que necesita
public interface IUsuarioRepository {
    List<Usuario> obtenerUsuarios();
    Usuario buscarUsuarioPorId(String id);
    void agregar(Usuario usuario);
}

public interface IEventoRepository { ... }
public interface IReportable { String generarResumen(); String exportarCSV(); }
public interface INotificable { void notificar(String mensaje); }
```

---

### D — DIP: Inversión de dependencias
**Solución:** `DataInitializer` implementa `IUsuarioRepository` e `IEventoRepository`. Los servicios dependen de las interfaces, no de la implementación concreta. Si mañana se conecta una base de datos real, solo se crea una nueva implementación.

```java
// DataInitializer implementa las interfaces
public class DataInitializer implements IUsuarioRepository, IEventoRepository {
    @Override public List<Usuario> obtenerUsuarios() { return usuarios; }
    @Override public List<Evento> obtenerEventos() { return eventos; }
    // ...
}

// los servicios dependen de la interfaz, no de DataInitializer
IUsuarioRepository repo = DataInitializer.getInstance();
```

---

## 📊 Diagrama de clases

El diagrama completo se encuentra en el archivo `diagrama_clases.puml` en la raíz del repositorio.

Cubre las entidades: `Usuario`, `Evento`, `Recinto`, `Zona`, `Asiento`, `Compra`, `Entrada`, `Pago`, `Incidencia` y las clases de soporte de patrones: `CompraComponent`, `ServicioDecorator`, `CancelacionStrategy`, `EventoObserver`, `Comando`, `ReporteExportador`.

Para visualizarlo instalar el plugin **PlantUML Integration** en IntelliJ IDEA y abrir el archivo `.puml`.

---

## 📁 Datos de prueba inicializados

`DataInitializer` carga automáticamente al iniciar la app:

- **3 usuarios** con métodos de pago (Tarjeta Débito, Nequi, PSE, Tarjeta Crédito)
- **1 recinto** (Estadio Centenario, Armenia) con 3 zonas y asientos VIP numerados
- **3 eventos publicados** (Concierto Juanes, Romeo y Julieta, Festival Petronio)
- Listas vacías listas para recibir compras, pagos e incidencias en tiempo de ejecución

---

## 📄 Reportes operativos

La app genera reportes desde el menú de administrador:

| Formato | Clase | Contenido |
|---|---|---|
| CSV | `ReporteCSVAdapter` | ID, Usuario, Evento, Total, Estado |
| PDF | `ReportePDFAdapter` | Tabla formateada con fecha de generación y total de registros |

