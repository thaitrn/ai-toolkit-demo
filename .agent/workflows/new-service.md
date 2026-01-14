---
description: Generate new microservice
---

# New Service Generator Workflow

Tạo một microservice mới theo chuẩn của team.

## Input Required
- Service name (e.g., `payment-service`)
- Main entity names
- Required integrations (Kafka, Redis, other services)

## Steps

1. Create project structure
```bash
mkdir -p src/main/java/com/vtrip/{service_name}/{config,controller,dto/request,dto/response,entity,exception,mapper,repository,service/impl,feign,kafka/producer,kafka/consumer}
mkdir -p src/main/resources
mkdir -p src/test/java/com/vtrip/{service_name}
```

2. Generate base files
   - Application.java
   - application.yml
   - Dockerfile
   - build.gradle

3. Generate entity classes
   - Use @Entity, @Table annotations
   - Include audit fields (createdAt, updatedAt)
   - Generate related DTOs

4. Generate service layer
   - Interface + Implementation
   - Basic CRUD operations

5. Generate controller
   - RESTful endpoints
   - OpenAPI documentation

6. Generate mapper
   - MapStruct mapper for entity/DTO

7. Generate tests
   - Unit tests for service
   - Integration tests for controller

8. Generate configuration
   - Database config
   - Kafka config (if needed)
   - Feign clients (if needed)

## Output
Complete microservice skeleton ready for development.
