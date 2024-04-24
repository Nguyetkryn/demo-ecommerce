package com.example.demoecommerce.filter;

import com.example.demoecommerce.jwt.JwtUtils;
import com.example.demoecommerce.service.user_detail.UserDetailImpl;
import com.example.demoecommerce.service.user_detail.UserDetailService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// lớp này để mỗi khi người dùng login thì hệ thống
// sẽ kiểm tra xem chuỗi token của người dùng có thông tin gì
// và quyền hạn như nào , sau đó nó giao tiếp với spring security
// để tuỳ biến người dùng đó được phép sử dụng những phần nào
@Service
public class AuthTokenFilter extends OncePerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(AuthTokenFilter.class);
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailService userDetailService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = extractToken(request);
            if (StringUtils.hasText(token) && jwtUtils.validateToken(token)) {
                String username = jwtUtils.getUserByToken(token);
                UserDetailImpl userDetails = (UserDetailImpl) userDetailService.loadUserByUsername(username);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails.getUsername(), null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else {
                SecurityContextHolder.clearContext();
            }
        } catch (Exception e) {
            LOG.error("Authentication failed : " + e.getMessage());
        }
        filterChain.doFilter(request, response);
    }

    public String extractToken(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization");
        if (authToken != null && authToken.startsWith("Bearer ")) {
            return authToken.substring(7);
        }
        return null;
    }
}
