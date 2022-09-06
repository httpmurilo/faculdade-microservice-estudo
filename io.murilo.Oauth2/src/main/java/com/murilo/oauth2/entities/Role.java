package com.murilo.oauth2.entities;

import java.io.Serializable;

public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String roleName;

    public Role(Long id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public Role() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
