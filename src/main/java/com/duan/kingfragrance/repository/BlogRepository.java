package com.duan.kingfragrance.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.duan.kingfragrance.model.Blog;
@Repository
public interface BlogRepository extends MongoRepository<Blog, String>{
@Query("{'id':?0}")
Optional<Blog> findByBlog(String id);
}
