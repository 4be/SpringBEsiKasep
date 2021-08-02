package com.sekoding.example.demo.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "tbl_roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRole;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole nameRole;

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public ERole getNameRole() {
        return nameRole;
    }

    public void setNameRole(ERole nameRole) {
        this.nameRole = nameRole;
    }

}
