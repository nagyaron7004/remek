package com.languagecourse.courseapi.repository;

import com.languagecourse.courseapi.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GroupRepository extends JpaRepository<Group, Long> {
}
