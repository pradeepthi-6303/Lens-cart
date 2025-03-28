//package com.capgemini.onlinelenscart.Security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//import com.capgemini.onlinelenscart.Filter.MyFilter;
//
//import lombok.RequiredArgsConstructor;
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig {
//	private final PasswordEncoder passwordEncoder;
//    private final UserDetailsService userDetailsService;
//    private final MyFilter myFilter;
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http.csrf(c->c.disable());
//
//        http.authorizeHttpRequests(request->{
//            request.requestMatchers("v1/api/user/register","v1/api/user/login").permitAll();
//            request.anyRequest().authenticated();
//        });
//        http.addFilterBefore(myFilter, UsernamePasswordAuthenticationFilter.class);
//
//        http.httpBasic(Customizer.withDefaults());
//        return http.build();
//    }
//
//
//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder);
//        provider.setUserDetailsService(userDetailsService);
//        return provider;
//    }
//
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }
//
//}
package com.capgemini.onlinelenscart.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
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

@Configuration  // Marks this class as a configuration class for Spring Security
@EnableWebSecurity  // Enables Spring Security for the application
@RequiredArgsConstructor  // Automatically injects required dependencies via constructor
public class SecurityConfig {

    // Custom authentication filter for processing security logic before authentication
    private final MyFilter myFilter;

    // UserDetailsService is used to fetch user details for authentication
    private final UserDetailsService userDetailsService;

    /**
     * Configures the security filter chain, defining access control rules.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Disabling CSRF (Cross-Site Request Forgery) protection for simplicity
        http.csrf(csrf -> csrf.disable());

        // Defining which URLs are accessible without authentication
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/v1/api/user/register", "/v1/api/user/login").permitAll()  // Allow public access
                .anyRequest().authenticated());  // All other requests require authentication

        // Adding a custom filter before UsernamePasswordAuthenticationFilter for additional security processing
        http.addFilterBefore(myFilter, UsernamePasswordAuthenticationFilter.class);

        // Enabling basic authentication (username and password)
        http.httpBasic();
        return http.build();
    }

    /**
     * Configures the authentication provider to use a DAO-based authentication mechanism.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());  // Uses BCrypt for password hashing
        provider.setUserDetailsService(userDetailsService);  // Uses custom user details service for fetching user info
        return provider;
    }

    /**
     * Configures the authentication manager, which manages authentication processes.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Defines the password encoder bean using BCrypt hashing algorithm.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
