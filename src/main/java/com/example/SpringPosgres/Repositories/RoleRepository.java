package com.example.SpringPosgres.Repositories;

import com.example.SpringPosgres.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String name);

}
