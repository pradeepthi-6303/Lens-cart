package com.capgemini.onlinelenscart.Service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.capgemini.onlinelenscart.Repository.UserRepository;
import com.capgemini.onlinelenscart.entities.Users;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
	  private final UserRepository userRepository;
	  /**
	     * Loads user details from the database based on the username.
	     * This method is used by Spring Security to authenticate users.
	     *
	     * @param username The username entered during login.
	     * @return UserDetails object containing username, password, and roles.
	     * @throws UsernameNotFoundException If the user is not found in the database.
	     */

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 // Attempt to find the user in the database by username
		 Optional<Users> opt=userRepository.findByUsername(username);
		// If user is not found, throw an exception
	        if(opt.isEmpty()){
	            throw new UsernameNotFoundException("user not found");
	        }
	     // Retrieve the user entity from the Optional
	        Users customer=opt.get();
	     // Convert the user entity into a Spring Security User object
	     // Set the username
	     // Set the encrypted password
	     // Collect roles as a Set
        
	        User user=new User(customer.getUsername(),customer.getPassword(),
	                customer.getRoles().stream().map(role->new SimpleGrantedAuthority(role.toString())).collect(Collectors.toSet()));
	        return user;
	}
	

}
