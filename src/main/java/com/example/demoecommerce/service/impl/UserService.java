package com.example.demoecommerce.service.impl;

import com.example.demoecommerce.dto.LoginDTO;
import com.example.demoecommerce.dto.UserResponse;
import com.example.demoecommerce.jwt.JwtUtils;
import com.example.demoecommerce.service.IUser;
import com.example.demoecommerce.service.user_detail.UserDetailImpl;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService implements IUser {
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    @Override
    public UserResponse login(LoginDTO loginRequest) {
        if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailImpl userDetail = (UserDetailImpl) authentication.getPrincipal();
            String accessToken = jwtUtils.generateTokenByUsername(loginRequest.getUsername());
            List<String> roles = userDetail.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
            return new UserResponse(
                    userDetail.getUser().getUserId(),
                    userDetail.getUser().getFullName(),
                    accessToken, roles);
        } catch (BadCredentialsException ex) {
            SecurityContextHolder.clearContext();
            throw new RuntimeException("LOGIN FAILED");
        }
    }
}
