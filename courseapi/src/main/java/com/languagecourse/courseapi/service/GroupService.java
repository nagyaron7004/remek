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
        groupRepository.deleteById(id);
    }

    public Group update(Group group) {
        return groupRepository.save(group);
    }

}
