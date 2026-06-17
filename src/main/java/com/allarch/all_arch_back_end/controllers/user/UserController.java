package com.allarch.all_arch_back_end.controllers.user;

import com.allarch.all_arch_back_end.models.user.UserTokenRequest;
import com.allarch.all_arch_back_end.services.user.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/allarch/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/generate-token", method = POST)
    public ResponseEntity generateToken(@RequestBody UserTokenRequest userTokenRequest, HttpServletResponse response) {
        return userService.generateToken(userTokenRequest, response);
    }

}
