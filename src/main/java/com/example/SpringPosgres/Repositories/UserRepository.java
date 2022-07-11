package com.example.SpringPosgres.Repositories;

import com.example.SpringPosgres.Model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<UserModel,Long> {

    UserModel findByUsername(String username);

}
