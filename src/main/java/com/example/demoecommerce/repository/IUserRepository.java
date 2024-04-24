package com.example.demoecommerce.repository;

import com.example.demoecommerce.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<Users, String> {
    Users findByUsername(String username);
}
