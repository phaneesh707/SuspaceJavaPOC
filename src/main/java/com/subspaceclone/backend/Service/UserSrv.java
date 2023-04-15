package com.subspaceclone.backend.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.subspaceclone.backend.ErrorHandlers.GlobalExceptionHandler.CustomError;
import com.subspaceclone.backend.Model.User;
import com.subspaceclone.backend.Repository.UserRepo;

@Service
public class UserSrv {
    @Autowired
    private UserRepo userRepository;

    public User findUserById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {

            throw new CustomError(404, "User not found");
        }
        return user.get();
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    // delete User
    public User deleteUser(Integer id) {
        User user = findUserById(id);
        userRepository.delete(user);
        return user;
    }
}
