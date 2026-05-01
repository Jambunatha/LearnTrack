package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Enrollment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class EnrollmentRepository {

    private final List<Enrollment> enrollments = new ArrayList<>();

    public void save(Enrollment enrollment) {
        enrollments.add(enrollment);
    }

    public Optional<Enrollment> findById(int id) {
        return enrollments.stream().filter(enrollment -> enrollment.getId() == id).findFirst();
    }

    public List<Enrollment> findAll() {
        return Collections.unmodifiableList(enrollments);
    }

    public Optional<Enrollment> findByStudentId(int studentId) {
        return enrollments.stream().filter(enrollment -> enrollment.getStudentId() == studentId).findFirst();
    }
}