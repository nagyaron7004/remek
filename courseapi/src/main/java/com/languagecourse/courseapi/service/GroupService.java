package com.languagecourse.courseapi.service;

import com.languagecourse.courseapi.entity.Group;
import com.languagecourse.courseapi.entity.Person;
import com.languagecourse.courseapi.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

    public Group add(Group group) {
        return groupRepository.save(group);
    }

    public List<Group> getAll() {
        return groupRepository.findAll();
    }

    public Group getById(Long id) {
        if(groupRepository.findById(id).isPresent()){
            return groupRepository.getById(id);
        } else throw new IllegalArgumentException("id does not exist");
    }

    public void deleteById(Long id) {
        groupRepository.deleteById(id); // ha nincs ilyen id runtime error?
    }

    public Group update(Group group) {
        return groupRepository.save(group);
    }

  /*  public List<Person> getAllPersonByGroupId (Long id) {

        List<Person> persons = new ArrayList<>();
        persons = (List<Person>) groupRepository.findById(id)
        .stream()
        ;

                //findById(id).orElseThrow().getPerson();
        return persons;
    }*/

}
