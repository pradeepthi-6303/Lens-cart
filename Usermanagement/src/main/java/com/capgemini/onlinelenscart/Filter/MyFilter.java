//package com.capgemini.onlinelenscart.Filter;
//
//import java.io.IOException;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.capgemini.onlinelenscart.Jwt.JwtUtil;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.RequiredArgsConstructor;
//@Component
//@RequiredArgsConstructor
//public class MyFilter extends OncePerRequestFilter {
//	private final JwtUtil jwtUtil;
//    private final UserDetailsService userDetailsService;
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		 String token=request.getHeader("Authorization");
//	        if(token!=null){
//	          var username=  jwtUtil.getSubject(token);
//	            System.out.println(username);
//	          if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
//	              var userDetails=userDetailsService.loadUserByUsername(username);
//	                boolean isTokenValid=jwtUtil.validateToken(token,userDetails.getUsername());
//	                if(isTokenValid){
//	                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new
//	                            UsernamePasswordAuthenticationToken(userDetails.getUsername()
//	                            ,userDetails.getPassword()
//	                            ,userDetails.getAuthorities());
//	                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//	                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//
//	                }
//	          }
//	        }
//
//	    filterChain.doFilter(request, response);
//		
//	}
//	
//
//}
package com.capgemini.onlinelenscart.Filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.capgemini.onlinelenscart.Jwt.JwtUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component  // Marks this class as a Spring-managed component (Bean)
@RequiredArgsConstructor  // Lombok annotation to generate a constructor for final fields (Dependency Injection)
public class MyFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;  // Utility class for JWT operations
    private final UserDetailsService userDetailsService;  // Service to fetch user details

    /**
     * This method intercepts all incoming requests and checks if they contain a valid JWT token.
     * If a valid token is found, it sets the authentication in the SecurityContext.
     *
     * @param request Incoming HTTP request
     * @param response HTTP response
     * @param filterChain Allows the request to proceed if authentication is successful
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        // Extract the JWT token from the "Authorization" header
        String token = request.getHeader("Authorization");

        // If a token is present, validate and authenticate the user
        if (token != null) {
            // Extract the username from the token
            var username = jwtUtil.getSubject(token);
            System.out.println(username);  // Log the username (for debugging)

            // Check if the user is not already authenticated
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // Load user details from the database using username
                var userDetails = userDetailsService.loadUserByUsername(username);

                // Validate the token to ensure it matches the user details
                boolean isTokenValid = jwtUtil.validateToken(token, userDetails.getUsername());
                
                if (isTokenValid) {
                    // Create an authentication token containing user details and authorities
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails.getUsername(),
                            userDetails.getPassword(),
                            userDetails.getAuthorities()
                    );

                    // Set authentication details for the request
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Store the authentication in SecurityContextHolder (so Spring Security recognizes it)
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }

        // Continue processing the request after authentication check
        filterChain.doFilter(request, response);
    }
}
