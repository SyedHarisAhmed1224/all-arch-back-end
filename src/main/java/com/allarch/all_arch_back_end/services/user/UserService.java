package com.allarch.all_arch_back_end.services.user;

import com.allarch.all_arch_back_end.models.user.UserTokenRequest;
import com.allarch.all_arch_back_end.utils.ApiResponse;
import com.allarch.all_arch_back_end.utils.JwtUtil;
import com.allarch.all_arch_back_end.utils.SQL;
import com.allarch.all_arch_back_end.utils.Scripts;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private SQL sql;

    @Autowired
    private JwtUtil jwtUtil;

    public ResponseEntity generateToken(UserTokenRequest userTokenRequest, HttpServletResponse response) {
        var res = sql.execute(Scripts.getUserIDQuery(), userTokenRequest.getEmail());

        if (res == null) {
            return ApiResponse.badRequest("User Not Found");
        }

        try {
            var userID = Integer.parseInt(res.get(0).get("UserID").toString());

            var token = jwtUtil.generateToken(userTokenRequest.getEmail(), userID);

            var tokenRes = sql.executeQueryOnce(Scripts.getUpdateUserSessionQuery(), userID, token);

            if (tokenRes == null) {
                return ApiResponse.badRequest("User Not Found");
            }

            ResponseCookie cookie = ResponseCookie.from("ACCESS_TOKEN", token)
                    .httpOnly(true)
//                    .secure(true)
                    .path("/")
                    .domain("192.168.100.7")
//                    .domain("allarachacademy.com")
                    .sameSite("Lax")
                    .maxAge(60 * 60)
                    .build();

            response.addHeader("Set-Cookie", cookie.toString());

            return ApiResponse.success("Success");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ApiResponse.serverError("Server Error");
    }

}
