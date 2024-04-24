package com.example.demoecommerce.service;

import com.example.demoecommerce.dto.LoginDTO;
import com.example.demoecommerce.dto.UserResponse;

public interface IUser {
    UserResponse login(LoginDTO loginDTO);
}
