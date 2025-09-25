# Campus Course & Records Manager (CCRM)

## Project Overview
The Campus Course & Records Manager (CCRM) is a console-based Java application designed to manage student and course data for an educational institution. It demonstrates core Java principles, Object-Oriented Programming, data persistence, and robust error handling in a structured, multi-package environment.

---

## How to Run
1.  **Prerequisites**: Java Development Kit (JDK) 17 or higher.
2.  **Clone the repository**: 
    ```bash
    git clone [https://github.com/YourUsername/your-repo-name.git](https://github.com/YourUsername/your-repo-name.git)
    ```
3.  **Navigate to the project directory**:
    ```bash
    cd your-repo-name
    ```
4.  **Run the application**: The application can be run from an IDE (like VS Code or Eclipse) by running the `main` method in `src/edu/ccrm/Application.java`.

---

## 1. Evolution of Java
* **1995**: Java 1.0 is officially released by Sun Microsystems.
* **1998**: J2SE 1.2 (Tiger) is released, introducing the Collections Framework and Swing.
* **2004**: J2SE 5.0 (Tiger) is a major release adding key features like Generics, Enums, Annotations, and the `Scanner` class.
* **2014**: Java SE 8 marks a massive evolution with the introduction of Lambda Expressions, the Stream API, and a new Date/Time API.
* **2018**: Java SE 11 is released as a Long-Term Support (LTS) version.
* **2021**: Java SE 17 becomes the latest LTS version, bringing further refinements to the language.

---

## 2. Java Platform Comparison (ME vs SE vs EE)

| Feature        | Java ME (Micro Edition)                          | Java SE (Standard Edition)                            | Java EE (Enterprise Edition / Jakarta EE)              |
|----------------|--------------------------------------------------|-------------------------------------------------------|--------------------------------------------------------|
| **Target** | Resource-constrained devices (e.g., IoT, sensors) | Desktop, servers, standard computing environments     | Large-scale, distributed, enterprise web applications  |
| **Core API** | A small subset of the Java SE API                | The core Java platform (Collections, I/O, etc.)       | Extends Java SE with APIs for web services, servlets, etc. |
| **Use Case** | Embedded systems, early mobile apps              | This CCRM project is a Java SE application.           | Web servers, application servers, microservices        |

---

## 3. Java Architecture: JDK, JRE, JVM

The Java platform is composed of three core components:

### ### JVM (Java Virtual Machine)
The JVM is an abstract machine that provides the runtime environment for Java bytecode to be executed. It's the component that makes Java "write once, run anywhere."

### ### JRE (Java Runtime Environment)
The JRE includes the JVM, core libraries, and other supporting files necessary to *run* Java applications. If you only want to run a Java program, you only need the JRE.

### ### JDK (Java Development Kit)
The JDK is a superset of the JRE. It contains everything in the JRE, plus the tools necessary to *develop* Java applications, such as the compiler (`javac`) and debugger (`jdb`).

**In simple terms:** `JDK > JRE > JVM`

---

## 4. Installation & Setup Screenshots

### JDK Installation Verification
[Your screenshot of the 'java -version' command output in your terminal here]

### IDE Project Setup
[Your screenshot of the CCRM project open in your IDE (VS Code or Eclipse) here]

### Program Running
[Your screenshot of the application's main menu running in the terminal here]

---

## 5. Feature Demonstration Mapping Table

| Concept / Requirement                | File(s) & Method(s) Where Demonstrated                               |
|--------------------------------------|----------------------------------------------------------------------|
| **Packages** | The entire project structure (`edu.ccrm.domain`, `service`, `io`, etc.) |
| **Inheritance & Abstraction** | `Student.java` and `Instructor.java` extend the abstract `Person.java` class. |
| **Encapsulation** | All domain classes use `private` fields with `public` getters/setters. |
| **Polymorphism** | *(TODO: Demonstrate by using a `Person` reference to hold a `Student` object)* |
| **Enums with Constructors** | `Grade.java` has a constructor to store grade points.                  |
| **Design Pattern: Builder** | `Course.java` uses a static nested `Builder` class for object creation. |
| **Design Pattern: Singleton** | *(TODO: Implement a Singleton class, e.g., for AppConfig)* |
| **Custom Checked Exceptions** | `MaxCreditLimitExceededException.java`                                 |
| **Exception Handling (`try-catch`)** | `Cli.java` in the `enrollStudent()` and `assignGrade()` methods.       |
| **File I/O with NIO.2 & Streams** | `ImportExportService.java` uses `Files.write()` and `Files.readAllLines()`. |
| **Lambda Expressions & Streams** | *(TODO: Implement search/filter features in services using streams)* |
| **Date/Time API** | *(TODO: Add a `LocalDate` field to the `Student` class for registration date)* |

---

## 6. Notes on Assertions

Assertions are used to verify assumptions about the program's state during development. They are disabled by default.

To enable them, the `-ea` (enable assertions) flag must be passed to the JVM at runtime. In VS Code or Eclipse, this can be added to the `vmArgs` section of the `launch.json` file or the "Run Configuration" settings.

**Example:** `assert studentId > 0;`