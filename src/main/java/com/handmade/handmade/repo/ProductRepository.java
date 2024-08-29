package com.handmade.handmade.repo;

import com.handmade.handmade.models.Post;
import com.handmade.handmade.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitleContainingIgnoreCase(String title);
}