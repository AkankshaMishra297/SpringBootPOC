package com.neo.spring.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.neo.spring.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

	@Query(value = "SELECT * FROM user where active=1", nativeQuery = true)
	List<User> findActiveUser();

	List<User> findByUserDetailsEmail(String email);
}
