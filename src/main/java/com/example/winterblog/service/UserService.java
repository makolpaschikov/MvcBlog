package com.example.winterblog.service;

import com.example.winterblog.domain.User;
import com.example.winterblog.domain.UserRole;
import com.example.winterblog.repository.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserDAO userDAO;

    @Autowired
    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Finds in the user base by his username
     * @param username - username of user
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.findUserByUsername(username);
    }

    public User getByName(String username) {
        return userDAO.findUserByUsername(username);
    }

    public List<User> getByNameStartWith(String username) {
        return userDAO.findUserByUsernameIsStartingWith(username);
    }

    public List<User> getAll() {
        return userDAO.findAll();
    }

    public List<User> getById(Long id) {
        return userDAO.findUserById(id);
    }

    public List<User> getAllByRole(UserRole role) {
        return userDAO.findAllByRoles(role);
    }

    public void deleteAll(List<User> users) {
        userDAO.deleteAll(users);
    }

    public void save(User usr) {
        userDAO.save(usr);
    }

}
