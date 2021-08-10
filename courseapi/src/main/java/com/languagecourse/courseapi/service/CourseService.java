package com.languagecourse.courseapi.service;

import com.languagecourse.courseapi.entity.Course;
import com.languagecourse.courseapi.entity.Group;
import com.languagecourse.courseapi.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
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
        } else throw new IllegalArgumentException("id does not exist");
    }

    public void deleteById(Long id) {
        courseRepository.deleteById(id); // ha nincs ilyen id runtime error?
    }

    public Course update(Course course) {
        return courseRepository.save(course);
    }

    public List<Group> getAllGroupByCourseId(Long id) {
    /*    List<Group> groups = courseRepository.findById(id).orElseThrow().getGroups();*/
        return null;
    }
}
