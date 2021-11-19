package com.duan.kingfragrance.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.duan.kingfragrance.model.ProductRecommended;

@Repository
public interface ProductRecRepository extends MongoRepository<ProductRecommended, String> {

	@Query("{'productId': ?0}")
	Optional<ProductRecommended> findByProductId(String productId);
}
