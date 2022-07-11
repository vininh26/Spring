package com.example.SpringPosgres.Service;


import com.example.SpringPosgres.Model.Role;
import com.example.SpringPosgres.Model.UserModel;

import java.util.List;


public interface UserService {
    UserModel userSave(UserModel userModel);
    Role saveRole(Role role);
    void addRoleToUser(String username , String roleName);

    UserModel getUser(String username);

    List<UserModel> getUsers();
}
