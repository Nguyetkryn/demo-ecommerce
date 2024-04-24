package com.example.demoecommerce.service.user_detail;

import com.example.demoecommerce.entity.Users;
import com.example.demoecommerce.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByUsername(username);
        if(ObjectUtils.isEmpty(user)){
            throw new RuntimeException("OBJECT_IS_EMPTY");
        }
        return UserDetailImpl.mapRolesToAuthorities(user);
    }
}
