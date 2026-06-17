package com.allarch.all_arch_back_end.services.user;

import com.allarch.all_arch_back_end.models.user.UserTokenRequest;
import com.allarch.all_arch_back_end.utils.ApiResponse;
import com.allarch.all_arch_back_end.utils.JwtUtil;
import com.allarch.all_arch_back_end.utils.SQL;
import com.allarch.all_arch_back_end.utils.Scripts;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

            var token = jwtUtil.generateToken(userTokenRequest.getEmail());

            var tokenRes = sql.executeQueryOnce(Scripts.getUpdateUserSessionQuery(), userID, token);

            if (tokenRes == null) {
                return ApiResponse.badRequest("User Not Found");
            }

            Cookie cookie = new Cookie("ACCESS_TOKEN", token);
            cookie.setHttpOnly(true);
            cookie.setSecure(true);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 60);

            response.addCookie(cookie);

            return ApiResponse.success("Success");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ApiResponse.serverError("Server Error");
    }

}
