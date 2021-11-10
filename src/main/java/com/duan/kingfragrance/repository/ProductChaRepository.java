package com.duan.kingfragrance.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.duan.kingfragrance.model.ProductCharacteristic;

@Repository
public interface ProductChaRepository extends MongoRepository<ProductCharacteristic, String> {
	
	@Query("{'productId': ?0}")
	Optional<ProductCharacteristic> findByProductId(String productId);
}
