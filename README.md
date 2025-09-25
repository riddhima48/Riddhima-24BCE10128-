# Riddhima-24BCE10128-
# Campus Course & Records Manager (CCRM)

A console-based Java SE application demonstrating OOP, Streams, NIO.2, Date/Time API, design patterns, and more.

## Project structure
- src/edu/ccrm/... : Java sources
- data/ : sample CSVs and exports/backups
- screenshots/ : (place your screenshots here)
- build.sh : compile & run helper

## How to build & run (Linux / macOS / Windows WSL)
Requirements: JDK 11+

Compile:
```
cd src
javac -d ../out $(find . -name "*.java")
```
Run:
```
java -cp ../out edu.ccrm.cli.Main
```

## What is included
- Student, Instructor, Course, Enrollment domain classes
- Services: StudentService, CourseService, EnrollmentService, TranscriptService
- Simple Import/Export (CSV), Backup with NIO.2
- CLI menu for operations

## Notes & mapping
- Singleton: AppConfig, DataStore
- Builder: Course.Builder
- Streams used in import/export, reports
- Enums: Semester, Grade (with points)

## Enabling assertions
Run with `-ea` flag when running `java` to enable assertions:
```
java -ea -cp ../out edu.ccrm.cli.Main
```

## Sample CSVs
See `data/students_sample.csv` and `data/courses_sample.csv`.
## Evolution of Java
The evolution of Java has been a long process, from its inception at Sun Microsystems to its current state under Oracle. 
Key Milestones in Java's Evolution

- 1995: Initial Public Release (Java 1.0)
- 1998: Java 2 (J2SE 1.2)
- 2004: Java 5.0 (J2SE 5)
- 2014: Java 8 (Java SE 8) 
- 2017: Java 9 (Java SE 9)
- 2018 Onward: The Six-Month Release Cycle

## Java ME vs SE vs EE comparison
Java has three main editions, each tailored for a specific development environment:

- Java SE (Standard Edition): This is the core, foundational platform for general-purpose programming. It includes the basic Java language, core APIs, and libraries needed for developing desktop applications, applets, and command-line programs. It's the starting point for most Java developers.

- Java ME (Micro Edition): A highly specialized version designed for resource-constrained devices like embedded systems, mobile phones (pre-smartphones), and IoT devices. It's a subset of Java SE with a smaller footprint and specific APIs for low-memory, low-power environments.

- Java EE (Enterprise Edition): Now known as Jakarta EE, this edition is built on top of Java SE and provides a set of APIs and a runtime environment for developing large-scale, multi-tiered, and scalable enterprise applications and web services. It includes technologies like Servlets, JSP, and Enterprise JavaBeans (EJB) for building robust, distributed systems.

## JDK/JRE/JVM explanation.
- JVM (Java Virtual Machine): The heart of Java's "Write Once, Run Anywhere" principle. The JVM is an abstract machine that provides a runtime environment to execute Java bytecode. It's the interpreter that translates your platform-independent bytecode into machine-specific code, making Java programs portable across different operating systems.

- JRE (Java Runtime Environment): This is the package you need to run a Java program. It's the on-the-ground environment that includes the JVM, core libraries, and other supporting files. If you only want to run Java applications but not develop them, you only need the JRE.

- JDK (Java Development Kit): The full toolkit for developing Java applications. It includes everything in the JRE, plus essential development tools like the javac compiler, a debugger, and other utilities necessary to write, compile, and package Java code. To write and build a Java program, you need the JDK.

## Windows install steps
- Download: Go to the official JetBrains website and download the installer for the desired edition (Community or Ultimate).
- Run Installer: Double-click the downloaded .exe file to start the installation wizard.
- Choose Location: Select the installation directory. The default path is usually fine.
- Configuration:
- Create Desktop Shortcut: Check this box for easy access.
- Update PATH variable: This allows you to open projects from the command line. It's a useful option to enable.
- Add "Open Folder as Project" (Optional): This adds a context menu option in File Explorer.
- Start Menu Folder: Choose the name for the Start Menu folder. The default is fine.
- Installation: Click "Install" and wait for the process to complete.
- Run IntelliJ: Once finished, you can check the box to run IntelliJ IDEA immediately.

  
