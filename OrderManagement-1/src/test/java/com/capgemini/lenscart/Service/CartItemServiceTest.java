package com.capgemini.lenscart.Service;

import com.capgemini.lenscart.Entities.CartItem;
import com.capgemini.lenscart.Exception.CartItemNotFoundException;
import com.capgemini.lenscart.Repositories.CartItemRepository;
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
public class CartItemServiceTest {

    @InjectMocks
    private CartItemServiceImpl cartItemService;

    @Mock
    private CartItemRepository cartItemRepository;

    @Mock
    private CartRepository cartRepository;

    private CartItem cartItem;

    @BeforeEach
    public void setUp() {
        cartItem = new CartItem();
        cartItem.setCartItemId(1);
        cartItem.setQuantity(2);
        cartItem.setCustomerId(123);
    }

    @Test
    public void testAddCartItem() {
        when(cartItemRepository.save(cartItem)).thenReturn(cartItem);
        
        CartItem result = cartItemService.addCartItem(cartItem);
        
        assertNotNull(result);
        assertEquals(1, result.getCartItemId());
        verify(cartItemRepository, times(1)).save(cartItem);
    }

    @Test
    public void testGetCartItemById() {
        // Correct: Mocking CartItemRepository to return an Optional<CartItem>
        when(cartItemRepository.findById(1)).thenReturn(Optional.of(cartItem));
        
        Optional<CartItem> result = cartItemService.getCartItemById(1);
        
        assertTrue(result.isPresent());
        assertEquals(1, result.get().getCartItemId());
        verify(cartItemRepository, times(1)).findById(1);
    }


}
