package com.handmade.handmade.repo;

import com.handmade.handmade.models.Post;
import com.handmade.handmade.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Post, Long> {
    List<Post> findByTitleContainingIgnoreCase(String title);
}
