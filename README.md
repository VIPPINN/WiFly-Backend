# WiFly Backend

**Spring Boot API para WiFi Network Management and Support Portal**

Backend API para la aplicación WiFly, construido con Spring Boot y Hibernate. Proporciona autenticación, gestión de redes WiFi y servicios de soporte técnico.

## Tecnologías

- **Java** 17
- **Spring Boot** 3.2.0
- **Spring Security** con JWT
- **Spring Data JPA** / Hibernate
- **Maven** - Herramienta de construcción
- **H2 Database** (desarrollo) / **MySQL** (producción)
- **Lombok** - Reducción de código boilerplate
- **MapStruct** - Mapeo de objetos
- **Spring Boot Actuator** - Monitoreo y métricas

## Características Principales

### Autenticación y Autorización
- Sistema de autenticación basado en JWT
- Registro y login de usuarios
- Recuperación de contraseña mediante email
- Seguridad implementada con Spring Security

### Gestión de Redes WiFi
- CRUD completo de redes WiFi
- Asociación de redes con usuarios
- Consulta de dispositivos conectados por red

### Gestión de Dispositivos
- Registro y seguimiento de dispositivos conectados
- Asociación de dispositivos con redes WiFi

### Sistema de Tickets de Soporte
- Creación y gestión de tickets de soporte
- Sistema de mensajería dentro de tickets
- Seguimiento del estado de tickets

### Servicios de Email
- Notificaciones de recuperación de contraseña
- Sistema de emails transaccionales

## Requisitos

- **Java Development Kit (JDK)** 17 o superior
- **Maven** 3.6 o superior
- **MySQL** 8.0 o superior (para producción)

## Variables de Entorno y Propiedades

El sistema utiliza las siguientes variables de entorno:

### Base de Datos
- `SPRING_DATASOURCE_URL` - URL de conexión a la base de datos (default: `jdbc:h2:mem:testdb` en desarrollo)
- `SPRING_DATASOURCE_USERNAME` - Usuario de la base de datos (default: `sa` en desarrollo)
- `SPRING_DATASOURCE_PASSWORD` - Contraseña de la base de datos (default: `password` en desarrollo)
- `DATABASE_URL` - URL de MySQL para producción (default: `jdbc:mysql://localhost:3306/wifly`)
- `DATABASE_USERNAME` - Usuario de MySQL para producción (default: `root`)
- `DATABASE_PASSWORD` - Contraseña de MySQL para producción

### Servidor
- `SERVER_PORT` - Puerto del servidor (default: `8080`)

### JWT (Autenticación)
- `JWT_SECRET` - Secreto para firmar tokens JWT (obligatorio en producción)
- `jwt.expiration` - Tiempo de expiración del token en milisegundos (default: `86400000` = 24 horas)

### Email
- `MAIL_USERNAME` - Usuario SMTP para envío de emails
- `MAIL_PASSWORD` - Contraseña SMTP para envío de emails
- `spring.mail.host` - Host del servidor SMTP (default: `smtp.gmail.com`)
- `spring.mail.port` - Puerto SMTP (default: `587`)

## Instalación y Configuración

1. Clonar el repositorio:
```bash
git clone https://github.com/VIPPINN/WiFly-Backend.git
cd WiFly-Backend
```

2. Instalar dependencias:
```bash
mvn clean install
```

3. Configurar variables de entorno (opcional para desarrollo):
```bash
export JWT_SECRET=tuSecretoSuperSeguroParaJWT
export MAIL_USERNAME=tu-email@gmail.com
export MAIL_PASSWORD=tu-contraseña
```

## Construcción y Ejecución

### Modo Desarrollo

Ejecutar directamente con Maven:
```bash
mvn spring-boot:run
```

La aplicación estará disponible en `http://localhost:8080`

### Construcción para Producción

1. Compilar y empaquetar:
```bash
mvn clean package
```

2. Ejecutar el JAR generado:
```bash
java -jar target/wifly-backend-0.0.1-SNAPSHOT.jar
```

### Con perfil de producción:
```bash
java -jar -Dspring.profiles.active=prod target/wifly-backend-0.0.1-SNAPSHOT.jar
```

## Ejemplo de Uso

### Registro de Usuario
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "username": "usuario@example.com",
    "password": "password123",
    "firstName": "Juan",
    "lastName": "Pérez"
  }'
```

### Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "username": "usuario@example.com",
    "password": "password123"
  }'
```

### Crear una Red WiFi (requiere autenticación)
```bash
curl -X POST http://localhost:8080/api/wifi-networks \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer TU_TOKEN_JWT" \
  -d '{
    "ssid": "Mi Red WiFi",
    "password": "password-wifi",
    "securityType": "WPA2"
  }'
```

### Listar Redes WiFi del Usuario
```bash
curl -X GET http://localhost:8080/api/wifi-networks \
  -H "Authorization: Bearer TU_TOKEN_JWT"
```

### Crear Ticket de Soporte
```bash
curl -X POST http://localhost:8080/api/support/tickets \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer TU_TOKEN_JWT" \
  -d '{
    "subject": "Problema con la conexión",
    "description": "No puedo conectarme a mi red WiFi",
    "priority": "HIGH"
  }'
```

## Endpoints de la API

### Autenticación (`/api/auth`)
- `POST /api/auth/register` - Registrar nuevo usuario
- `POST /api/auth/login` - Iniciar sesión
- `POST /api/auth/forgot-password` - Solicitar restablecimiento de contraseña
- `POST /api/auth/reset-password` - Restablecer contraseña con token

### Redes WiFi (`/api/wifi-networks`)
- `GET /api/wifi-networks` - Obtener redes del usuario
- `POST /api/wifi-networks` - Crear nueva red WiFi
- `PUT /api/wifi-networks/{id}` - Actualizar red WiFi
- `DELETE /api/wifi-networks/{id}` - Eliminar red WiFi
- `GET /api/wifi-networks/{id}/devices` - Obtener dispositivos conectados

### Soporte Técnico (`/api/support`)
- `GET /api/support/tickets` - Obtener tickets del usuario
- `POST /api/support/tickets` - Crear nuevo ticket
- `GET /api/support/tickets/{id}/messages` - Obtener mensajes del ticket
- `POST /api/support/tickets/{id}/messages` - Enviar mensaje al ticket

## Pruebas

Ejecutar las pruebas unitarias:
```bash
mvn test
```

## Consola de Base de Datos

En modo desarrollo, la consola H2 está disponible en:
```
http://localhost:8080/h2-console
```

**Configuración de conexión:**
- JDBC URL: `jdbc:h2:mem:testdb`
- Usuario: `sa`
- Contraseña: `password`

## Contribución

Las contribuciones son bienvenidas. Por favor, sigue estos pasos:

1. Haz fork del proyecto
2. Crea una rama para tu funcionalidad (`git checkout -b feature/nueva-funcionalidad`)
3. Realiza tus cambios y haz commit (`git commit -am 'Agregar nueva funcionalidad'`)
4. Sube los cambios a tu fork (`git push origin feature/nueva-funcionalidad`)
5. Abre un Pull Request

Por favor, asegúrate de:
- Escribir pruebas para nuevas funcionalidades
- Seguir las convenciones de código existentes
- Actualizar la documentación según sea necesario

## Licencia

Este proyecto está licenciado bajo la Licencia MIT - ver el archivo LICENSE para más detalles.

## Contacto

**VIPPINN** - [@VIPPINN](https://github.com/VIPPINN)

Repositorio del proyecto: [https://github.com/VIPPINN/WiFly-Backend](https://github.com/VIPPINN/WiFly-Backend)
