package com.neo.spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neo.spring.demo.model.User;
import com.neo.spring.demo.model.UserDetails;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails,Integer> {


}