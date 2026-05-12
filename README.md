
# Torneo Magic - Backend System (Commander Edition)

## Descripción del Proyecto
Sistema integral para la gestión de torneos de *Magic: The Gathering*. La aplicación permite administrar jugadores, mazos, cartas, infraestructura (locales y organizadores) y la lógica jerárquica de un torneo (inscripciones, rondas, partidas y resultados). 

Este proyecto aplica un **Modelo Entidad-Relación (MER) estrictamente normalizado  y sigue buenas prácticas de persistencia con Spring Data JPA.

## Tecnologías y Estándares
* **Lenguaje:** Java 
* **Framework:** Spring Boot 
* **Persistencia:** Spring Data JPA  / MySQL
* **Arquitectura:** Patrón CSR (Model-Controller-Service-Repository) proyectado para Microservicios (con bases de datos segregadas por módulo).

 Estructura de Entidades (MER Normalizado )
El sistema se compone de 15 entidades para evitar dependencias parciales y redundancia de datos. Se eliminaron todas las relaciones `@ManyToMany` reemplazándolas por entidades intermedias.

# 1. Gestión de Jugadores y Cartas
* `JUGADOR`: Datos del participante (con email unique).
* `MAZO`: El deck creado por el jugador.
* `CARTA`: Catálogo base de cartas.
* `TIPO`: Categoría o tipo de la carta.
* `CARTA_MAZO`: **(Entidad Intermedia)** Resuelve la relación N:N entre cartas y mazos, permitiendo agregar el atributo "cantidad".

# 2. Gestión del Torneo y Juego
* `TORNEO`: Evento principal con cupos y fechas.
* `PARTICIPACION`: **(Entidad Intermedia)** Vincula Jugadores con Torneos, guardando el ranking y estado de inscripción.
* `RONDA`: Fases jerárquicas dentro de un torneo.
* `PARTIDA`: Enfrentamientos específicos. Referencia directamente los `id_mazo` que usó cada jugador.
* `RESULTADO`: Relación 1:1 con Partida. Almacena ganadores y puntajes.

# 3. Infraestructura y Ubicación
* `LOCAL`: Sede física del evento.
* `ORGANIZADOR`: Encargado del torneo.
* `LOCAL_ORGANIZADOR`: **(Entidad Intermedia)** Resuelve la relación N:N entre locales y organizadores, añadiendo la fecha de asignación y cargo.
* `COMUNA` y `REGION`: Normalización de la ubicación geográfica.

# Reglas JPA Aplicadas en el Código
Para garantizar el rendimiento y la integridad, se siguieron estas convenciones:
* `@Entity` en todas las clases del modelo.
* `@Table` utilizando nomenclatura `snake_case`.
* `@Id` + `@GeneratedValue(strategy = GenerationType.IDENTITY)` para las claves primarias.
* **Relaciones `@ManyToOne`**: Se utilizó `FetchType.LAZY` en todas para optimizar consultas a la base de datos.
* Integridad referencial en todas las Foreign Keys (FKs) mediante `@JoinColumn`.
* Columnas con `@Column(nullable = false)` para campos obligatorios.

#  Prevención de Loops y Recursividad Infinita
Una de las prioridades del diseño fue evitar que Jackson genere ciclos infinitos al serializar JSON. Esto se logró mediante:
1. **Relaciones Unidireccionales:** Solo se define el lado que contiene la FK (el lado `@ManyToOne`). Las colecciones `@OneToMany` se omiten a menos que sean estrictamente necesarias.
2. **Uso de DTOs (Data Transfer Objects):** Las respuestas de la API nunca devuelven entidades directas. Se mapean a objetos como `JugadorDTO`, `TorneoDTO`, `PartidaDTO`, etc.
3. Uso de `FetchType.LAZY` para evitar cargar objetos anidados innecesarios.

# Orden de Construcción 
El proyecto fue construido siguiendo este flujo en capas para asegurar el bajo acoplamiento:
1. **Modelos (Entidades):** Creación de las 14 clases Java mapeando el MER normalizado.
2. **Repositorios (JPA):** Interfaces para la persistencia.
3. **DTOs:** Creación de los objetos de transferencia de datos.
4. **Servicios (Lógica de Negocio):** Implementación de reglas y manejo de dependencias.
5. **Controladores (API REST):** Exposición de los endpoints utilizando `ResponseEntity` y validaciones.
