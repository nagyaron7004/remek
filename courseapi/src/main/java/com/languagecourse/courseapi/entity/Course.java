package com.languagecourse.courseapi.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Language language;
    private Grade grade;
    //
     //private Person teacher; // by Person.id

}
