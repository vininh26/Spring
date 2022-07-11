package com.example.SpringPosgres;

import com.example.SpringPosgres.Model.Role;
import com.example.SpringPosgres.Model.UserModel;
import com.example.SpringPosgres.Service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringPosgresApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringPosgresApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(UserService userService) {
		return args -> {
			userService.saveRole(new Role(null,"ROLE_ADMIN"));
			userService.saveRole(new Role(null,"ROLE_MANAGER"));
			userService.saveRole(new Role(null,"ROLE_USER"));
			userService.saveRole(new Role(null,"ROLE_SUPPER_ADMIN"));

			userService.userSave(new UserModel(0,"ninhvv","123abc","donkey.n1160@gmail.com",0,"acsccc" , new ArrayList<>()));

			userService.addRoleToUser("ninhvv","ROLE_ADMIN");
		};
	}
}
