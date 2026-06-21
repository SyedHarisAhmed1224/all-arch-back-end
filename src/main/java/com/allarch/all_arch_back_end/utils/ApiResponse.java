package com.allarch.all_arch_back_end.utils;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
public class ApiResponse<T> implements Serializable {

    private int responseCode;
    private String responseCodeDesc;
    private T body;

    private static final int SUCCESS = 200;
    private static final int BAD_REQUEST = 400;
    private static final int SERVER_ERROR = 500;

    public static <T> ResponseEntity<ApiResponse<T>> success(T body) {
        return new ResponseEntity<>(
                new ApiResponse<>(SUCCESS, "SUCCESS", body),
                HttpStatus.OK
        );
    }

    public static <T> ResponseEntity<ApiResponse<T>> badRequest(T body) {
        return new ResponseEntity<>(
                new ApiResponse<>(BAD_REQUEST, "BAD_REQUEST", body),
                HttpStatus.OK
        );
    }

    public static <T> ResponseEntity<ApiResponse<T>> serverError(T body) {
        return new ResponseEntity<>(
                new ApiResponse<>(SERVER_ERROR, "SERVER_ERROR", body),
                HttpStatus.OK
        );
    }
}