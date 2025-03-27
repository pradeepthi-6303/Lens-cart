package com.capgemini.lenscart.Service;

import com.capgemini.lenscart.Entities.Cart;
import com.capgemini.lenscart.Exception.CartItemNotFoundException;
import com.capgemini.lenscart.Repositories.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CartServiceTest {

    @InjectMocks
    private CartServiceImpl cartService;

    @Mock
    private CartRepository cartRepository;

    private Cart cart;

    @BeforeEach
    public void setUp() {
        cart = new Cart();
        cart.setItemId(1);
        cart.setCustomerId(123);
        cart.setTotal(150.0);
    }

    @Test
    public void testAddCart() {
        when(cartRepository.save(cart)).thenReturn(cart);
        
        Cart result = cartService.addCart(cart);
        
        assertNotNull(result);
        assertEquals(1, result.getItemId());
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    public void testGetCartById() {
        when(cartRepository.findById(1)).thenReturn(Optional.of(cart));
        
        Optional<Cart> result = cartService.getCartById(1);
        
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getItemId());
        verify(cartRepository, times(1)).findById(1);
    }

    @Test
    public void testGetCartById_NotFound() {
        when(cartRepository.findById(2)).thenReturn(Optional.empty());
        
        Optional<Cart> result = cartService.getCartById(2);
        
        assertFalse(result.isPresent());
        verify(cartRepository, times(1)).findById(2);
    }

    @Test
    public void testDeleteCart() {
        when(cartRepository.findById(1)).thenReturn(Optional.of(cart));
        
        boolean result = cartService.deleteCart(1);
        
        assertTrue(result);
        verify(cartRepository, times(1)).delete(cart);
    }

    @Test
    public void testDeleteCart_NotFound() {
        when(cartRepository.findById(2)).thenReturn(Optional.empty());
        
        CartItemNotFoundException thrown = assertThrows(CartItemNotFoundException.class, () -> {
            cartService.deleteCart(2);
        });
        
        assertEquals("Cart with ID 2 not found", thrown.getMessage());
    }
}
