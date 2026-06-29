package com.allarch.all_arch_back_end.utils;

import com.allarch.all_arch_back_end.security.CustomUserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {

    private SecurityUtils() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static CustomUserPrincipal getCurrentUser() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return null;
        }

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof CustomUserPrincipal)) {
            return null;
        }

        return (CustomUserPrincipal) principal;
    }

    public static Integer getCurrentUserId() {
        CustomUserPrincipal user = getCurrentUser();
        return user != null ? user.getUserId() : null;
    }

    public static String getCurrentUserEmail() {
        CustomUserPrincipal user = getCurrentUser();
        return user != null ? user.getEmail() : null;
    }
}