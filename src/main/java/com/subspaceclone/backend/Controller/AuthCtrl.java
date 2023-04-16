package com.subspaceclone.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.subspaceclone.backend.Model.User;
import com.subspaceclone.backend.Service.AuthSrv;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthCtrl {

    @Autowired
    private AuthSrv authService;

    record SignupUserRequest(String name, String email, String password, String photoURL, String desc,
            Integer companyId) {
    }

    record LoginUserRequest(String email, String password, Integer companyId) {
    }

    // signup-user
    @PostMapping("/signup")
    private User signupUser(@RequestBody SignupUserRequest request) {
        return authService.signupUser(request.name(), request.email(), request.password(), request.photoURL(),
                request.desc(), request.companyId());
    }

    // login-user
    @PostMapping("/login")
    private User loginUser(@RequestBody LoginUserRequest request) {
        return authService.loginUser(request.email(), request.password(), request.companyId());
    }
}
