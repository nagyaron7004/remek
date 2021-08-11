package com.languagecourse.courseapi.controller;

import com.languagecourse.courseapi.entity.Person;
import com.languagecourse.courseapi.service.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public Person add(@RequestBody Person person) {
        return personService.add(person);
    }
    @GetMapping
    public List<Person> getAll() {return personService.getAll();}

    @GetMapping("/{id}")
    public Person getById(@PathVariable("id") Long id) {
        return personService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable ("id") Long id) {
        personService.deleteById(id);
    }

    @PutMapping
    public Person update(@RequestBody Person person) {
        return personService.update(person);
    }
}
