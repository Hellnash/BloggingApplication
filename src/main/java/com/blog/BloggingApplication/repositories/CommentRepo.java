package com.blog.BloggingApplication.repositories;

import com.blog.BloggingApplication.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository <Comment, Integer>{

}
