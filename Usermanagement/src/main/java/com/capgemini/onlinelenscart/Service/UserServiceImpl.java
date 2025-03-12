package com.capgemini.onlinelenscart.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.capgemini.onlinelenscart.DTO.LoginRequest;
import com.capgemini.onlinelenscart.DTO.UserDTO;
import com.capgemini.onlinelenscart.Exception.InvalidUserException;
import com.capgemini.onlinelenscart.Exception.UserNotFoundException;
import com.capgemini.onlinelenscart.Jwt.JwtUtil;
import com.capgemini.onlinelenscart.Repository.UserRepository;
import com.capgemini.onlinelenscart.entities.UserRole;
import com.capgemini.onlinelenscart.entities.Users;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    
//    @Override
//    public String createCustomer(UserDTO userDTO) {
//        var customer=userRepository.save(Users.builder()
//                .password(passwordEncoder.encode(userDTO.getPassword()))
//                .username(userDTO.getUsername())
//                .roles(userDTO.getRoles())
//                .email(userDTO.getEmail())
//                .firstName(userDTO.getFirstName())
//                .lastName(userDTO.getLastName())
//                .build());
//        return "Customer created with ID ::"+customer.getId();
//    }
    public Users registerUser(String firstName, String lastName, String username, String password, String email, Set<UserRole> roles) {
        if (username == null || password == null || email == null) {
            throw new InvalidUserException("Username, password, or email cannot be null");
        }

      
        String encryptedPassword = passwordEncoder.encode(password);

 
        Users user = new Users();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setPassword(encryptedPassword);  // Set the encrypted password
        user.setEmail(email);
        user.setRoles(roles);

        
        return userRepository.save(user);
    }

    @Override
    public String login(LoginRequest loginRequest) {

        UsernamePasswordAuthenticationToken token=new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
        var authentication=authenticationManager.authenticate(token);
        var isValid=authentication.isAuthenticated();
        if(isValid){
            return jwtUtil.generateToken(loginRequest.getUsername());
        }
        return "Login Failed";
    }
 // Admin functionalities - List all customers
  public List<Users> listUsers() {
      
      return userRepository.findAll();
  }
  public Users getUserById(Long id) {
  	return userRepository.findById(id)
  			.orElseThrow(()-> new UserNotFoundException("User not found with id: "+id));
  	
  }


  // Delete a customer
  public boolean deleteUser(Long userId) {
      Optional<Users> userOpt = userRepository.findById(userId);
      if (!userOpt.isPresent()) {
          throw new UserNotFoundException("User with ID " + userId + " not found");
      }
      userRepository.delete(userOpt.get());
      return true;
  }

  // Update a user's role
  public Users updateUserRole(Long userId, Set<UserRole> newRoles) {
      Optional<Users> userOpt = userRepository.findById(userId);
      if (!userOpt.isPresent()) {
          throw new UserNotFoundException("User with ID " + userId + " not found");
      }
      Users user = userOpt.get();
      user.setRoles(newRoles);
      return userRepository.save(user);
  }
  public Set<Users> listCustomers() {
      Set<Users> customers = new HashSet<>();
      for (Users user : userRepository.findAll()) {
          if (user.getRoles().contains(UserRole.CUSTOMER)) {
              customers.add(user);
          }
      }
      return customers;
  }


 
}
