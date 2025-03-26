package com.capgemini.onlinelenscart.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capgemini.onlinelenscart.DTO.LoginRequest;
import com.capgemini.onlinelenscart.Exception.InvalidUserException;
import com.capgemini.onlinelenscart.Exception.UserNotFoundException;
import com.capgemini.onlinelenscart.Jwt.JwtUtil;
import com.capgemini.onlinelenscart.Repository.UserRepository;
import com.capgemini.onlinelenscart.entities.UserRole;
import com.capgemini.onlinelenscart.entities.Users;

import lombok.RequiredArgsConstructor;

/**
 * This class provides the business logic for user management,
 * including user registration, login authentication, fetching user details, 
 * and deleting users.
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    // Logger to track operations and log important events
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    // Dependencies injected via constructor (Handled by Lombok @RequiredArgsConstructor)
    private final UserRepository userRepository; // Repository for database operations
    private final PasswordEncoder passwordEncoder; // Encrypts passwords before storing
    private final AuthenticationManager authenticationManager; // Handles authentication
    private final JwtUtil jwtUtil; // Generates JWT tokens for authentication

    /**
     * Registers a new user with the given details.
     * Encrypts the password before saving and assigns roles.
     */
    public Users registerUser(String firstName, String lastName, String username, String password, String email, Set<UserRole> roles) {
        // Basic validation to ensure required fields are not null
        if (username == null || password == null || email == null) {
            logger.error("Invalid registration attempt: Missing required fields");
            throw new InvalidUserException("Username, password, or email cannot be null");
        }

        // Encrypt the password to ensure security before storing it
        String encryptedPassword = passwordEncoder.encode(password);

        // Creating a new user object with the provided details
        Users user = new Users();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(encryptedPassword); // Storing encrypted password
        user.setEmail(email);
        user.setRoles(roles); // Assigning roles (e.g., CUSTOMER, ADMIN)

        // Saving the new user into the database
        Users savedUser = userRepository.save(user);
        logger.info("User registered successfully with username: {}", username);
        return savedUser;
    }

    /**
     * Handles user login by verifying the username and password.
     * If authentication is successful, generates a JWT token.
     */
    @Override
    public String login(LoginRequest loginRequest) {
        logger.info("Login attempt for username: {}", loginRequest.getUsername());
        
        try {
            // Creating an authentication token with the provided username and password
            UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());

            // Authenticate the user using Spring Security
            var authentication = authenticationManager.authenticate(token);

            // If authentication is successful, generate a JWT token for the user
            if (authentication.isAuthenticated()) {
                String jwt = jwtUtil.generateToken(loginRequest.getUsername());
                logger.info("Login successful for username: {}", loginRequest.getUsername());
                return jwt; // Returning JWT token
            }
        } catch (Exception e) {
            logger.error("Login failed for username: {} - Error: {}", loginRequest.getUsername(), e.getMessage());
        }

        // If authentication fails, throw an exception
        throw new InvalidUserException("Invalid username or password");
    }

    /**
     * Retrieves a list of all registered users from the database.
     */
    public List<Users> listUsers() {
        logger.info("Fetching all users from the database");
        return userRepository.findAll();
    }

    /**
     * Fetches a single user by their unique ID.
     * Throws an exception if the user is not found.
     */
    public Users getUserById(Long id) {
        logger.info("Fetching user with ID: {}", id);
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.error("User not found with ID: {}", id);
                    return new UserNotFoundException("User not found with id: " + id);
                });
    }

    /**
     * Deletes a user from the database using their ID.
     * If the user does not exist, throws an exception.
     */
    public boolean deleteUser(Long userId) {
        logger.info("Attempting to delete user with ID: {}", userId);

        // Check if the user exists in the database
        Optional<Users> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            userRepository.delete(userOpt.get()); // Delete user if found
            logger.info("User deleted successfully with ID: {}", userId);
            return true;
        }

        // If user is not found, throw an exception
        logger.error("User deletion failed - User not found with ID: {}", userId);
        throw new UserNotFoundException("User with ID " + userId + " not found");
    }

    /**
     * Fetches a list of all users who have the CUSTOMER role.
     */
    public Set<Users> listCustomers() {
        logger.info("Fetching all customers from the database");
        Set<Users> customers = new HashSet<>();
        
        // Iterate through all users and check their roles
        for (Users user : userRepository.findAll()) {
            if (user.getRoles().contains(UserRole.CUSTOMER)) {
                customers.add(user); // Add to the customer list if they have the CUSTOMER role
            }
        }

        logger.info("Total customers found: {}", customers.size());
        return customers;
    }
}

