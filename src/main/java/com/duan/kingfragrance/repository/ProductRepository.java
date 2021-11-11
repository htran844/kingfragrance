package com.duan.kingfragrance.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.duan.kingfragrance.model.Product;


@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
	
	@Query("{'slug': ?0}")
	Optional<Product> findBySlug(String slug);
}
