package com.allarch.all_arch_back_end.controllers.auth;

import com.allarch.all_arch_back_end.models.auth.LoginRequest;
import com.allarch.all_arch_back_end.models.auth.SignUpRequest;
import com.allarch.all_arch_back_end.services.auth.AuthService;
import com.allarch.all_arch_back_end.utils.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/allarch/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/register", method = POST)
    public ResponseEntity register(@RequestBody SignUpRequest signUpRequest) {
        return authService.register(signUpRequest);
    }

    @RequestMapping(value = "/login", method = POST)
    public ResponseEntity login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        return authService.login(loginRequest, response);
    }

}
