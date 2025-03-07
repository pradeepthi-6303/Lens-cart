package com.capgemini.onlinelenscart.Controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.capgemini.onlinelenscart.DTO.UserDTO;
import com.capgemini.onlinelenscart.Exception.UserNotFoundException;
import com.capgemini.onlinelenscart.Service.UserService;
import com.capgemini.onlinelenscart.entities.User;
import com.capgemini.onlinelenscart.entities.UserRole;

import jakarta.validation.Valid;
@Validated
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    // Register a new user
    @PostMapping("/register")
    public User registerUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.registerUser(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail(), userDTO.getRoles());
    }

    // User login
    @PostMapping("/login")
    public User loginUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.loginUser(userDTO.getUsername(), userDTO.getPassword());
    }

    //  List all users
    @GetMapping("/allUsers")
    public List<User> listUsers() {
        return userService.listUsers();
    }
 // Admin: List all customers
    @GetMapping("/customers")
    public Set<User> listCustomers() {
        return userService.listCustomers();
    }
   
 
    
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id){
    	return userService.getUserById(id);}

    // Admin: Delete a user by ID
    @DeleteMapping("/admin/delete/{userId}")
    public String deleteUser(@PathVariable Integer userId) {
    	if (!userService.deleteUser(userId)) {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
        return "User deleted successfully";
    }

    // Admin: Update user's role
    @PutMapping("/admin/update/{userId}")
    public User updateUserRole(@PathVariable Integer userId, @RequestBody Set<UserRole> newRoles) {
        return userService.updateUserRole(userId, newRoles);
    }
}