package com.camilo.api.proyect.spring_jpa_neon.user.service;

import com.camilo.api.proyect.spring_jpa_neon.user.model.User;
import com.camilo.api.proyect.spring_jpa_neon.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> findAllUsers() {
       Optional<List<User>> users =  Optional.of(this.repository.findAll());
        return users.orElse(null);
    }

    @Override
    public User findUserById(Long id) {
        Optional<User> userFound = this.repository.findById(id);
        return userFound.orElse(null);
    }

    @Override
    public User createUser(User newUser) {
        return this.repository.save(newUser);
    }

    @Override
    public void deleteUserById(Long id) {
        Optional<User> userFound = this.repository.findById(id);
        userFound.ifPresent((this.repository::delete));
    }

    @Override
    public User updateUserById(Long id, User updateUser){
        return this.repository.findById(id)
                .map( user -> {
                    user.setEmail(updateUser.getEmail());
                    user.setName(updateUser.getName());
                    user.setLastname(updateUser.getLastname());
                    return this.repository.save(user);
                })
                .orElse(null);
    }
}
