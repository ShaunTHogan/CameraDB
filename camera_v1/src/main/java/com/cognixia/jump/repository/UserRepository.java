package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	// look for users with a certain account type
	@Query("{ 'accounts.type': { $eq: ?0 } }")
	public List<User> findUsersByAccountType(String type);

}