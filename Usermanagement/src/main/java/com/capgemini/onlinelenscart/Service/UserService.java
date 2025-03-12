package com.capgemini.onlinelenscart.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.capgemini.onlinelenscart.DTO.LoginRequest;
import com.capgemini.onlinelenscart.DTO.UserDTO;
import com.capgemini.onlinelenscart.entities.UserRole;
import com.capgemini.onlinelenscart.entities.Users;

import jakarta.validation.Valid;

public interface UserService {
	    public Users registerUser(String firstName, String lastName,String username, String password, String email, Set<UserRole> roles);
	    String login(LoginRequest loginRequest);
	    public Users getUserById(Long id);
	    public boolean deleteUser(Long userId);
	    public Users updateUserRole(Long userId, Set<UserRole> newRoles);
	    public Set<Users> listCustomers();
        public List<Users> listUsers();
		
		
		
}
