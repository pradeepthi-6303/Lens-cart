package com.capgemini.onlinelenscart.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


 // This class is responsible for password encryption.
 // It uses BCrypt to securely hash passwords before storing them.
 
@Component  // Marks this class as a Spring-managed component
public class MyPasswordEncoder {

    
     // Creates a BCryptPasswordEncoder bean.
     // This ensures that all passwords are securely hashed.
    
     // @return A BCryptPasswordEncoder instance to encode passwords.
     
    @Bean  // Creates a Spring Bean that can be used throughout the application
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(); // Uses BCrypt for strong password hashing
    }
}
