package com.airtribe.learntrack.service;

import com.airtribe.learntrack.entity.Course;
import com.airtribe.learntrack.enums.CourseStatus;
import com.airtribe.learntrack.exception.EntityNotFoundException;
import com.airtribe.learntrack.repository.CourseRepository;
import com.airtribe.learntrack.util.IdGenerator;

import java.util.List;

public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addCourse(String courseName, String description, int duration) {
        if (duration <= 0) {
            throw new IllegalArgumentException("Duration must be greater than 0 week.");
        }
        Course course = new Course(
                IdGenerator.getNextCourseId(), courseName, description, duration, CourseStatus.ACTIVE);
        courseRepository.save(course);
        return course;
    }

    public List<Course> listCourses() {
        return courseRepository.findAll();
    }

    public Course findCourseById(int courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course details not found with id: " + courseId));
    }
}
