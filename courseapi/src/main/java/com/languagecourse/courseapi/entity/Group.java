package com.languagecourse.courseapi.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Data
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Person member; // course member by Person.id
    @ManyToOne
    private Course course; // by Course.id


    @ManyToOne(optional = false)
    private Course groups;

    public Course getGroups() {
        return groups;
    }

    public void setGroups(Course groups) {
        this.groups = groups;
    }
}
