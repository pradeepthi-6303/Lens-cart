package com.capgemini.lenscart.Feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.capgemini.lenscart.Dto.CartDto;
import com.capgemini.lenscart.Dto.CategoryDTO;
@FeignClient(name = "ProductMaanagement1", url = "http://localhost:8086")
public interface CategoryFeignClient {
    @GetMapping("/api/categories/{id}")
    CategoryDTO getCategoryById(@PathVariable("id") Long id);  // Update the return type to CategoryDTO
}
