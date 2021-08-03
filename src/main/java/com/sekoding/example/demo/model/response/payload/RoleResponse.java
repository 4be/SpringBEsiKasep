package com.sekoding.example.demo.model.response.payload;

import com.sekoding.example.demo.model.entity.ERole;

public class RoleResponse {

    private Integer id;
    private ERole rolename;

    public RoleResponse(Integer id, ERole rolename) {
        this.id = id;
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
