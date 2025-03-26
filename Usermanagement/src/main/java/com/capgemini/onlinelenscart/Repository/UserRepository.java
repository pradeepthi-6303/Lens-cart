package com.capgemini.onlinelenscart.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capgemini.onlinelenscart.entities.Users;
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	  // select * from customer where username=?
	   Optional<Users> findByUsername(String username);

	   Users findByEmail(String string);


}
