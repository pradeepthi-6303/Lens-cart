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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Optional<Users> opt=userRepository.findByUsername(username);
	        if(opt.isEmpty()){
	            throw new UsernameNotFoundException("user not found");
	        }
	        Users customer=opt.get();
	        User user=new User(customer.getUsername(),customer.getPassword(),
	                customer.getRoles().stream().map(role->new SimpleGrantedAuthority(role.toString())).collect(Collectors.toSet()));
	        return user;
	}
	

}
