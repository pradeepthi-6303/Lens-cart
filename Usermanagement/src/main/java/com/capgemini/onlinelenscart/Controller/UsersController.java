package com.capgemini.onlinelenscart.Controller;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.onlinelenscart.DTO.LoginRequest;
import com.capgemini.onlinelenscart.DTO.UserDTO;

import com.capgemini.onlinelenscart.Exception.UserNotFoundException;
import com.capgemini.onlinelenscart.Service.UserService;
import com.capgemini.onlinelenscart.entities.UserRole;
import com.capgemini.onlinelenscart.entities.Users;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping(value = "/v1/api/user")
@RequiredArgsConstructor
public class UsersController {
	private final UserService userService;

//    @PostMapping(value = "/register")
//    public ResponseEntity<String> addCustomer(@RequestBody @Valid UserDTO userDTO) {
//        var msg=userService.createCustomer(userDTO);
//        return new ResponseEntity<>(msg, HttpStatusCode.valueOf(201));
//   }
	@PostMapping("/register")
    public Users registerUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.registerUser(userDTO.getFirstName(),userDTO.getLastName(),userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail(), userDTO.getRoles());
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequest loginRequest) {
        var msg=userService.login(loginRequest);
        return new ResponseEntity<>(msg, HttpStatusCode.valueOf(201));
    }
   

    @GetMapping
    public String welcome(){
        return "Welcome to  Authentication Service";
    }
      //List all users
  @GetMapping("/allUsers")
  public List<Users> listUsers() {
      return userService.listUsers();
  }
  // Admin: List all customers
  @GetMapping("/customers")
  public Set<Users> listCustomers() {
      return userService.listCustomers();
  }

  // Admin: Delete a user by ID
  @DeleteMapping("/admin/delete/{userId}")
  public String deleteUser(@PathVariable @Min(value = 1, message = "UserId number should be positive")Long userId) {
      return userService.deleteUser(userId) ? "User deleted successfully" : "User not found";
  }

  // Admin Update user's role
  @PutMapping("/admin/update/{userId}")
  public Users updateUserRole(
          @PathVariable @Min(value = 1, message = "UserId number should be positive") Long userId,
          @RequestBody Set<UserRole> newRoles) {
      return userService.updateUserRole(userId, newRoles);
  }



}
