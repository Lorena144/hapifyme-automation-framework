# Hapifyme Automation Framework

Test automation framework built using Java, Maven, Selenide, Cucumber BDD, Rest Assured, JUnit and TestNG.

The project focuses on building a maintainable and scalable automation framework covering both UI and API testing using reusable components, clean architecture and separation of concerns.

---
![CI](https://github.com/Lorena144/hapifyme-automation-framework/actions/workflows/tests.yml/badge.svg)

# Tech Stack

## UI Automation
- Java
- Maven
- Selenide
- Cucumber BDD
- JUnit 4

## API Automation
- Rest Assured
- TestNG
- Jackson Databind
- Awaitility

## Logging
- Allure Reports
- SLF4J + Logback

## DevOps & CI/CD
- GitHub Actions
- Docker
- DockerHub

---

# Current Automated Flows

## UI Automation

### Authentication
- Successful login
- Invalid login scenarios

### Registration
- Successful user registration

### Home Feed
- Create post

---

## API Automation

### Authentication & User Lifecycle
- User registration
- Successful login
- Invalid login validation
- Retrieve user profile
- Update user profile
- Delete user profile

---

## UI ↔ API Integration Flows
- Create user through API → login through UI
- Login through UI → create post → validate post through API

---

# Project Structure
```txt
hapifyme-automation-framework
│
├── ui-tests
├── api-tests
├── common
└── pom.xml
```
# UI Framework Structure
```txt
ui-tests
│
├── src/main/java
│   ├── models
│   └── pages
│
├── src/main/resources
│   ├── config
│   └── logback.xml
│
├── src/test/java
│   ├── hooks
│   ├── runners
│   └── stepdefinitions
│
└── src/test/resources
    ├── features
    └── logback-test.xml
```
# API Framework Structure
```txt
api-tests
│
├── src/main/java
│   └── com.hapifyme
│       ├── models
│       └── services
│
├── src/main/resources
│   └── config
│
└── src/test/java
    └── tests
```

# Implemented Design Principles
- Separation of concerns
- Reusable page objects
- Reusable API request/response models
- Shared test context handling
- Centralized configuration management
- Dynamic test data generation
- Explicit waits and polling strategies
- Modular multi-module Maven architecture
- Clean and maintainable framework architecture
- Reusable validation steps with Allure integration

---

# Reporting & Test Organization
- Allure reporting integration
- Custom Allure steps and attachments
- Request and response logging using Allure Rest Assured
- Smoke and regression test tagging using Cucumber tags
- GitHub Actions CI pipeline with Allure report publishing

---

# Docker

Build Docker image locally:
```bash
docker build -t hapifyme-automation-framework .
```

Run tests inside Docker container:
```bash
docker run hapifyme-automation-framework
```

DockerHub image:
```bash
docker pull lorenab144/hapifyme-automation-framework
```

---

## Run

Run all tests:
```bash
mvn clean test
```

Run tests from UI module only:
```bash
mvn test -pl ui-tests
```

Run API tests only:
```bash
mvn test -pl api-tests
```

Generate Allure Report:
```bash
allure serve api-tests/target/allure-results
```
