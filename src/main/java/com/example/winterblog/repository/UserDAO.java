package com.example.winterblog.repository;

import com.example.winterblog.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
    List<User> findUserByUsernameIsStartingWith(String username);
}
