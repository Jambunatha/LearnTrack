package com.airtribe.learntrack.repository;

import com.airtribe.learntrack.entity.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class StudentRepository {

    private final List<Student> students = new ArrayList<>();

    public void save(Student student) {
        students.add(student);
    }

    public Optional<Student> findById(int id) {
        return students.stream().filter(student -> student.getId() == id).findFirst();
    }

    public List<Student> findAll() {
        return Collections.unmodifiableList(students);
    }
}
