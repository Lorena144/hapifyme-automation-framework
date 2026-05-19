# Hapifyme Automation Framework

UI automation framework built using Java, Maven, Selenide, Cucumber BDD and JUnit.

The project focuses on building a maintainable and scalable test automation framework using Page Object Model, reusable components and clean project architecture.

---

# Tech Stack

- Java
- Maven
- Selenide
- Cucumber BDD
- JUnit 4
- SLF4J + Logback

---

# Current Automated Flows

## Authentication
- Successful login
- Invalid login scenarios

## Registration
- Successful user registration

## Home Feed
- Create post

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

# Implemented Design Principles
- Separation of concerns
- Reusable page objects
- Centralized configuration management
- Stable synchronization strategy using explicit waits
- Dynamic test data handling

## Run

Run all tests:
```bash
mvn clean test
```

Run tests from UI module only:
```bash
mvn test -pl ui-tests
```

# Author
Built as a personal QA Automation portfolio project focused on transitioning from Manual QA to Automation QA.