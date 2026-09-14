# ApexHealth ..

ApexHealth is a Spring Boot (Java) backend for a basic hospital / clinic management system. It models patients, doctors, departments, appointments, and insurance policies, and persists them to a PostgreSQL database using Spring Data JPA / Hibernate.

> **Status:** early-stage / work in progress. The project currently exposes its functionality through service classes and JUnit tests rather than REST controllers — see [Current State & Known Gaps](#current-state--known-gaps) below.

## Tech Stack

- **Java 21**
- **Spring Boot 4.1.0** (`spring-boot-starter-parent`)
  - `spring-boot-starter-data-jpa` — JPA / Hibernate persistence
  - `spring-boot-starter-webmvc` — Spring MVC web layer
- **PostgreSQL** — primary datastore (`postgresql` JDBC driver)
- **Lombok** — boilerplate reduction (`@Getter`, `@Setter`, `@Builder`, etc.)
- **JUnit 5 / Spring Boot Test** — testing (`spring-boot-starter-data-jpa-test`, `spring-boot-starter-webmvc-test`)
- **Maven** (with the Maven Wrapper, `mvnw` / `mvnw.cmd`)

## Data Model

| Entity | Description |
|---|---|
| `patient` | A patient record — name, gender, birth date, email, blood group, creation timestamp. Has a one-to-one relationship with `Insurance` and a one-to-many relationship with `Appointment`. |
| `Doctor` | A doctor — name, specialization, email. Many-to-many with `Department`. |
| `Department` | A hospital department — has a head doctor (`Doctor`, one-to-one) and a set of doctors (many-to-many). |
| `Appointment` | Links a `patient` and a `Doctor` at a given date/time, with an optional reason. |
| `Insurance` | An insurance policy — policy number, provider, valid-until date, creation timestamp. One-to-one with `patient`. |
| `bloodgroup` (enum) | `A_POSITIVE`, `A_NEGATIVE`, `B_POSITIVE`, `B_NEGATIVE`, `AB_POSITIVE`, `AB_NEGATIVE`, `O_POSITIVE`, `O_NEGATIVE` |

A `BloodGroupCountResponseEntity` DTO (blood group + count) is also defined, intended for a "count patients by blood group" style query/response.

### Repositories

Standard `JpaRepository<T, Long>` interfaces exist for each entity:

- `patientRepository`
- `DoctorRepository`
- `DepartmentRepository`
- `AppointmentRepository`
- `InsuranceRepository`

None currently define custom query methods beyond what `JpaRepository` provides out of the box.

### Services

- **`AppointmentService`** — `createAppointment(appointment, doctorId, patientId)`: looks up the doctor and patient, links them to the appointment, and saves it.
- **`InsuranceService`** — `assignPatientToInsurance(insurance, patientId)` and `diassociatePatientToInsurance(patientId)`: attach/detach an insurance policy to a patient while keeping the bidirectional relationship consistent.
- **`PatientService`** — currently a stub with a `patientRepository` field and no methods implemented yet.

## Project Structure

```
ApexHealth/
├── mvnw, mvnw.cmd              # Maven wrapper scripts
├── pom.xml                     # Maven project descriptor
└── src/
    ├── main/
    │   ├── java/com/nicode/ApexHealth/
    │   │   ├── ApexHealthApplication.java     # Spring Boot entry point
    │   │   ├── Dto/
    │   │   │   └── BloodGroupCountResponseEntity.java
    │   │   ├── Entity/
    │   │   │   ├── Appointment.java
    │   │   │   ├── Department.java
    │   │   │   ├── Doctor.java
    │   │   │   ├── Insurance.java
    │   │   │   ├── patient.java
    │   │   │   └── Type/bloodgroup.java
    │   │   ├── Repository/
    │   │   │   ├── AppointmentRepository.java
    │   │   │   ├── DepartmentRepository.java
    │   │   │   ├── DoctorRepository.java
    │   │   │   ├── InsuranceRepository.java
    │   │   │   └── patientRepository.java
    │   │   └── Service/
    │   │       ├── AppointmentService.java
    │   │       ├── InsuranceService.java
    │   │       └── PatientService.java
    │   └── resources/
    │       ├── application.properties
    │       └── data.sql            # Seed data (patients, doctors)
    └── test/
        └── java/com/nicode/ApexHealth/
            ├── ApexHealthApplicationTests.java
            ├── AppointmentTest.java
            ├── InsuranceTest.java
            └── patientTest.java
```

## Getting Started

### Prerequisites

- JDK 21+
- PostgreSQL running locally, with a database created (default configured name: `Apex_DB`)
- (Optional) Maven, though the Maven Wrapper is included so a local install isn't required

### 1. Configure the database

Edit `src/main/resources/application.properties` and point it at your PostgreSQL instance:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/Apex_DB
spring.datasource.username=<your-username>
spring.datasource.password=<your-password>
```

> ⚠️ **Note:** the current `application.properties` in the repo has real-looking credentials committed in plain text, and `spring.jpa.hibernate.ddl-auto=create` (which **drops and recreates** all tables on every startup). Both are fine for local experimentation but should be changed before this project goes anywhere near production — externalize credentials (env vars / a secrets manager) and switch `ddl-auto` to `validate` or `update` (or use a migration tool like Flyway/Liquibase).

On startup, `data.sql` seeds a handful of sample patients and doctors (the app is configured with `spring.sql.init.mode=always` and `spring.jpa.defer-datasource-initialization=true`, so this runs after Hibernate creates the schema).

### 2. Run the application

```bash
# macOS/Linux
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

The application runs with a base context path of `/api` (see `server.servlet.context-path` in `application.properties`).

### 3. Run the tests

```bash
./mvnw test
```

## Current State & Known Gaps

This is an in-progress learning/portfolio project. A few things to be aware of if you're picking it up:

- **No REST controllers yet.** Despite depending on `spring-boot-starter-webmvc`, there are no `@RestController` classes — functionality is currently exercised only through the service layer and its JUnit tests (e.g. `AppointmentTest`, `InsuranceTest`, `patientTest`).
- **`PatientService` is a stub** with no implemented methods.
- **`patientRepository` has no custom queries** yet (e.g. the blood-group count DTO isn't wired up to a query).
- **Error handling in `AppointmentService.createAppointment`** uses `orElseThrow(null)`, which will throw a `NullPointerException` rather than a meaningful error if the doctor or patient isn't found — worth replacing with a proper exception (similar to how `InsuranceService` does it).
- **Naming conventions** — the `patient` entity, `patientRepository`, and `bloodgroup`/`patientTest` classes are lowercase, which is inconsistent with standard Java class-naming conventions (`Patient`, `PatientRepository`, `BloodGroup`).
- **Secrets in source control** — database credentials are committed directly in `application.properties`.

## Roadmap Ideas

- Add REST controllers to expose CRUD operations for patients, doctors, departments, appointments, and insurance.
- Implement `PatientService` and add custom repository queries (e.g. count patients by blood group, using the existing `BloodGroupCountResponseEntity` DTO).
- Move secrets to environment variables and add a `.env`/`application-local.properties` pattern.
- Replace `ddl-auto=create` with a proper migration strategy (Flyway or Liquibase) once the schema stabilizes.
- Add validation (e.g. `jakarta.validation`) on entity fields and DTOs.

## License

No license file is currently included in this repository. Add one (e.g. MIT, Apache 2.0) if you intend for others to use or contribute to this code.
