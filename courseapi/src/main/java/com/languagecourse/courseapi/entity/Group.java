package com.languagecourse.courseapi.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "group")
    private List<Person> persons;


}
