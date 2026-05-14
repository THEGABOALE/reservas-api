# API de Reservas de Espacios de Estudio

API REST desarrollada con Spring Boot para gestionar salas de estudio y reservas dentro de un entorno académico.  
El proyecto implementa operaciones CRUD, persistencia con PostgreSQL y una relación entre entidades utilizando JPA/Hibernate.

---

## Tabla de contenido

- [Descripción del proyecto](#descripción-del-proyecto)
- [Tecnologías utilizadas](#tecnologías-utilizadas)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Modelo de datos](#modelo-de-datos)
- [Relación entre entidades](#relación-entre-entidades)
- [Configuración de la base de datos](#configuración-de-la-base-de-datos)
- [Ejecución del proyecto](#ejecución-del-proyecto)
- [Endpoints principales](#endpoints-principales)
- [Ejemplos de uso](#ejemplos-de-uso)
- [Pruebas en Postman](#pruebas-en-postman)
- [Documentación del proyecto](#documentación-del-proyecto)
- [Autor](#autor)

---

## Descripción del proyecto

Este proyecto consiste en una API REST para la administración de salas de estudio y reservas.

La aplicación permite registrar salas disponibles, consultar la información de cada sala, actualizar sus datos, eliminar registros y gestionar reservas asociadas a una sala específica.

El objetivo principal es demostrar el desarrollo de una API funcional con Spring Boot, aplicando operaciones CRUD, conexión a una base de datos relacional y relaciones entre entidades mediante JPA/Hibernate.

---

## Tecnologías utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Lombok
- Validation
- Maven
- Postman
- Git y GitHub

---

## Estructura del proyecto

```text
src/main/java/ni/edu/uam/reservas_api
│
├── controller
│   ├── SalaEstudioController.java
│   └── ReservaController.java
│
├── model
│   ├── SalaEstudio.java
│   └── Reserva.java
│
├── repository
│   ├── SalaEstudioRepository.java
│   └── ReservaRepository.java
│
├── service
│   ├── SalaEstudioService.java
│   └── ReservaService.java
│
└── ReservasApiApplication.java
```

---

## Modelo de datos

### SalaEstudio

Representa una sala disponible para realizar reservas.

Campos principales:

- `id`
- `nombre`
- `ubicacion`
- `capacidad`
- `disponible`

### Reserva

Representa una reserva realizada por un usuario para una sala específica.

Campos principales:

- `id`
- `nombreSolicitante`
- `correoSolicitante`
- `fecha`
- `horaInicio`
- `horaFin`
- `motivo`
- `salaEstudio`

---

## Relación entre entidades

El proyecto implementa una relación de uno a muchos:

```text
SalaEstudio 1 ─── * Reserva
```

Esto significa que una sala de estudio puede tener varias reservas, pero cada reserva pertenece únicamente a una sala.

En JPA/Hibernate se implementa mediante:

```java
@OneToMany(mappedBy = "salaEstudio")
```

y

```java
@ManyToOne
@JoinColumn(name = "sala_id")
```

---

## Configuración de la base de datos

El proyecto utiliza PostgreSQL como base de datos relacional.

Crear una base de datos con el siguiente nombre:

```sql
CREATE DATABASE reservas_estudio_db;
```

Luego configurar el archivo:

```text
src/main/resources/application.properties
```

Ejemplo de configuración:

```properties
spring.application.name=reservas-api

spring.datasource.url=jdbc:postgresql://localhost:5432/reservas_estudio_db
spring.datasource.username=postgres
spring.datasource.password=TU_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.open-in-view=false

server.port=8080
```

> Nota: reemplazar `TU_PASSWORD` por la contraseña local de PostgreSQL.

---

## Ejecución del proyecto

Para ejecutar el proyecto desde la terminal:

```bash
mvn spring-boot:run
```

También puede ejecutarse directamente desde el IDE utilizando la clase principal:

```text
ReservasApiApplication.java
```

Cuando la API se ejecute correctamente, estará disponible en:

```text
http://localhost:8080
```

---

## Endpoints principales

### Salas de estudio

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/salas` | Listar todas las salas |
| GET | `/api/salas/{id}` | Buscar una sala por ID |
| POST | `/api/salas` | Crear una nueva sala |
| PUT | `/api/salas/{id}` | Actualizar una sala existente |
| DELETE | `/api/salas/{id}` | Eliminar una sala |
| GET | `/api/salas/{id}/reservas` | Listar las reservas de una sala |

### Reservas

| Método | Endpoint | Descripción |
|---|---|---|
| GET | `/api/reservas` | Listar todas las reservas |
| GET | `/api/reservas/{id}` | Buscar una reserva por ID |
| POST | `/api/reservas/sala/{salaId}` | Crear una reserva asociada a una sala |
| PUT | `/api/reservas/{id}/sala/{salaId}` | Actualizar una reserva |
| DELETE | `/api/reservas/{id}` | Eliminar una reserva |

---

## Ejemplos de uso

### Crear una sala

```http
POST /api/salas
```

```json
{
  "nombre": "Sala de Estudio A",
  "ubicacion": "Biblioteca Central - Primer piso",
  "capacidad": 8,
  "disponible": true
}
```

### Crear una reserva asociada a una sala

```http
POST /api/reservas/sala/1
```

```json
{
  "nombreSolicitante": "Gabriel García",
  "correoSolicitante": "garciaga@uamv.edu.ni",
  "fecha": "2026-05-15",
  "horaInicio": "08:00:00",
  "horaFin": "10:00:00",
  "motivo": "Estudio grupal para programación"
}
```

---

## Pruebas en Postman

Las pruebas de la API se realizaron utilizando Postman para validar los endpoints principales del CRUD.

La colección de Postman estará disponible en:

[Ver colección de Postman](docs/postman/Reservas%20API%20-%20Pruebas%20CRUD.postman_collection.json)

Pruebas principales realizadas:

- Crear salas de estudio.
- Listar salas registradas.
- Buscar sala por ID.
- Actualizar información de una sala.
- Crear reservas asociadas a una sala.
- Listar reservas registradas.
- Consultar reservas por sala.
- Actualizar reservas.
- Eliminar registros.

---

## Documentación del proyecto

La documentación final del proyecto estará disponible en la carpeta `docs`.

- [Ver carpeta de documentación](docs/)
- [Ver informe PDF](docs/pdf/Informe%20API%20Reservas.pdf)
- [Ver colección de Postman](docs/postman/Reservas%20API%20-%20Pruebas%20CRUD.postman_collection.json)

El informe PDF incluye:

- Portada.
- Descripción breve del proyecto.
- Capturas de pruebas realizadas en Postman.
- Enlace del repositorio en GitHub.

---

## Autor

**Gabriel Alejandro García Angulo**  
Universidad Americana  
Ingeniería en Sistemas
