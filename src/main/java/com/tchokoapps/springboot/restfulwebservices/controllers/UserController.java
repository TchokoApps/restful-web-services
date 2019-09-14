package com.tchokoapps.springboot.restfulwebservices.controllers;

import com.tchokoapps.springboot.restfulwebservices.entities.User;
import com.tchokoapps.springboot.restfulwebservices.services.UserDaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Set;

@RestController
@Slf4j
public class UserController {

    private UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping("/users")
    public Set<User> retrieveAllUsers() {
        log.info("retrieveAllUsers called");
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable long id) {
        log.info("retrieveUser called");
        return userDaoService.findOne(Long.valueOf(id));
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        log.info("createUser called");
        User savedUser = userDaoService.save(user);
        log.info("user with id: " + savedUser.getId() + " successfully created");
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable long id) {
       userDaoService.deleteById(Long.valueOf(id));
    }
}
