package com.languagecourse.courseapi.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phoneNumber;
    private String email;

    //@ManyToOne(cascade = CascadeType.REMOVE)
    @ManyToOne
    private Group groups;

}
