# LearnTrack

## Project Description
LearnTrack is a console-based Student & Course Management System built using Core Java.
It helps admins manage:
- Students
- Courses
- Enrollments

This project demonstrates:
- Java basics and package structure
- OOP concepts (Encapsulation, Inheritance, Polymorphism)
- Constructors and method overloading
- Collections using ArrayList
- Exception handling
- Menu-driven console application design

### Features
- **Student Management**
    - Add new student
    - View all students
    - Search student by ID
    - Deactivate a student (set `active = false` instead of deleting)

- **Course Management**
    - Add new course
    - View all courses
    - Activate/Deactivate a course

- **Enrollment Management**
    - Enroll a student in a course
    - View enrollments for a student
    - Mark enrollment as completed/cancelled
---

## Project Structure
```text
LearnTrack/
├── docs/
│   ├── Design_Notes.md
│   ├── JVM_Basics.md
│   └── Setup_Instructions.md
├── src/
│   └── com/airtribe/learntrack/
│       ├── constants/
│       │   ├── AppConstants.java
│       │   └── MenuOptions.java
│       ├── entity/
│       │   ├── Person.java
│       │   ├── Student.java
│       │   ├── Trainer.java
│       │   ├── Course.java
│       │   └── Enrollment.java
│       ├── enums/
│       │   ├── CourseStatus.java
│       │   └── EnrollmentStatus.java
│       ├── exception/
│       │   ├── EntityNotFoundException.java
│       │   └── InvalidInputException.java
│       ├── repository/
│       │   ├── StudentRepository.java
│       │   ├── CourseRepository.java
│       │   └── EnrollmentRepository.java
│       ├── service/
│       │   ├── StudentService.java
│       │   ├── CourseService.java
│       │   └── EnrollmentService.java
│       ├── util/
│       │   ├── IdGenerator.java
│       │   └── InputValidator.java
│       └── Main.java   
└── README.md
```

## How to Compile and Run

### Option 1: Command Line
1. Make sure you have **JDK 17** (or compatible version) installed and `javac`/`java` available in your PATH.
2. Open a terminal in the project root directory.
3. Compile all source files:
   ```bash
   javac *.java
   ```
4. Run the main program:
   ```bash
   java Main
   ```

### Option 2: Import into an IDE
1. Open your preferred IDE (e.g., IntelliJ IDEA, Eclipse, NetBeans, or VS Code with Java extensions).
2. Select **File → Open Project** (or **Open Folder** in VS Code).
3. Navigate to the project root directory and import it.
4. Ensure your IDE is configured to use **JDK 17** (or a compatible version).
5. Locate `Main.java` — this file contains the `public static void main(String[] args)` entry point.
6. Right-click on `Main.java` and choose **Run**, or use the IDE’s run button.
7. The program will start, and you can interact with the menu-driven interface to manage students, courses, and enrollments.

## Class Diagram
```text
                 +----------------+
                 |     Person     |
                 +----------------+
                 | - id           |
                 | - firstName    |
                 | - lastName     |
                 | - email        |
                 +----------------+
                         ^
                         |
                 extends |
                         |
                 +----------------+
                 |    Student     |
                 +----------------+
                 | - batch        |
                 | - active       |
                 +----------------+

+----------------+        +----------------+
|     Course     |        |   Enrollment   |
+----------------+        +----------------+
| - id           |        | - id           |
| - courseName   |        | - studentId    |
| - description  |        | - courseId     |
| - duration     |        | - enrollDate   |
| - active       |        | - status       |
+----------------+        +----------------+

+---------------------+
|   StudentService    |
+---------------------+
| manages Student[]   |
+---------------------+

+---------------------+
|   CourseService     |
+---------------------+
| manages Course[]    |
+---------------------+

+---------------------+
| EnrollmentService   |
+---------------------+
| manages Enrollment[]|
| uses StudentService |
| uses CourseService  |
+---------------------+

+---------------------+
|       Main          |
+---------------------+
| Console UI / Menus  |
+---------------------+
```

## Technologies Used
- Core Java
- ArrayList
- OOP Principles
- Console Input (Scanner)

## Author
Jambunatha Koni