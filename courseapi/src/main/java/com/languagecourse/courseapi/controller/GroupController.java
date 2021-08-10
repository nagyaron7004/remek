package com.languagecourse.courseapi.controller;

import com.languagecourse.courseapi.entity.Group;
import com.languagecourse.courseapi.entity.Person;
import com.languagecourse.courseapi.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//
// getAllPersonByGroupId


@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping
    public Group add(@RequestBody Group group) {
        return groupService.add(group);
    }
    @GetMapping
    public List<Group> getAll() {return groupService.getAll();}

    @GetMapping("/{id}")
    public Group getById(@PathVariable("id") Long id) {
        return groupService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable ("id") Long id) {
        groupService.deleteById(id);
    }

    @PutMapping
    public Group update(@RequestBody Group group) {
        return groupService.update(group);
    }


}
