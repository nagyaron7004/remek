package com.languagecourse.courseapi.service;

import com.languagecourse.courseapi.entity.Course;

import com.languagecourse.courseapi.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;

    public Course add(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public Course getById(Long id) {
        if (courseRepository.findById(id).isPresent()) {
            return courseRepository.getById(id);
        } else throw new IllegalArgumentException("Id does not exist");
    }

    public void deleteById(Long id) {
        if (courseRepository.existsById(id)){
            courseRepository.deleteById(id);
        }
        throw new RuntimeException("Course not found: " + id + "!");
    }

    public Course update(Course course) {
        return courseRepository.save(course);
    }
}
