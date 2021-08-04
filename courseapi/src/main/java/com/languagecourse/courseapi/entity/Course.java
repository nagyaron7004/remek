package com.languagecourse.courseapi.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Language language;
    private Grade grade;

  /*  @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private List<Group> groups;*/
}
