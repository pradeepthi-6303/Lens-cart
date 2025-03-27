
package com.capgemini.onlinelenscart.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.capgemini.onlinelenscart.Filter.MyFilter;

import lombok.RequiredArgsConstructor;


 // Security configuration class for handling authentication and authorization.
 // - Defines security rules for accessing endpoints.
 // - Uses `BCryptPasswordEncoder` for password encryption.
 // - Configures an authentication provider and security filter chain.
 
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // Service to load user details from the database
    private final UserDetailsService userDetailsService;

    // Custom security filter (e.g., for JWT authentication)
    private final MyFilter myFilter;
    
     // Configures security rules for HTTP requests.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Disable CSRF protection (useful for stateless authentication like JWT)
            .csrf(csrf -> csrf.disable())

            // Define which endpoints are publicly accessible and which require authentication
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/v1/api/user/register", "/v1/api/user/login").permitAll() // Allow access to registration and login APIs
                .anyRequest().authenticated() // Require authentication for all other endpoints
            )

            // Add custom security filter before the default authentication filter
            .addFilterBefore(myFilter, UsernamePasswordAuthenticationFilter.class)

            // Enable basic authentication (useful for API testing)
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
  
     // Configures the authentication provider, which is responsible for verifying user credentials.
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder()); // Use BCrypt for password encryption
        provider.setUserDetailsService(userDetailsService); // Load user details from the database
        return provider;
    }

    
     // Provides an `AuthenticationManager` that is required for authentication operations.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    
     // Defines the password encoder used to encrypt and verify passwords.l.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
