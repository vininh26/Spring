package com.example.SpringPosgres.Model;

import lombok.Data;

@Data
public class RoleToUser {
    private String userName ;
    private String roleName ;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
