package com.allarch.all_arch_back_end.models.auth;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class SignUpRequest {
    private String emailAddress;
    private String password;
    private String firstName;
    private String lastName;
    private int interestFieldID;
}
