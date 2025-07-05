# AuthService - Sistema de Autenticación y Autorización con JWT

## 🧾 Descripción

`Auth-Service` es un microservicio de autenticación construido con Spring Boot que permite a los usuarios registrarse, iniciar sesión y obtener tokens JWT para acceder de forma segura a otros servicios. El sistema implementa autorización basada en roles y manejo de refresh tokens.

Ideal para integrarse en arquitecturas de microservicios y proyectos donde se requiera seguridad robusta.

---

## 🚀 Funcionalidades principales

- Registro de usuarios
- Inicio de sesión con generación de access y refresh tokens
- Refresh automático de tokens
- Cierre de sesión (invalidación de token)
- Autorización basada en roles (`ADMIN`, `USER`)
- Encriptación de contraseñas con BCrypt
- Validaciones y manejo global de errores
- Documentación Swagger UI

---

## 🛠️ Tecnologías utilizadas

| Tecnología       | Versión         |
|------------------|------------------|
| Java             | 17               |
| Spring Boot      | 3.x              |
| Spring Security  | 6.x              |
| JWT (jjwt / jose4j) | ✅           |
| PostgreSQL       | 15+              |
| Docker           | ✅               |
| Swagger / OpenAPI| ✅               |

---

