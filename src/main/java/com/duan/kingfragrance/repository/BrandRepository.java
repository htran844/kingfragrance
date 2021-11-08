package com.duan.kingfragrance.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.duan.kingfragrance.model.Brand;


@Repository
public interface BrandRepository extends MongoRepository<Brand, String> {
	
	@Query("{'name': ?0}")
	Optional<Brand> findByBrand(String name);
}
