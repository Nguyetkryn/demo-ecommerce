package com.example.demoecommerce.entity;

import com.example.demoecommerce.entity.base.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Roles extends AbstractEntity {
    @Id
    private String roleId;
    @Column(name = "roleName")
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private List<Users> users = new ArrayList<>();

    public String getRoleName(){
        return this.roleName;
    }
}
