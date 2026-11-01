# Rental Management System – Backend (Mid-Term Project)

- **Student:** NSHUTI Dorothy
- **Student ID:** 27129
- **Project:** Rental Management System
- **Course:** INSY 8322 Web Technology and Internet (AUCA) – Lecturer: Patrick DUSHIMIMANA

Spring Boot 3 · Spring Data JPA · PostgreSQL · Java 17

## Steps to run the API

1. Install Java 17+, Maven and PostgreSQL.
2. Create the database: `CREATE DATABASE rental_management_db;`
3. Open `src/main/resources/application.properties` and put your PostgreSQL password in `spring.datasource.password`.
4. Run: `mvn spring-boot:run` (the tables are created automatically, `ddl-auto=update`).
5. The API listens on `http://localhost:8080`.
6. Load the seed data through the save endpoints: `python seed.py` (see the note at the top of the file about dates).
7. Import `rental-management-system-midterm.postman_collection.json` into Postman and set the collection variables (ids come from the `/all` endpoints).

## Layout

`model` (6 entities + enum) · `repository` (derived + JPQL queries) · `service` (business rules, return List / Optional / String) · `controller` (maps messages to HTTP statuses with if/else)
