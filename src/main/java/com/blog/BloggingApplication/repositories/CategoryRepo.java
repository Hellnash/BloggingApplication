package com.blog.BloggingApplication.repositories;

import com.blog.BloggingApplication.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
