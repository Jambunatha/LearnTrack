package com.airtribe.learntrack;

import com.airtribe.learntrack.constants.AppConstants;
import com.airtribe.learntrack.constants.MenuOptions;
import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.CourseStatus;
import com.airtribe.learntrack.enums.EnrollmentStatus;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.service.CourseService;
import com.airtribe.learntrack.service.EnrollmentService;
import com.airtribe.learntrack.service.StudentService;
import com.airtribe.learntrack.util.InputValidator;

import java.util.Scanner;

public class Main {

    private final Scanner scanner;
    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;

    public Main() {
        scanner = new Scanner(System.in);

        StudentRepository studentRepository = new StudentRepository();
        CourseRepository courseRepository = new CourseRepository();
        EnrollmentRepository enrollmentRepository = new EnrollmentRepository();

        studentService = new StudentService(studentRepository);
        courseService = new CourseService(courseRepository);
        enrollmentService = new EnrollmentService(studentService, courseService, enrollmentRepository);
    }

    public static void main(String[] args) {
        new Main().start();
    }

    private void start() {
        System.out.println("Welcome to " + AppConstants.APP_NAME + "!");

        boolean running = true;
        while (running) {
            try {
                showMainMenu();
                int options = InputValidator.readInt(scanner, "Choose an option:");

                switch (options) {
                    case MenuOptions.STUDENT_MANAGEMENT:
                        handleStudentMenu();
                        break;
                    case MenuOptions.COURSE_MANAGEMENT:
                        handleCourseMenu();
                        break;
                    case MenuOptions.ENROLLMENT_MANAGEMENT:
                        handleEnrollmentMenu();
                        break;
                    case MenuOptions.EXIT:
                        running = false;
                        System.out.println("Thank you for using LearnTrack. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("\n==============================");
        System.out.println("           Main Menu            ");
        System.out.println("================================");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Enrollment Management");
        System.out.println("0. Exit");
    }

    private void handleStudentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n----- Student Management -----");
            System.out.println("1. Add new student");
            System.out.println("2. View all students");
            System.out.println("3. Search student by ID");
            System.out.println("4. Deactivate a student");
            System.out.println("0. Back");

            int option = InputValidator.readInt(scanner, "Choose student option: ");
            try {
                switch (option) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        studentService.listStudents();
                        break;
                    case 3:
                        findStudentById();
                        break;
                    case 4:
                        deactivateStudent();
                        break;
                    case 0:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addStudent() {
        String firstName = InputValidator.readNonEmptyString(scanner, "First name: ");
        String lastName = InputValidator.readNonEmptyString(scanner, "Last name: ");
        String email = InputValidator.readOptionalString(scanner, "Email (Optional): ");
        String batch = InputValidator.readNonEmptyString(scanner, "Batch: ");

        Student student = studentService.addStudent(firstName, lastName, email, batch);
        System.out.println("Student added: " + student);
    }

    private void findStudentById() {
        int studentId = InputValidator.readInt(scanner, "Please enter student id: ");
        Student student = studentService.findStudentById(studentId);
        System.out.println(student);
    }

    private void deactivateStudent() {
        int studentId = InputValidator.readInt(scanner, "Please enter student id to deactivate : ");
        studentService.deactivateStudent(studentId);
    }

    private void handleCourseMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n----- Course Management -----");
            System.out.println("1. Add new course");
            System.out.println("2. View all courses");
            System.out.println("3. Activate/Deactivate a course");
            System.out.println("0. Back");

            int option = InputValidator.readInt(scanner, "Choose course option: ");
            try {
                switch (option) {
                    case 1:
                        addCourse();
                        break;
                    case 2:
                        courseService.listCourses();
                        break;
                    case 3:
                        activateOrDeactivateCourse();
                        break;
                    case 0:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void addCourse() {
        String courseName = InputValidator.readNonEmptyString(scanner, "Course name: ");
        String description = InputValidator.readNonEmptyString(scanner, "Course description: ");
        int duration = InputValidator.readInt(scanner, "Duration in weeks: ");
        Course course = courseService.addCourse(courseName, description, duration);
        System.out.println("Course added successfully: " + course);
    }

    private void activateOrDeactivateCourse() {
        int courseId = InputValidator.readInt(scanner, "Enter course ID: ");
        Course course = courseService.findCourseById(courseId);
        System.out.println("Course found: " + course.getCourseName() + " (Status: " + course.getStatus() + ")");
        String choice = InputValidator.readChoice(scanner,
                "Do you want to activate or deactivate this course?", "A", "D");

        if ("A".equals(choice)) {
            if (CourseStatus.ACTIVE.equals(course.getStatus())) {
                System.out.println("Course is already active.");
            } else {
                course.setStatus(CourseStatus.ACTIVE);
                System.out.println("Course " + courseId + " has been activated.");
            }
        } else if ("D".equals(choice)) {
            if (CourseStatus.INACTIVE.equals(course.getStatus())) {
                System.out.println("Course is already inactive.");
            } else {
                course.setStatus(CourseStatus.INACTIVE);
                System.out.println("Course " + courseId + " has been deactivated.");
            }
        }
    }

    private void handleEnrollmentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n----- Enrollment Management -----");
            System.out.println("1. Enroll a student in a course");
            System.out.println("2. View enrollments for a student");
            System.out.println("3. Mark enrollment as completed/cancelled");
            System.out.println("0. Back");

            int option = InputValidator.readInt(scanner, "Choose course option: ");
            try {
                switch (option) {
                    case 1:
                        enrollStudent();
                        break;
                    case 2:
                        viewStudentEnrollments();
                        break;
                    case 3:
                        markEnrollment();
                        break;
                    case 0:
                        back = true;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void enrollStudent() {
        int studentId = InputValidator.readInt(scanner, "Enter student ID: ");
        int courseId = InputValidator.readInt(scanner, "Enter course ID: ");
        Enrollment enrollment = enrollmentService.enrollStudent(studentId, courseId);
        System.out.println("Enrollment created successfully: " + enrollment);
    }

    private void viewStudentEnrollments() {
        int studentId = InputValidator.readInt(scanner, "Enter student ID: ");
        Enrollment enrollment = enrollmentService.findByStudentId(studentId);
        System.out.println(enrollment);
    }

    private void markEnrollment() {
        int studentId = InputValidator.readInt(scanner, "Enter student ID: ");
        Enrollment enrollment = enrollmentService.findByStudentId(studentId);
        System.out.println("Enrollment found: " + enrollment.getStudentId() + " (Status: " + enrollment.getStatus() + ")");
        String choice = InputValidator.readChoice(scanner,
                "Do you want to mark this enrollment as completed or cancelled?", "C", "X");

        if ("C".equals(choice)) {
            if (EnrollmentStatus.COMPLETED.equals(enrollment.getStatus())) {
                System.out.println("Enrollment is already marked as completed.");
            } else {
                enrollment.setStatus(EnrollmentStatus.COMPLETED);
                System.out.println("Enrollment for student " + studentId + " has been marked as completed.");
            }
        } else if ("X".equals(choice)) {
            if (EnrollmentStatus.CANCELLED.equals(enrollment.getStatus())) {
                System.out.println("Enrollment is already marked as cancelled.");
            } else {
                enrollment.setStatus(EnrollmentStatus.CANCELLED);
                System.out.println("Enrollment for student " + studentId + " has been cancelled.");
            }
        }
    }
}