package com.camilo.api.proyect.spring_jpa_neon.user.repository;

import com.camilo.api.proyect.spring_jpa_neon.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
