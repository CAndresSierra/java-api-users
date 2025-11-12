package com.camilo.api.proyect.spring_jpa_neon.user.service;

import com.camilo.api.proyect.spring_jpa_neon.user.model.User;
import org.hibernate.FetchNotFoundException;


import java.util.List;


public interface UserService {
    List<User> findAllUsers();
    User findUserById(Long id);
    User createUser(User newUser);
    void deleteUserById(Long id);
    User updateUserById(Long id, User updateUser);
}
