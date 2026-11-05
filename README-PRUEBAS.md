# BAS - API REST con autenticación JWT

Proyecto configurado para Java 17, Spring Boot 3.5.7, Spring Security 6, JJWT 0.11.5 y MySQL.

## 1. Requisitos

- JDK 17.
- MySQL Server iniciado.
- IntelliJ IDEA o Maven.
- Postman.

## 2. Configurar MySQL

El proyecto utiliza por defecto la base `bas_db` en `localhost:3306` y el usuario `root`. La base se crea automáticamente si MySQL permite `createDatabaseIfNotExist=true`.

En `src/main/resources/application.properties`, las credenciales se leen de variables de entorno:

- `DB_USERNAME` (por defecto `root`).
- `DB_PASSWORD` (por defecto vacío).
- `DB_URL` (opcional, para cambiar la URL).

Si tu usuario root tiene contraseña, define `DB_PASSWORD` con esa contraseña en la configuración de ejecución de IntelliJ o modifica el valor de entorno antes de iniciar la aplicación. No subas contraseñas reales a Git.

## 3. Ejecutar

En Windows PowerShell, desde la carpeta raíz del proyecto:

```powershell
.\mvnw.cmd clean test
.\mvnw.cmd spring-boot:run
```

La API queda en `http://localhost:8080`.

## 4. Importar Postman

Importa `postman/BAS-JWT.postman_collection.json` en Postman. La colección incluye las variables `baseUrl`, `email`, `password` y `token`.

Ejecuta las peticiones en este orden:

1. `1 - Registrar usuario`: `POST /auth/signup`, espera HTTP 201.
2. `2 - Iniciar sesion y guardar token`: `POST /auth/login`, espera HTTP 200. El script guarda automáticamente el JWT en la variable `token`.
3. `3 - Consultar mi perfil`: `GET /users/me`, espera HTTP 200.
4. `4 - Consultar todos los usuarios`: `GET /users`, espera HTTP 200.
5. `5 - Perfil sin token`: espera HTTP 401.
6. `6 - Perfil con token invalido`: espera HTTP 401.

Si vuelves a ejecutar el registro con el mismo correo, se espera HTTP 409 porque la cuenta ya existe. Puedes cambiar `email` en las variables de la colección para registrar otro usuario.

## 5. Endpoints

| Método | Endpoint | Acceso | Descripción |
|---|---|---|---|
| POST | `/auth/signup` | Público | Registrar usuario |
| POST | `/auth/login` | Público | Iniciar sesión y recibir JWT |
| GET | `/users/me` | JWT requerido | Consultar usuario autenticado |
| GET | `/users` | JWT requerido | Consultar usuarios |

Para las rutas protegidas, Postman debe enviar:

```http
Authorization: Bearer TU_TOKEN_JWT
```

## 6. Respuestas esperadas

- Registro exitoso: `201 Created`.
- Login exitoso: `200 OK` con `token`, `tokenType` y `expiresIn` (milisegundos).
- Acceso protegido sin token o con token inválido/expirado: `401 Unauthorized`.
- Correo duplicado: `409 Conflict`.
- Datos de entrada inválidos: `400 Bad Request`.

## 7. Seguridad

Las contraseñas se almacenan con BCrypt. Las respuestas de usuarios nunca incluyen el hash de la contraseña. La API usa sesiones `STATELESS`: no crea sesión HTTP y valida el JWT enviado en `Authorization: Bearer ...`.

La clave JWT incluida como valor predeterminado es únicamente para pruebas locales. En un despliegue real debe configurarse una clave secreta privada mediante `JWT_SECRET_KEY` (Base64, mínimo 32 bytes para HS256) y una duración mediante `JWT_EXPIRATION`.
