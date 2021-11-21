package com.duan.kingfragrance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


import com.duan.kingfragrance.model.ProductDetail;
@Repository
public interface ProductDetailRepository extends MongoRepository<ProductDetail, String> {
	@Query("{'productId': ?0}")
	Optional<ProductDetail> findByProductId(String productId);
	
	@Query("{'productId': ?0, 'capacity': ?1}")
	Optional<ProductDetail> findByCapacityAndProductId(String productId, int capacity);
	
	@Query("{'productId': ?0}")
	List<ProductDetail> findAllByProductId(String productId);
	
	@Query("{'id': ?0}")
	ProductDetail findByProductDetailId(String productDetailId);
}
