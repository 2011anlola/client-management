**Idioma:** [English](README.md) | **Español**

# Sistema de Gestión de Clientes

Una aplicación web full-stack para gestionar información de clientes, construida con backend Spring Boot y frontend Angular.

Esta aplicación permite a los usuarios realizar operaciones CRUD en entradas de clientes: agregar nuevos clientes, ver los existentes, actualizar información de clientes y eliminar clientes según sea necesario. El sistema incluye capacidades de filtrado para encontrar rápidamente clientes, y presenta una interfaz de usuario limpia y responsiva construida con Bootstrap.

Este es un proyecto hecho principalmente para fines de portafolio.

**DESCARGO DE RESPONSABILIDAD**: Todas las entradas del Seeder de Base de Datos se hicieron usando IA. No es información real.

---

## Tabla de Contenidos

- [Características](#características)
- [Tecnologías y Aplicaciones Usadas](#tecnologías-y-aplicaciones-usadas)
- [Prerrequisitos](#prerrequisitos)
- [Cómo Ejecutar](#cómo-ejecutar)
- [Cómo Reiniciar la Base de Datos](#cómo-reiniciar-la-base-de-datos)
- [Endpoints de API](#endpoints-de-api)
- [Mejoras Futuras](#mejoras-futuras)

---

## Características

- **Operaciones CRUD**: Crear, leer, actualizar y eliminar entradas de clientes
- **Filtrado Avanzado**: Filtrar clientes por cada parámetro
- **Gestión de Estado de Clientes**: Alternar fácilmente el estado de los clientes entre activo e inactivo
- **Diseño Responsivo**: Interfaz de usuario basada en Bootstrap que funciona en escritorio y dispositivos móviles
- **API RESTful**: API backend bien estructurada siguiendo principios REST
- **Seeding de Base de Datos**: Población inicial de datos para pruebas y demostración
- **Validación de Formularios**: Validación del lado del cliente y del servidor para integridad de datos

---

## Tecnologías y Aplicaciones Usadas

- **Backend**:
  - Java 21
  - Spring Boot 4.0.3
  - Spring Data JPA
  - Base de Datos PostgreSQL
  - Maven (herramienta de construcción)

- **Frontend**:
  - Angular 21
  - TypeScript
  - Bootstrap 5
  - Node.js & npm

- **DevOps y Herramientas**:
  - IntelliJ IDEA
  - Docker
  - JUnit (pruebas)
  - Git y Github

---

## Prerrequisitos

- **Docker** instalado en tu sistema. [Descárgalo aquí](https://www.docker.com/products/docker-desktop/)
- (Opcional) JDK de Java 21 y Maven para desarrollo backend local
- (Opcional) Node.js 20+ y npm para desarrollo frontend local

---

## Cómo Ejecutar

### Usando Docker (Recomendado)

1. **Clona el repositorio**:
   ```bash
   git clone https://github.com/2011anlola/client-management.git
   cd client-management
   ```

2. **Construye y ejecuta la aplicación**:
   ```bash
   docker compose up --build
   ```

3. **Accede a la aplicación**:
   - Frontend: http://localhost:4200
   - API Backend: http://localhost:8080
   - Base de Datos: localhost:5432 (si es necesario para acceso directo)

La aplicación iniciará automáticamente todos los servicios (base de datos PostgreSQL, backend Spring Boot y frontend Angular) y configurará las conexiones necesarias.

### Configuración de Desarrollo Local

#### Configuración del Backend:
1. Asegúrate de que PostgreSQL esté ejecutándose localmente o vía Docker
2. Navega a la raíz del proyecto
3. Ejecuta `./mvnw spring-boot:run`

#### Configuración del Frontend:
1. Navega a `client-management-frontend/`
2. Instala dependencias: `npm install`
3. Inicia el servidor de desarrollo: `npm start`
4. Accede en http://localhost:4200

---

## Cómo Reiniciar la Base de Datos

Para reiniciar la base de datos y comenzar con datos frescos:

1. **Detén la aplicación y fuerza la eliminación de la Base de Datos**:
   ```bash
   docker compose down -v
   ```

2. **Reinicia la aplicación**:
   ```bash
   docker compose up --build
   ```

Esto recreará la base de datos desde cero y ejecutará el seeder de base de datos.

---

## Endpoints de API

El backend proporciona los siguientes endpoints de API REST:

- `GET /api/clients` - Obtener todos los clientes (con filtros opcionales)
- `GET /api/clients/{id}` - Obtener cliente por ID
- `POST /api/clients` - Crear un nuevo cliente
- `PUT /api/clients/{id}` - Actualizar un cliente existente
- `DELETE /api/clients/{id}` - Eliminar un cliente

## Mejoras Futuras

- Terminar la barra de navegación superior (no hace nada).
- Agregar paginación a la lista de clientes.
- Agregar más páginas.