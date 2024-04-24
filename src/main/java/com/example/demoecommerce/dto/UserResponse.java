package com.example.demoecommerce.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserResponse {
    private String id;
    private String fullName;
    private String accessToken;
    private List<String> role;


}
