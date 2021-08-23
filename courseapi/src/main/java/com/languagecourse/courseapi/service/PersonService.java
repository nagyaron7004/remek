package com.languagecourse.courseapi.service;

import com.languagecourse.courseapi.entity.Group;
import com.languagecourse.courseapi.entity.Person;
import com.languagecourse.courseapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

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

    public Person updateById(Person person, long id) {
        if (!personRepository.existsById(id)) {
            return null;
        }
        Person PersonToUpdate = personRepository.getById(id);
        PersonToUpdate.setAge(person.getAge());
        PersonToUpdate.setEmail(person.getEmail());
        PersonToUpdate.setName(person.getName());

        return PersonToUpdate;
    }

    public Person update (Person person) {
        if (!personRepository.existsById(person.getId())){
            return null;
        }
        return personRepository.save(person);
    }

    public List<Group> getGroupByPersonId (long id) {
        List<Group> groups = personRepository.findById(id).orElseThrow().getGroups();
        return groups.stream().collect(Collectors.toList());
    }
}
