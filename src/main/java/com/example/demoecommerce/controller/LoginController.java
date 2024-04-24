package com.example.demoecommerce.controller;

import com.example.demoecommerce.dto.LoginDTO;
import com.example.demoecommerce.dto.ProductDTO;
import com.example.demoecommerce.dto.UserResponse;
import com.example.demoecommerce.jwt.JwtUtils;
import com.example.demoecommerce.service.IUser;
import com.example.demoecommerce.service.impl.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/no-auth/login")
@AllArgsConstructor
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    private IUser userService;
    @PostMapping
    public ResponseEntity<UserResponse> login(@RequestBody LoginDTO loginDTO) {
        return ResponseEntity.ok(userService.login(loginDTO));
    }
}
