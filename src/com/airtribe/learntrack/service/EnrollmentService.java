package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.entity.Enrollment;
import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.enums.CourseStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.EnrollmentRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.List;
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

    public void save(Enrollment enrollment) {
        enrollmentRepository.save(enrollment);
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

        Enrollment newEnrollment = new Enrollment(IdGenerator.getNextEnrollmentId(), studentId, courseId);
        save(newEnrollment);
        return newEnrollment;
    }

    public List<Enrollment> findByStudentId(int studentId) {
        List<Enrollment> enrollments = enrollmentRepository.findByStudentId(studentId);
        if (enrollments.isEmpty()) {
            throw new EntityNotFoundException("No enrollments found for student ID: " + studentId);
        }
        return enrollments;
    }
}
