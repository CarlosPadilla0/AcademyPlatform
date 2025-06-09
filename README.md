# AcademyPlatform

Plataforma Académica Interna (API RESTful)

## Descripción

AcademyPlatform es una API RESTful desarrollada en Java con Spring Boot para la gestión académica de cursos, asignaturas, estudiantes, profesores, materiales, matrículas, notas y reportes. Permite la administración segura de usuarios y recursos educativos, integrando autenticación JWT y control de roles (ADMIN, TEACHER, STUDENT).

## Características principales

- Gestión de usuarios (registro, login, roles)
- Administración de cursos, asignaturas y periodos lectivos
- Matrícula de estudiantes en cursos
- Registro y consulta de notas
- Gestión de materiales didácticos
- Reportes de promedios y desempeño
- Seguridad con JWT y roles
- Documentación OpenAPI/Swagger

## Estructura del proyecto

```text
AcademyPlatform/
├── src/
│   ├── main/
│   │   ├── java/com/hitss/AcademyPlatform/
│   │   │   ├── controllers/   # Controladores REST
│   │   │   ├── entities/      # Entidades JPA
│   │   │   ├── dto/           # Objetos de transferencia
│   │   │   ├── repositories/  # Repositorios Spring Data
│   │   │   ├── services/      # Lógica de negocio
│   │   │   ├── security/      # Seguridad y JWT
│   │   │   └── ...
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
├── README.md
└── ...
```

## Instalación y ejecución

1. **Requisitos previos:**
   - Java 17+
   - Maven 3.9+
   - MySQL Server

2. **Configura la base de datos:**

   Crea una base de datos MySQL y actualiza `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/academy_db
   spring.datasource.username=usuario
   spring.datasource.password=contraseña
   ```

3. **Compila y ejecuta:**

   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

   O en Windows:

   ```powershell
   .\mvnw.cmd clean install
   .\mvnw.cmd spring-boot:run
   ```

4. **Accede a la documentación Swagger:**

   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Uso de la API

La API utiliza autenticación JWT. Primero, regístrate y obtén un token:

### Autenticación

- **POST /api/auth/register** — Registra un usuario (ADMIN, TEACHER, STUDENT)
- **POST /api/auth/login** — Obtiene el token JWT

### Endpoints principales

- **Usuarios:**
  - `GET /api/usuarios` — Lista todos los usuarios
  - `GET /api/usuarios/{id}` — Detalle de usuario
- **Estudiantes:**
  - `GET /api/estudiantes` — Lista estudiantes
  - `GET /api/estudiantes/{id}/notas` — Notas del estudiante
- **Profesores:**
  - `GET /api/profesores` — Lista profesores
  - `GET /api/profesores/{id}/asignaturas` — Asignaturas asignadas
- **Cursos y Asignaturas:**
  - `GET /api/cursos` — Lista cursos
  - `GET /api/asignaturas` — Lista asignaturas
- **Materiales:**
  - `POST /api/materiales` — Subir material (TEACHER/ADMIN)
  - `GET /api/materiales/asignatura/{id}` — Materiales por asignatura
- **Notas:**
  - `POST /api/notas` — Registrar nota (TEACHER/ADMIN)
  - `GET /api/notas/asignatura/{id}` — Notas por asignatura
  - `GET /api/notas/estudiante/{id}` — Notas por estudiante
- **Matrículas:**
  - `POST /api/matriculas` — Matricular estudiante
  - `GET /api/matriculas` — Listar matrículas
- **Periodos:**
  - `GET /api/periodos` — Listar periodos lectivos
- **Reportes:**
  - `GET /api/reportes/notas-promedio` — Promedios generales
  - `GET /api/reportes/historial-estudiante/{id}` — Historial de notas de un estudiante

### Ejemplo de autenticación

```http
POST /login
{
  "email": "admin@example.com",
  "password": "adminpass"
}
```

Respuesta:

```json
{
  "accessToken": "<jwt-token>"
}
```

Usa el token en el header:

```http
Authorization: Bearer <jwt-token>
```


## Licencia

Este proyecto está bajo la Licencia MIT. Ver el archivo [LICENSE](LICENSE).

---

Desarrollado por CarlosPadilla0, 2025.
