package com.allarch.all_arch_back_end.security;

import com.allarch.all_arch_back_end.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;

    Logger logger = LoggerFactory.getLogger(JwtAuthenticationFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("doFilterInternal");

        String path = request.getRequestURI();
        logger.info("path: " + path);

        if (path.startsWith("/allarch/auth") || path.startsWith("/allarch/user/generate-token") || path.startsWith("/allarch/landing-page")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = null;

        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                logger.info("COOKIE: " + cookie.getName());
                if (cookie.getName().equals("ACCESS_TOKEN")) {
                    token = cookie.getValue();
                    break;
                }
            }
        }

        if (token == null) {
            response.sendError(
                    HttpServletResponse.SC_UNAUTHORIZED,
                    "Access Token Missing or Invalid"
            );
            return;
        }

        try {
            Claims claims = jwtUtil.validateToken(token);

            String email = claims.getSubject();
            Integer userId = claims.get("userId", Integer.class);

            CustomUserPrincipal principal =
                    new CustomUserPrincipal(
                            userId,
                            email
                    );

            UsernamePasswordAuthenticationToken authentication =
                    UsernamePasswordAuthenticationToken.authenticated(
                            principal,
                            null,
                            AuthorityUtils.NO_AUTHORITIES
                    );

            logger.info(
                    "authenticated={}, authorities={}",
                    authentication.isAuthenticated(),
                    authentication.getAuthorities()
            );

            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);

        } catch (Exception ex) {

            response.sendError(
                    HttpServletResponse.SC_UNAUTHORIZED,
                    "Invalid Token"
            );
            return;
        }

        filterChain.doFilter(request, response);
    }
}