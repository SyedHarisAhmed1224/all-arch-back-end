package com.allarch.all_arch_back_end.models.user;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class UserTokenRequest {
    private String email;
}
