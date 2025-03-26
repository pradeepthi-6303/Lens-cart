package com.capgemini.onlinelenscart.DTO;

import java.util.Set;

import com.capgemini.onlinelenscart.entities.UserRole;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserDTO {
	
	 private Long id;

	    @NotNull(message = "{firstName.required}")
	    @Size(min = 2, max = 50, message = "{firstName.size}")
	    @Pattern(regexp = "^[A-Za-z ]+$", message = "{firstName.pattern}")
	    private String firstName;

	    @NotNull(message = "{lastName.required}")
	    @Size(min = 2, max = 50, message = "{lastName.size}")
	    @Pattern(regexp = "^[A-Za-z ]+$", message = "{lastName.pattern}")
	    private String lastName;

	    @NotNull(message = "{username.required}")
	    @Size(min = 3, max = 30, message = "{username.size}")
	    @Pattern(regexp = "^[A-Za-z0-9_.]+$", message = "{username.pattern}")
	    private String username;

	    @NotNull(message = "{password.required}")
	    @Size(min = 8, message = "{password.size}")
	    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$", message = "{password.pattern}")
	    private String password;

	    @NotNull(message = "{email.required}")
	    @Email(message = "{email.valid}")
	    private String email;

	    @NotEmpty(message = "{roles.required}")
	    private Set<UserRole> roles;
 
 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<UserRole> getRoles() {
		return roles;
	}
	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}
	public UserDTO(Long id, @NotEmpty(message = "First Name is required") String firstName,
			@NotEmpty(message = "Last Name is required") String lastName,
			@Email(message = "Email is not valid") @NotEmpty(message = "Email is required") String email,
			@NotEmpty(message = "Username is required") String username,
			@NotEmpty(message = "Password is required") String password,
			@NotNull(message = "role is required") Set<UserRole> roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.roles = roles;
	}
	public UserDTO() {
		
	}
    
 

}
