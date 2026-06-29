package com.allarch.all_arch_back_end.services.auth;

import com.allarch.all_arch_back_end.models.auth.LoginRequest;
import com.allarch.all_arch_back_end.models.auth.SignUpRequest;
import com.allarch.all_arch_back_end.models.user.UserTokenRequest;
import com.allarch.all_arch_back_end.services.user.UserService;
import com.allarch.all_arch_back_end.utils.ApiResponse;
import com.allarch.all_arch_back_end.utils.SQL;
import com.allarch.all_arch_back_end.utils.Scripts;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private SQL sql;

    @Autowired
    private UserService userService;

    public ResponseEntity register(SignUpRequest signUpRequest) {
        try {
            var res = sql.executeQueryOnce(
                    Scripts.registerQuery(),
                    signUpRequest.getEmailAddress(),
                    signUpRequest.getPassword(),
                    signUpRequest.getFirstName(),
                    signUpRequest.getLastName(),
                    signUpRequest.getInterestFieldID()
            );

            if (res == null) {
                return ApiResponse.serverError("Internal Server Error");
            }

            if (res == 0) {
                return ApiResponse.badRequest("Bad Request");
            }

            return ApiResponse.success("User Created");
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.serverError(e.getMessage());
        }
    }

    public ResponseEntity login(LoginRequest loginRequest, HttpServletResponse response) {
        try {
            var res = sql.execute(Scripts.getLoginQuery(), loginRequest.getEmailAddress(), loginRequest.getPassword());

            if (res == null) {
                return ApiResponse.serverError("Internal Server Error");
            }

            if (res.size() == 0) {
                return ApiResponse.badRequest("Invalid Login Details");
            }

            return userService.generateToken(new UserTokenRequest(loginRequest.getEmailAddress()), response);
        } catch (Exception e) {
            e.printStackTrace();
            return ApiResponse.serverError("Internal Server Error");
        }
    }

}
