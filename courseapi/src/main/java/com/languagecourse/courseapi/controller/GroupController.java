package com.languagecourse.courseapi.controller;

import com.languagecourse.courseapi.entity.Group;
import com.languagecourse.courseapi.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody @Valid Group group, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        log.info("New group saved: " + group.getId() + "!");

        return ResponseEntity.ok(groupService.add(group));
    }


    @GetMapping
    public ResponseEntity<?> getAll() {
        List<Group> groups = groupService.getAll();
        log.info("Groups found!");

        return ResponseEntity.ok(groups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {
       Group group = groupService.getById(id);
       if (group == null) {
           log.error("Group id not found: " + id + "!");
           return ResponseEntity.notFound().build();
       }
       log.info("Group found: " + id + "!");
       return ResponseEntity.ok(group); //groupService.getById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ("id") Long id) {
        log.info("Deleting group by id!");
        try {
            groupService.deleteById(id);
            log.info("Group deleted: " + id + "!" );
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            log.error("Group not found!");
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid Group group, @PathVariable long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }
        Group result = groupService.update(group, id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }


}
