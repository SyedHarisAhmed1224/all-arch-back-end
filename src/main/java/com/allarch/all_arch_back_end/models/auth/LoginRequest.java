package com.allarch.all_arch_back_end.models.auth;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor
@ToString @Getter @Setter
public class LoginRequest {
    private String emailAddress;
    private String password;
}
