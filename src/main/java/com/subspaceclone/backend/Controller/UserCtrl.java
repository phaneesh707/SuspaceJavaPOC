package com.subspaceclone.backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subspaceclone.backend.Model.User;
import com.subspaceclone.backend.Service.UserSrv;

@RestController
@RequestMapping("/api/v1/user")
public class UserCtrl {

    @Autowired
    private UserSrv userSrv;

    @GetMapping
    public List<User> getAllUsers() {
        return userSrv.findAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id) {
        return userSrv.findUserById(id);
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable Integer id) {
        return userSrv.deleteUser(id);
    }
}
