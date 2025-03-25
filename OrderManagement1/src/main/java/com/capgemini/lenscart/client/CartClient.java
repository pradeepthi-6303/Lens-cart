package com.capgemini.lenscart.client;

import com.capgemini.lenscart.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
/*
@FeignClient(name = "cart-service", url = "http://localhost:8092/api/cart")  // Replace with actual Cart Service URL
public interface CartClient {

    @GetMapping("/{itemId}")
    CartDTO getCartById(@PathVariable("itemId") int itemId) ; // Fetch CartDTO by cartId

    CartDTO getCartByItemId(int itemId);

    //CartDTO getCartByItemId(int itemId);


    //CartDTO getCartByItemId(int itemId);
}
*/
@FeignClient(name = "cart-service", url = "http://localhost:8092/carts")  // Replace with the actual Cart service URL
public interface CartClient {

    @GetMapping("/{itemId}")
    CartDTO getCartByItemId(@PathVariable("itemId") int itemId); // Fetch CartDTO by itemId
}