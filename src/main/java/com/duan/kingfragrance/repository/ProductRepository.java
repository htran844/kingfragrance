package com.duan.kingfragrance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.duan.kingfragrance.model.Product;


@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
	
	@Query("{'slug': ?0}")
	Optional<Product> findBySlug(String slug);
	
	@Query(value = "{ $and: [ { 'name' : {$regex:?0,$options:'i'} }, { 'gender' : {$regex:?1,$options:'i'} } ] }")
	List<Product> findAllAdminProduct(String search, String gender);
	
	
	@Query("{'name': {$regex: ?0 ,$options:'i'}})")
	List<Product> findByName(String search);
	
	@Query("{'gender': {$regex: ?0,$options:'i' }})")
	List<Product> findByGender(String gender);
}
