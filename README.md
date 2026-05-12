
#  Torneo Magic - Backend
# Sistema de Gestión de Torneos de Cartas  TGR- Magic: The Gathering: Crear torneos de Magic Commander.
Registrar jugadores y sus mazos, Inscribir jugadores en torneos (Participación), Generar rondas, Registrar resultados, Calcular ranking automáticamente

El sistema se basa en un Modelo Entidad-Relación (MER) normalizado que incluye las siguientes entidades principales:

* Jugadores y Mazos: Gestión de usuarios, sus estadísticas y la composición de sus mazos.
* Torneos y Rondas: Estructura jerárquica de torneos, desde la inscripción hasta la generación de rondas.
* Partidas y Resultados: Registro detallado de enfrentamientos, puntajes y ganadores.
* Infraestructura Local: Gestión de locales (sedes), organizadores y ubicación geográfica (Comunas/Regiones).

# tecnologías utilizadas
Lenguaje: Java 
Framework:Spring Boot 
Persistencia:Spring Data JPA / Hibernate
Base de Datos: mySQL /
Arquitectura: Microservicios (Proyectada) con bases de datos segregadas.

# Reglas de Negocio y JPA Aplicadas
Para este proyecto se han seguido estrictas reglas de desarrollo:
* Relaciones: Uso preferencial de relaciones unidireccionales `@ManyToOne` con `FetchType.LAZY` para optimizar el rendimiento.
* Evitar Bucles:Uso de DTOs (Data Transfer Objects) para las respuestas de la API.
  
# Arquitectura (CSR)
Controller: Maneja endpoints REST
Service: Lógica de negocio
Repository: Acceso a datos (JPA)
Model: Entidades
DTO: Transferencia de datos
