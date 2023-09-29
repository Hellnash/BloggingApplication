package com.blog.BloggingApplication.repositories;

import java.util.List;

import com.blog.BloggingApplication.entities.Category;
import com.blog.BloggingApplication.entities.Post;
import com.blog.BloggingApplication.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Integer> {
	
	List<Post> findByUsers(Users users);
	List<Post> findByCategory(Category category);
	List<Post> findByTittleContaining(String tittle);
	
	/* we can create custom methods with custom query & key = %keyword%
	 * @Query("select p from posts where p.tittle like: key") List<Post>
	 * searchByTittle(@RequestParam("key") String tittle);
	 */

}
