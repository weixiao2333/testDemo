package com.example.service;

import com.example.entity.User;

import java.util.List;

/**
 * ????
 */
public interface UserService {
    
    /**
     * ? ID ???
     */
    User getUserById(Long id);
    
    /**
     * ??????
     */
    List<User> getAllUsers();
    
    /**
     * ???????
     */
    List<User> searchByUsername(String username);
    
    /**
     * ???
     */
    User createUser(User user);
    
    /**
     * ???
     */
    User updateUser(User user);
    
    /**
     * ??
     */
    boolean deleteUser(Long id);
}
