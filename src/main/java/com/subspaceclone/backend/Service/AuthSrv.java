package com.subspaceclone.backend.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.subspaceclone.backend.ErrorHandlers.GlobalExceptionHandler.CustomError;
import com.subspaceclone.backend.Model.Company;
import com.subspaceclone.backend.Model.User;
import com.subspaceclone.backend.Repository.UserRepo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class AuthSrv {

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private CompanySrv companySrv;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // signup-user
    public User signupUser(String name, String email, String password, String photoURL, String desc,
            Integer companyId) {

        Company company = companySrv.getCompany(companyId);
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder().encode(password));
        user.setPhotoURL(photoURL);
        user.setDescr(desc);
        user.setCompany(company);
        return userRepository.save(user);
    }

    public User loginUser(String email, String password, Integer companyId) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (!optionalUser.isPresent() || optionalUser.get().getCompany().getId() != companyId) {
            throw new CustomError(404, "User Not Found");
        }
        User user = optionalUser.get();
        if (!passwordEncoder().matches(password, user.getPassword())) {
            throw new CustomError(404, "Invalid Credentials");
        }
        return user;
    }
}
