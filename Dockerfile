# Stage 1: build
FROM maven:3.9.12-eclipse-temurin-21-alpine AS build

WORKDIR /app

# Copy pom.xml first for caching
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy full project
COPY . .

# Build the jar
RUN mvn clean package -DskipTests

# Stage 2: runtime
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

COPY --from=build /app/target/client-management-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]