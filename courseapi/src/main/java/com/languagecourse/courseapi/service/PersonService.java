package com.languagecourse.courseapi.service;

import com.languagecourse.courseapi.entity.Person;
import com.languagecourse.courseapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;


    public Person add(Person person) {
        return personRepository.save(person);
    }

    public List<Person> getAll() {
        return personRepository.findAll();
    }

    public Person getById(Long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) return optionalPerson.get();
        else throw new RuntimeException("Person does not exist");
    }

    public void deleteById(Long id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()){
            personRepository.deleteById(id);
        }
        else throw new RuntimeException("Id does not exist");
    }

    public Person update(Person person) {
        return personRepository.save(person);
    }
}
