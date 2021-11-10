package com.duan.kingfragrance.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.duan.kingfragrance.model.ProductFragrance;

@Repository
public interface ProductFraRepository extends MongoRepository<ProductFragrance, String> {
	
	@Query("{'productId': ?0}")
	Optional<ProductFragrance> findByProductId(String productId);
}
