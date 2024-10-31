package com.example.ssjava.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class AuthResponseDto {
    private String token;
    private String email;
    private int userId;
}
