package com.languagecourse.courseapi.service;

import com.languagecourse.courseapi.entity.Person;
import com.languagecourse.courseapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Person getPersonById(Long id) {
        if(personRepository.findById(id).isPresent()){
            return personRepository.getById(id);
        } else throw new IllegalArgumentException("id does not exist");
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id); // ha nincs ilyen id runtime error?
    }

    public Person update(Person person) {
        return personRepository.save(person);
    }
}
