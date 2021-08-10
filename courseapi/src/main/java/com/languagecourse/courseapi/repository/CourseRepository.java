package com.languagecourse.courseapi.repository;

import com.languagecourse.courseapi.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CourseRepository extends JpaRepository<Course, Long> {
}
