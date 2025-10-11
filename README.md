# WiFly Backend - WiFi Network Management API

Backend API for WiFly mobile application, built with Spring Boot and Hibernate. Provides authentication, WiFi network management, and support services.

## Features

- **Authentication & Authorization**: JWT-based authentication with Spring Security
- **User Management**: Registration, login, password recovery
- **WiFi Network Management**: CRUD operations for WiFi networks
- **Device Management**: Track connected devices
- **Support System**: Ticket management and messaging
- **Email Services**: Password reset and notifications

## Tech Stack

- **Framework**: Spring Boot 3.2.0
- **Security**: Spring Security with JWT
- **Database**: H2 (development), MySQL (production)
- **ORM**: Hibernate/JPA
- **Build Tool**: Maven
- **Java Version**: 17

## Prerequisites

- Java Development Kit (JDK 17)
- Maven 3.6+
- MySQL (for production)

## Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd WiFly-Backend
```

2. Install dependencies:
```bash
mvn clean install
```

## Running the Application

### Development Mode
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

### Production Mode
```bash
mvn clean package
java -jar target/wifly-backend-0.0.1-SNAPSHOT.jar
```

## API Endpoints

### Authentication
- `POST /api/auth/login` - User login
- `POST /api/auth/register` - User registration
- `POST /api/auth/forgot-password` - Request password reset
- `POST /api/auth/reset-password` - Reset password with token

### WiFi Networks
- `GET /api/wifi-networks` - Get user's WiFi networks
- `POST /api/wifi-networks` - Create new WiFi network
- `PUT /api/wifi-networks/{id}` - Update WiFi network
- `DELETE /api/wifi-networks/{id}` - Delete WiFi network
- `GET /api/wifi-networks/{id}/devices` - Get connected devices

### Support
- `GET /api/support/tickets` - Get user's support tickets
- `POST /api/support/tickets` - Create new support ticket
- `GET /api/support/tickets/{id}/messages` - Get ticket messages
- `POST /api/support/tickets/{id}/messages` - Send message

## Configuration

### Application Properties
The application uses `application.yml` for configuration:

- **Database**: H2 for development, MySQL for production
- **JWT**: Configurable secret and expiration time
- **Email**: SMTP configuration for notifications
- **CORS**: Configured for React Native app

### Environment Variables
- `JWT_SECRET`: JWT signing secret
- `DATABASE_URL`: Database connection URL
- `DATABASE_USERNAME`: Database username
- `DATABASE_PASSWORD`: Database password
- `MAIL_USERNAME`: Email username
- `MAIL_PASSWORD`: Email password

## Database Schema

### Entities
- **User**: User account information
- **WiFiNetwork**: WiFi network details
- **ConnectedDevice**: Devices connected to networks
- **SupportTicket**: Support requests
- **TicketMessage**: Messages within support tickets

## Security

- JWT-based authentication
- Password encryption with BCrypt
- CORS configuration for mobile app
- Role-based access control (ready for future expansion)

## Development

### Adding New Features
1. Create entity classes in `entity` package
2. Create repository interfaces extending `JpaRepository`
3. Create service classes for business logic
4. Create controller classes for REST endpoints
5. Add DTOs for request/response objects

### Testing
```bash
mvn test
```

### Database Console
In development mode, H2 console is available at:
`http://localhost:8080/h2-console`

## Deployment

### Docker (Recommended)
```bash
docker build -t wifly-backend .
docker run -p 8080:8080 wifly-backend
```

### Traditional Deployment
1. Build the JAR file: `mvn clean package`
2. Deploy to your application server
3. Configure environment variables
4. Set up database connection

## Monitoring

The application includes Spring Boot Actuator for monitoring:
- Health checks: `/actuator/health`
- Metrics: `/actuator/metrics`
- Application info: `/actuator/info`

## Contributing

1. Create a feature branch
2. Make your changes
3. Add tests for new functionality
4. Ensure all tests pass
5. Submit a pull request

## License

This project is proprietary software developed for Tecnored.
