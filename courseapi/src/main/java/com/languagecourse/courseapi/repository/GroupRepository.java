package com.languagecourse.courseapi.repository;

import com.languagecourse.courseapi.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface GroupRepository extends JpaRepository<Group, Long> {
}
