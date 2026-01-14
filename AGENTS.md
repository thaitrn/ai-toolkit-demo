# VTrip Engineering - AI Agent Instructions

> This file provides high-level guidance for AI agents working with this codebase.

## 🏗️ Architecture Overview

This is a **microservices architecture** built with:
- **Java 17+** / **Spring Boot 3.x**
- **PostgreSQL** for persistence
- **Kafka** for async messaging
- **Redis** for caching
- **Spring Cloud** (Feign, Config, Gateway)

## 📁 Project Structure

```
service-name/
├── src/main/java/com/vtrip/{service}/
│   ├── config/         # Configuration
│   ├── controller/     # REST endpoints
│   ├── dto/            # Data Transfer Objects
│   ├── entity/         # JPA entities
│   ├── exception/      # Custom exceptions
│   ├── mapper/         # MapStruct mappers
│   ├── repository/     # Data access
│   ├── service/        # Business logic
│   └── feign/          # External service clients
└── src/test/java/      # Tests
```

## ✅ Key Conventions

1. **Layered Architecture** - Controllers → Services → Repositories
2. **Constructor Injection** - Use `@RequiredArgsConstructor` 
3. **MapStruct** for DTO mappings
4. **Feign Clients** for inter-service communication
5. **Kafka** for async events

## 🔧 Common Commands

```bash
# Build
./gradlew build

# Run tests
./gradlew test

# Run locally
./gradlew bootRun --args='--spring.profiles.active=local'

# Docker build
docker build -t service-name .
```

## 📚 Detailed Rules

For specific patterns and templates, see:
- `.cursor/rules/` - Detailed AI rules for each concern
- `docs/` - Architecture documentation

## ⚠️ Important Guidelines

1. **No hardcoded secrets** - Use environment variables
2. **Always validate input** - Use `@Valid` on request DTOs
3. **Handle exceptions** - Use custom BusinessException hierarchy
4. **Write tests** - Minimum 80% coverage for new code
5. **Document APIs** - Use OpenAPI/Swagger annotations
