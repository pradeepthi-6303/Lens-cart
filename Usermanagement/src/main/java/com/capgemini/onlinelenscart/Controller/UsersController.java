package com.capgemini.onlinelenscart.Controller;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.onlinelenscart.DTO.LoginRequest;
import com.capgemini.onlinelenscart.DTO.UserDTO;
import com.capgemini.onlinelenscart.Service.UserService;
import com.capgemini.onlinelenscart.entities.Users;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping(value = "/v1/api/user")
@RequiredArgsConstructor
public class UsersController {

    // Logger to keep track of application events
    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    // Service to handle user-related business logic
    private final UserService userService;

    
     // Registers a new user.
     // @param userDTO Contains user details like name, email, and password.
     // @return The newly registered user.
     
    @PostMapping("/register")
    public Users registerUser(@RequestBody @Valid UserDTO userDTO) {
        logger.info("Registering user: {}", userDTO.getUsername());

        // Calls the service to create a new user
        Users registeredUser = userService.registerUser(
            userDTO.getFirstName(),
            userDTO.getLastName(),
            userDTO.getUsername(),
            userDTO.getPassword(),
            userDTO.getEmail(),
            userDTO.getRoles()
        );

        logger.info("User registered successfully with ID: {}", registeredUser.getId());
        return registeredUser;
    }

    
     // Authenticates a user by checking the provided credentials.
     // @param loginRequest Contains username and password.
    // @return A JWT token if login is successful.
     
    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest) {
        logger.info("Login attempt for user: {}", loginRequest.getUsername());

        // Calls service to authenticate and generate JWT token
        String jwtToken = userService.login(loginRequest);

        logger.info("Login successful for user: {}", loginRequest.getUsername());
        return new ResponseEntity<>(jwtToken, HttpStatusCode.valueOf(201));
    }


// Retrieves a list of all registered users.
// @return A list of users.

    @GetMapping("/allUsers")
    public List<Users> listUsers() {
        logger.info("Fetching all users");

        // Calls service to get all users
        List<Users> users = userService.listUsers();

        logger.info("Total users found: {}", users.size());
        return users;
    }

    
     // Retrieves a list of all customers.
     // @return A set of users who have the CUSTOMER role.
     
    @GetMapping("/customers")
    public Set<Users> listCustomers() {
        logger.info("Fetching all customers");

        // Calls service to get users who are customers
        Set<Users> customers = userService.listCustomers();

        logger.info("Total customers found: {}", customers.size());
        return customers;
    }

    
     // Deletes a user by their ID. This is only allowed for admins.
     // @param userId The ID of the user to be deleted.
     // @return A success or failure message.
     
    @DeleteMapping("/admin/delete/{userId}")
    public String deleteUser(@PathVariable @Min(value = 1, message = "User ID should be positive") Long userId) {
        logger.info("Deleting user with ID: {}", userId);

        // Calls service to delete the user
        boolean isDeleted = userService.deleteUser(userId);

        if (isDeleted) {
            logger.info("User with ID: {} deleted successfully", userId);
            return "User deleted successfully";
        } else {
            logger.warn("User with ID: {} not found", userId);
            return "User not found";
        }
    }
}


