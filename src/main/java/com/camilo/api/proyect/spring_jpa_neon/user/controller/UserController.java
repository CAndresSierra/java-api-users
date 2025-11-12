package com.camilo.api.proyect.spring_jpa_neon.user.controller;

import com.camilo.api.proyect.spring_jpa_neon.user.model.User;
import com.camilo.api.proyect.spring_jpa_neon.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        if(this.service.findAllUsers() != null){
            List<User> users = this.service.findAllUsers();
            return ResponseEntity.ok(users);
        }
        return ResponseEntity.status(404).body(new ApiErrorResponse("No users found"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>  getUserById(@PathVariable Long id) {
        User userFound = service.findUserById(id);
        if(userFound != null){
            return ResponseEntity.ok(userFound);
        }
        return ResponseEntity.status(404).body(new ApiErrorResponse("User not exist"));
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = this.service.createUser(user);
        return ResponseEntity
                .status(201)
                .body(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updated = this.service.updateUserById(id, user);
        if (updated == null) {
            return ResponseEntity
                    .status(404)
                    .body(new ApiErrorResponse("Unable to update, user not found"));
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User userFound = this.service.findUserById(id);
        if(userFound == null){
            return ResponseEntity.status(404).body(new ApiErrorResponse("User with id: " + id + " not found"));
        }
        service.deleteUserById(id);
        return ResponseEntity.status(204).build();
    }
}