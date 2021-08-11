package com.languagecourse.courseapi.repository;

import com.languagecourse.courseapi.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person, Long> {
}
