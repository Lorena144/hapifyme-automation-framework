# Hapifyme Automation Framework

Test automation framework built using Java, Maven, Selenide, Cucumber BDD, Rest Assured, JUnit and TestNG.

The project focuses on building a maintainable and scalable automation framework covering both UI and API testing using reusable components, clean architecture and separation of concerns.

---

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

# Project Structure
```txt
hapifyme-automation-framework
│
├── ui-tests
├── api-tests
└── pom.xml
```
# UI Framework Structure
```txt
ui-tests
│
├── src/main/java
│   ├── models
│   ├── pages
│   └── utils
│
├── src/main/resources
│   └── config
│
├── src/test/java
│   ├── hooks
│   ├── runners
│   └── stepdefinitions
│
└── src/test/resources
    └── features	
```
# API Framework Structure
```txt
api-tests
│
├── src/main/java
│   ├── models
│   └── utils
│
├── src/main/resources
│   └── config
│
├── src/test/java
│   ├── context
│   ├── tests
│   └── utils
│
└── src/test/resources
```

# Implemented Design Principles
- Separation of concerns
- Reusable page objects
- Reusable API request/response models
- Shared test context handling
- Centralized configuration management
- Dynamic test data generation
- Explicit waits and polling strategies
- Clean and maintainable framework architecture
- Reusable validation steps with Allure integration

---

# Reporting & Test Organization
- Allure reporting integration for API automation
- Request and response logging using Allure Rest Assured
- Custom Allure steps and attachments
- Smoke and regression test tagging using Cucumber tags

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

# Author
Built as a personal QA Automation portfolio project focused on transitioning from Manual QA to Automation QA.