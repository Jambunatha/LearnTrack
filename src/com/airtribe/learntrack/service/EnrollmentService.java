package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.CourseStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.Optional;

public class EnrollmentService {

    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(
            StudentService studentService, CourseService courseService, EnrollmentRepository enrollmentRepository) {
        this.studentService = studentService;
        this.courseService = courseService;
        this.enrollmentRepository = enrollmentRepository;
    }

    public Enrollment enrollStudent(int studentId, int courseId) {
        Student student = studentService.findStudentById(studentId);
        Course course = courseService.findCourseById(courseId);

        if (!student.isActive()) {
            throw new IllegalStateException("Enrollment failed: Student " + studentId + " is inactive.");
        }
        if (CourseStatus.INACTIVE.equals(course.getStatus())) {
            throw new IllegalStateException("Enrollment failed: Course " + courseId + " is inactive.");
        }
        Optional<Enrollment> enrollment = enrollmentRepository.findByStudentId(studentId);
        if (enrollment.isPresent()) {
            throw new IllegalStateException("Enrollment failed: Student " + studentId + " is already enrolled.");
        }

        Enrollment newEnrollment = new Enrollment(IdGenerator.getNextEnrollmentId(), studentId, courseId);
        enrollmentRepository.save(newEnrollment);
        return newEnrollment;
    }

    public Enrollment findByStudentId(int studentId) {
        return enrollmentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new EntityNotFoundException("No enrollments found for student ID: " + studentId));
    }
}
