package com.capgemini.onlinelenscart.entities;

import java.util.Set;


import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import jakarta.persistence.JoinColumn;

@Entity
@Builder
@Table(name = "users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // Integer as primary key
	private String firstName;
	private String lastName;
	@Column(unique = true,nullable = false)
	private String username;
	private String password;
	@Column(unique = true,nullable = false)
	private String email;

	// Collection of roles for user (Admin, Customer)
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "roles_tab",joinColumns = @JoinColumn(name ="cid" ))
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Set<UserRole> roles; // Store multiple roles for each user

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<UserRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<UserRole> roles) {
		this.roles = roles;
	}

	public Users(Long id, String firstName, String lastName, String username, String password, String email,
			Set<UserRole> roles) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.email = email;
		this.roles = roles;
	}

	public Users() {
		
	}
	

}