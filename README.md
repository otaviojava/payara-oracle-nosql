# Payara Oracle NoSQL Integration

A Jakarta EE application demonstrating integration between [Payara Micro](https://payara.fish/downloads/payara-platform-community-edition) and Oracle NoSQL database using Eclipse JNoSQL. 
This project provides a RESTful API for managing a book library.

## Prerequisites

### For Docker Compose (Recommended)
- [Docker](https://www.docker.com/get-started)

### For Local Development
- [Java SE 21+](https://adoptium.net/?variant=openjdk21)
- [Maven 3.6+](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/get-started) (for Oracle NoSQL database)

## Quick Start with Docker Compose

The fastest way to get the application running is with Docker Compose. No need to install Java or Maven—Docker handles everything:

```bash
# Start both services (builds application automatically)
docker-compose up -d

# View logs
docker-compose logs -f

# Stop services
docker-compose down
```

The application will be available at http://localhost:8080/

The Dockerfile uses multi-stage builds to compile the Maven project inside Docker, so you don't need Maven installed locally.

## Development Setup

### Running Locally

1. Start Oracle NoSQL database:
```bash
docker run -d --name oracle-nosql -p 9999:8888 ghcr.io/oracle/nosql:latest-ce
```

2. Run the application with Payara Micro:
```bash
mvn clean package payara-micro:dev
```

3. Access the application at http://localhost:8080/

### Configuration

Database connection settings are configured in `src/main/resources/META-INF/microprofile-config.properties`:

```properties
jnosql.keyvalue.database=books
jnosql.document.database=books
jnosql.oracle.nosql.host=http://localhost:9999
```

When running with Docker Compose, the host is automatically set to `http://oracle-nosql:8888` via environment variables.

## Building and Deployment

### Build WAR File

```bash
mvn clean package
```

The WAR file will be created in `target/payara-oracle-nosql-0.1-SNAPSHOT.war`

### Build Docker Image

```bash
mvn clean package docker:build
```

This creates a Docker image named `payara-oracle-nosql:0.1-SNAPSHOT`

### Run Docker Container Manually

```bash
docker run -p 8080:8080 \
  -e JNOSQL_ORACLE_NOSQL_HOST=http://oracle-nosql:8888 \
  --network payara-network \
  payara-oracle-nosql:0.1-SNAPSHOT
```

## API Endpoints

The application exposes RESTful endpoints for book management:

- `GET /api/books` - List all books
- `GET /api/books/{id}` - Get book by ID
- `POST /api/books` - Create new book
- `PUT /api/books/{id}` - Update book
- `DELETE /api/books/{id}` - Delete book

Additional endpoints:
- `GET /api/hello` - Hello World endpoint
- Swagger UI available at http://localhost:8080/ (when running)

## Testing

Run tests with:

```bash
mvn test
```

## Project Structure

```
src/main/java/com/otaviojava/library/
├── api/              # REST resources and DTOs
├── application/      # Service layer and mappers
├── domain/           # Domain entities and repositories
└── infrastructure/   # JAX-RS configuration
```

## Technology Stack

- Jakarta EE 10
- Payara Micro 6.2025.9
- Eclipse JNoSQL 1.1.10
- Oracle NoSQL CE
- MapStruct 1.6.3
- MicroProfile

