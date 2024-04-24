package com.example.demoecommerce.service.impl;

import com.example.demoecommerce.entity.Users;
import com.example.demoecommerce.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Service
//@AllArgsConstructor
public class UserDetailsServiceImpl  {
//    @Autowired
//    private IUserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
//        Users user = userRepository.findByUsername(username);
//        if (user == null){
//            throw new UsernameNotFoundException("User not found!");
//        }
//        //return new Users(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
//        return new MyUserPrincipal(user);
//    }
//
//    /*private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Roles> roles){
//        return roles.stream()
//                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
//                .collect(Collectors.toList());
//    }*/
}
