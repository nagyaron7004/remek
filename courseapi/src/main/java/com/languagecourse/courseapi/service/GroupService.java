package com.languagecourse.courseapi.service;

import com.languagecourse.courseapi.entity.Group;
import com.languagecourse.courseapi.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


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
        if(groupRepository.existsById(id)){
            groupRepository.deleteById(id);
        }else {
            throw new RuntimeException("Id not found!");
        }
    }

    public Group update(Group group, long id) {
        if (!groupRepository.existsById(id)){
            return null;
        }
        Group updatedGroup = getById(id);
        updatedGroup.setId(id);
        updatedGroup.setCourse(group.getCourse());
        updatedGroup.setPerson(group.getPerson());
        return updatedGroup;

    }

}
