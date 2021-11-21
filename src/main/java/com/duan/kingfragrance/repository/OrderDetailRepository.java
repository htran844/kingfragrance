package com.duan.kingfragrance.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.duan.kingfragrance.model.OrderDetail;
@Repository
public interface OrderDetailRepository extends MongoRepository<OrderDetail, String> {
	@Query("{'orderId': ?0}")
	List<OrderDetail> getlstOdDetails(String orderId);
}
