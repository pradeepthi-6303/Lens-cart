package com.capgemini.onlinelenscart.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.onlinelenscart.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUsername(String username);


}
