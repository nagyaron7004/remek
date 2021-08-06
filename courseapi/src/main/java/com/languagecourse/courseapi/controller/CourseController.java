package com.languagecourse.courseapi.controller;

import com.languagecourse.courseapi.entity.Course;
import com.languagecourse.courseapi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping
    public Course add(@RequestBody Course course) {
        return courseService.add(course);
    }
    @GetMapping
    public List<Course> getAll() {return courseService.getAll();}

    @GetMapping("/{id}")
    public Course getById(@PathVariable("id") Long id) {
        return courseService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable ("id") Long id) {
        courseService.deleteById(id);
    }

    @PutMapping
    public Course update(@RequestBody Course course) {
        return courseService.update(course);
    }


}
