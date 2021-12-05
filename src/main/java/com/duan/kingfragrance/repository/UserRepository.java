package com.duan.kingfragrance.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.duan.kingfragrance.model.User;
@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	@Query("{'username': ?0,'password': ?1}")
	Optional<User> findUser(String username, String password);
	
	@Query("{'username': ?0}")
	Optional<User> findUserByUsername(String username);
}
