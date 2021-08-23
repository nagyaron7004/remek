package com.languagecourse.courseapi.controller;

import com.languagecourse.courseapi.entity.Group;
import com.languagecourse.courseapi.entity.Person;
import com.languagecourse.courseapi.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    /*public PersonController(PersonService personService) {
        this.personService = personService;
    }*/

    @PostMapping
    public ResponseEntity<?> add(@RequestBody @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("Bad request!");
            return ResponseEntity.badRequest().build();
        }
        log.info("Person saved " + person.getName() + "!");
        return ResponseEntity.ok(personService.add(person));
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        log.info("Person found");
        List<Person> persons = personService.getAll();
        return ResponseEntity.ok(persons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
        Person person = personService.getById(id);
        if (person == null) {
            log.error("Id not exist: " + id + "!");
            return ResponseEntity.notFound().build();
        }
        log.info("Person found: " + person.getName() + "!");
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ("id") Long id) {
        try {
            personService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();

        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateById(@RequestBody @Valid Person person, @PathVariable  long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        Person person1 = personService.getById(id);
        if (person1 == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(personService.updateById(person1, id));
    }

    @PutMapping
    public ResponseEntity<?> update (@RequestBody @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(personService.update(person));
    }



    @GetMapping("/{id}/groups")
    public List<Group> getGroupByPersonId (@PathVariable long id) {
        log.info("Getting groups by Person id" + id + "!");
        return personService.getGroupByPersonId(id);
    }
}
