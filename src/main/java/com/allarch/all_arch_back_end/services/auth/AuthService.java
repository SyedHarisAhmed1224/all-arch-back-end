package com.allarch.all_arch_back_end.services.auth;

import com.allarch.all_arch_back_end.models.auth.SignUpRequest;
import com.allarch.all_arch_back_end.utils.ApiResponse;
import com.allarch.all_arch_back_end.utils.SQL;
import com.allarch.all_arch_back_end.utils.Scripts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthService {

    @Autowired
    private SQL sql;

    public ResponseEntity register(@RequestBody SignUpRequest signUpRequest) {
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
    }

}
