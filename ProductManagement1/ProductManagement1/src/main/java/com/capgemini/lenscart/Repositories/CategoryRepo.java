package com.capgemini.lenscart.Repositories;


import com.capgemini.lenscart.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
