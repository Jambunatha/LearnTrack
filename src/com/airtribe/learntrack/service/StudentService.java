package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Student;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.StudentRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.List;

public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(String firstName, String lastName, String email, String batch) {
        Student student = email.isEmpty()
                ? new Student(IdGenerator.getNextStudentId(), firstName, lastName, batch)
                : new Student(IdGenerator.getNextStudentId(), firstName, lastName, email, batch);
        studentRepository.save(student);
        return student;
    }


    public List<Student> listStudents() {
        return studentRepository.findAll();
    }

    public Student findStudentById(int studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new EntityNotFoundException("Student details not found with id: " + studentId));
    }

    public void deactivateStudent(int studentId) {
        Student student = findStudentById(studentId);
        student.setActive(false);
        studentRepository.save(student);
    }
}
