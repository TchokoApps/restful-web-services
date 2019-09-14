package com.tchokoapps.springboot.restfulwebservices.services;

import com.tchokoapps.springboot.restfulwebservices.entities.User;
import com.tchokoapps.springboot.restfulwebservices.exceptions.UserNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class UserDaoService {

    private static Set<User> users = new HashSet<>();
    private static Long usersCount = 3L;

    static {
        users.add(new User(1L, "Adam", new Date()));
        users.add(new User(2L, "Eve", new Date()));
        users.add(new User(3L, "Jack", new Date()));
    }

    public Set<User> findAll() {
        return users;
    }

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(Long id) {
        Optional<User> userOptional = users.stream().filter(user -> user.getId().equals(id)).findFirst();
        if (userOptional.isPresent()) {
            return userOptional.get();
        }
        throw new UserNotFoundException(String.format("User with id %s has not been found", id));
    }

    public void deleteById(Long id) {
        Optional<User> userOptional = users.stream().filter(user -> user.getId().equals(id)).findFirst();
        if (userOptional.isPresent()) {
            users.remove(userOptional.get());
        } else {
            throw new UserNotFoundException(String.format("User %s not found", id));
        }
    }
}
