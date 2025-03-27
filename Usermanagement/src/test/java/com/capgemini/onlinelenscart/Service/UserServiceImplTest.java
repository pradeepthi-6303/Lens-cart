package com.capgemini.onlinelenscart.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.capgemini.onlinelenscart.Repository.UserRepository;
import com.capgemini.onlinelenscart.entities.UserRole;
import com.capgemini.onlinelenscart.entities.Users;

public class UserServiceImplTest {
	@Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private UserServiceImpl userService;

    private Users user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new Users(1L, "Pradeepthi", "Chevvakula", "pradeepthich", "password@123", "johndoe@example.com", Set.of(UserRole.CUSTOMER));
    }


    @Test
    void testRegisterUser() {
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(Users.class))).thenReturn(user);

        Users registeredUser = userService.registerUser("Pradeepthi", "Chevvakula", "pradeepthich", "password@123", "johndoe@example.com", Set.of(UserRole.CUSTOMER));

        assertNotNull(registeredUser);
        assertEquals("Pradeepthi", registeredUser.getFirstName());
    }

    @Test
    void testGetUserById_UserExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Users foundUser = userService.getUserById(1L);

        assertNotNull(foundUser);
        assertEquals(1L, foundUser.getId());
    }

    @Test
    void testGetUserById_UserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> userService.getUserById(1L));
        assertTrue(exception.getMessage().contains("User not found"));
    }
    
    @Test
    void testListUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        
        List<Users> users = userService.listUsers();
        assertEquals(1, users.size());
    }
    @Test
    void testDeleteUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);
        
        boolean result = userService.deleteUser(1L);
        assertTrue(result);
        verify(userRepository, times(1)).delete(user);
    }

}
