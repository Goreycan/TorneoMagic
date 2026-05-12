# Torneo Magic - Backend API (Microservicios)

## Descripción del Proyecto
Sistema integral y escalable para la gestión de torneos de Magic: The Gathering (Commander Edition). Esta plataforma backend permite administrar jugadores, mazos, catálogo de cartas, infraestructura (locales y organizadores) y la lógica jerárquica de un torneo (inscripciones, generación de rondas, partidas y resultados).

El proyecto fue construido como una Arquitectura orientada a Microservicios, aislando dominios de negocio y aplicando un Modelo Entidad-Relación (MER) estrictamente normalizado en Tercera Forma Normal (3FN).

## Tecnologías, Herramientas y Estándares
* Lenguaje: Java 17+
* Framework Principal: Spring Boot 3.x
* Persistencia de Datos: Spring Data JPA / Hibernate
* Base de Datos: MySQL (Servidor Local Laragon) / Proyectado para PostgreSQL
* Comunicación de Red: Spring Cloud OpenFeign / RestTemplate (Para llamadas entre microservicios)
* Validaciones: Spring Boot Starter Validation (JSR 380)
* Testing de API: Postman
* Control de Versiones: Git & GitHub (Flujo basado en Feature Branches)
## Estructura de Entidades (MER Normalizado en 3FN)
Para asegurar la atomicidad y evitar la redundancia, el dominio se separó en 15 entidades, eliminando cualquier relación de "Muchos a Muchos" directa a favor de tablas intermedias.

### 1. Dominio de Usuarios y Cartas
* JUGADOR: Perfil del participante (email único).
* MAZO: Deck configurado por el jugador.
* CARTA y TIPO: Catálogo maestro de cartas.
* CARTA_MAZO (Intermedia): Resuelve la relación N:N, almacenando la cantidad exacta de copias de una carta en un mazo.

### 2. Dominio de Torneos y Enfrentamientos
* TORNEO: Evento principal (fechas, reglas, cupos máximos).
* PARTICIPACION (Intermedia): Registra a un Jugador en un Torneo, almacenando su ranking actual.
* RONDA: Etapas clasificatorias del torneo.
* PARTIDA: Enfrentamiento 1v1. Registra qué id_mazo utilizó cada jugador.
* RESULTADO: Relación 1:1 con Partida. Almacena puntajes y valida al ganador.

### 3. Dominio de Infraestructura
* LOCAL: Sede física del evento.
* ORGANIZADOR: Usuario administrador.
* LOCAL_ORGANIZADOR (Intermedia): Historial de organizadores por sede.
* COMUNA y REGION: Tablas de normalización geográfica para búsquedas filtradas.

## Patrones de Diseño y Buenas Prácticas (Código)

### 1. Manejo de Tráfico y Recursividad (El Problema de Jackson)
Se previno el clásico error de "Bucle Infinito" (StackOverflowError) al serializar JSON mediante:
* Relaciones Unidireccionales: Priorización de @ManyToOne con FetchType.LAZY.
* Capa DTO (Data Transfer Object): Nunca se devuelve una @Entity al cliente. Toda entrada y salida pasa por objetos DTO (JugadorDTO, MazoDTO), protegiendo el esquema de la base de datos.

### 2. Manejo Global de Excepciones
Se implementó un @ControllerAdvice para capturar errores de ejecución y devolver respuestas HTTP profesionales y amigables.
* 404 Not Found: Si se busca un ID que no existe (ej: NoSuchElementException).
* 400 Bad Request: Si los datos ingresados no cumplen el contrato @Valid (ej: @NotBlank, @Email).
* 500 Internal Server Error: Errores no controlados del servidor.

### 3. Comunicación entre Microservicios
Para satisfacer el aislamiento de bases de datos, los diferentes dominios se comunican mediante HTTP. 
* Ejemplo: Si el Microservicio de Torneos necesita validar a un Jugador, realiza una petición remota (GET) al Microservicio de Usuarios utilizando un cliente HTTP y procesa la respuesta en formato JSON antes de continuar el flujo.


## Documentación de API (Endpoints Principales)
A continuación, ejemplos de los endpoints RESTful expuestos a través de los @RestController. (Se recomienda usar Postman para las pruebas):

| Método HTTP | Endpoint Base | Descripción | Código de Éxito |
| :--- | :--- | :--- | :--- |
| GET | /api/v1/jugadores | Lista todos los jugadores activos | 200 OK |
| GET | /api/v1/jugadores/{id} | Obtiene el detalle de un jugador | 200 OK |
| POST | /api/v1/jugadores | Crea un nuevo jugador (Requiere JSON) | 201 Created |
| PUT | /api/v1/mazos/{id} | Actualiza un mazo existente | 200 OK |
| DELETE | /api/v1/torneos/{id} | Elimina un torneo de forma lógica | 204 No Content |

## Flujo de Trabajo (Git Branching)
Este proyecto fue construido colaborativamente utilizando Git. Para evitar conflictos, se estableció un flujo de trabajo atómico:
1. Creación de Ramas Aisladas: Cada integrante trabajó en feature branches separadas.
2. Commits Atómicos: Trazabilidad clara con mensajes de commit descriptivos.
3. Merge Requests: Integración controlada a la rama main tras validación cruzada.

### Instrucciones de Instalacion y Ejecucion

### Paso 1: Descargar el proyecto
Abra una terminal y ejecute el siguiente comando para clonar el repositorio:

bash
git clone https://github.com/Goreycan/TorneoMagic.git
### Paso 2: Configurar la base de datos
Abra el archivo src/main/resources/application.properties y verifique las credenciales de conexión. La configuración por defecto para el entorno local con MySQL es:
spring.datasource.url=jdbc:mysql://localhost:3306/db_torneo
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
### Paso 3: Construir el proyecto
Desde la raiz del proyecto, descargue las dependencias y compile el codigo:
mvnw.cmd clean install
Paso 4: Arrancar el servidor
Inicie la aplicacion Spring Boot
mvnw.cmd spring-boot:run

Si todo sale bien, vas a ver un montón de texto en la consola y al final algo como:

```
Tomcat started on port(s): 8080 (http)
Started MsClientesApplication in 4.523 seconds
```

Eso significa que el servidor está corriendo en `http://localhost:8080`. Si abres esa dirección en el navegador no vas a ver una página bonita (porque es una API, no una web), pero ya está lista para recibir peticiones.

Para probar la creacion de un jugador desde la terminal:

Bash
curl -X POST http://localhost:8080/api/v1/jugadores \
  -H "Content-Type: application/json" \
  -d '{
    "nombre": "Paola",
    "apellido": "Gomez",
    "email": "paola@torneomagic.cl",
    "alias": "PaoCommander"
  }'


