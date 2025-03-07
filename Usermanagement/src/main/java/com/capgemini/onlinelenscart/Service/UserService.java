package com.capgemini.onlinelenscart.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.onlinelenscart.Exception.InvalidUserException;
import com.capgemini.onlinelenscart.Exception.UserNotFoundException;
import com.capgemini.onlinelenscart.Repository.UserRepository;
import com.capgemini.onlinelenscart.entities.User;
import com.capgemini.onlinelenscart.entities.UserRole;

@Service
public class UserService {
 
    @Autowired
    private UserRepository userRepository;
 
    // Register a new user
    public User registerUser(String username, String password, String email, Set<UserRole> roles) {
    	if (username == null || password == null || email == null) {
            throw new InvalidUserException("Username, password, or email cannot be null");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // In production, don't forget to hash the password
        user.setEmail(email);
        user.setRoles(roles);
        return userRepository.save(user);
    }
 
    // Login method - (for simplicity, just check if username exists)
    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User with username " + username + " not found");
        }

        if (!user.getPassword().equals(password)) {
            throw new InvalidUserException("Incorrect password for username " + username);
        }

        return user;

    }
    public Set<User> listCustomers() {
    	 
        Set<User> customers = new HashSet<>();
 
        for (User user : userRepository.findAll()) {
 
            if (user.getRoles().contains(UserRole.CUSTOMER)) {
 
                customers.add(user);
 
            }
 
        }
 
        return customers;
 
    }
 
    // Admin functionalities - List all customers
    public List<User> listUsers() {
        
        return userRepository.findAll();
    }
    public User getUserById(Integer id) {
    	return userRepository.findById(id)
    			.orElseThrow(()-> new UserNotFoundException("User not found with id: "+id));
    	
    }
 
  
    // Delete a customer
    public boolean deleteUser(Integer userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (!userOpt.isPresent()) {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
        userRepository.delete(userOpt.get());
        return true;
    }
 
    // Update a user's role
    public User updateUserRole(Integer userId, Set<UserRole> newRoles) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (!userOpt.isPresent()) {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
        User user = userOpt.get();
        user.setRoles(newRoles);
        return userRepository.save(user);
    }
}
