package com.example.demoecommerce.entity;

import com.example.demoecommerce.entity.base.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "users",
        uniqueConstraints = {@UniqueConstraint(columnNames = "username"),
                             @UniqueConstraint(columnNames = "email"),
                             @UniqueConstraint(columnNames = "phoneNumber")})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users extends AbstractEntity {
    @Id
    private String userId;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "fullName")
    private String fullName;
    @Column(name = "email")
    private String email;
    @Column(name = "address")
    private String address;
    @Column(name = "status")
    private String status;
    @Column(name = "phoneNumber")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
    private String phoneNumber;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "privilege",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    @JsonIgnoreProperties("users")
    private List<Roles> roles = new ArrayList<>();
    @OneToOne
    @JoinColumn(name = "cartId")
    private Cart cart;
    @ManyToMany(mappedBy = "users")
    private List<Payments> payments = new ArrayList<>();

    public String getUsername(){
       return this.username;
    };
    public String getPassword(){
        return this.password;
    }
}
