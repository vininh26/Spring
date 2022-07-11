package com.example.SpringPosgres.Service;

import com.example.SpringPosgres.Model.Role;
import com.example.SpringPosgres.Model.UserModel;
import com.example.SpringPosgres.Repositories.RoleRepository;
import com.example.SpringPosgres.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository ;
    private final RoleRepository roleRepository ;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel userModel = userRepository.findByUsername(username);
        if (userModel == null){
            log.error("User not four the in database");
            throw new UsernameNotFoundException("User not four the in database");
        }else {
            log.info("User found in the database {}" + username );
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userModel.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new User(userModel.getUsername(),userModel.getPassword(),authorities);
    }

    @Override
    public UserModel userSave(UserModel userModel) {
        log.info("Save to db ");
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        return userRepository.save(userModel);
    }

    @Override
    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        UserModel userModel = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        userModel.getRoles().add(role);
    }

    @Override
    public UserModel getUser(String username) {
        log.info("Find user db ");
        return userRepository.findByUsername(username);
    }

    @Override
    public List<UserModel> getUsers() {
        log.info("Find all db ");
        return userRepository.findAll();
    }
}
