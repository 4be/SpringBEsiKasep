package com.sekoding.example.demo.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole rolename;

    public Role() {
    }

    public Role(ERole rolename) {
        this.rolename = rolename;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ERole getRolename() {
        return rolename;
    }

    public void setRolename(ERole rolename) {
        this.rolename = rolename;
    }
}
