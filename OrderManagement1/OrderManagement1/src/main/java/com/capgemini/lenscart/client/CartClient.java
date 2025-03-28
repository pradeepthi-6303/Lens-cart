package com.capgemini.lenscart.client;

import com.capgemini.lenscart.dto.CartDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "cart-service", url = "http://localhost:8092/carts")  // Replace with the actual Cart service URL
public interface CartClient {

    @GetMapping("/{itemId}")
    CartDTO getCartByItemId(@PathVariable("itemId") int itemId); // Fetch CartDTO by itemId
}