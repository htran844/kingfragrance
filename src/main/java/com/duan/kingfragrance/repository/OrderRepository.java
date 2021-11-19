package com.duan.kingfragrance.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.duan.kingfragrance.model.Order;


@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
	
	@Query(value = "{ $and: [ { 'phone' : {$regex:?0,$options:'i'} }, { 'status' : {$regex:?1,$options:'i'} } ] }")
	List<Order> findAllAdminOrder(String phone, String status);
	
	
	@Query("{'phone': {$regex: ?0 ,$options:'i'}})")
	List<Order> findByPhone(String phone);
	
	@Query("{'status': {$regex: ?0,$options:'i' }})")
	List<Order> findByStatus(String status);
}
