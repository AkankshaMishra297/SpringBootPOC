package com.neo.spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neo.spring.demo.model.UserEducation;

@Repository
public interface UserEducationRepository extends JpaRepository<UserEducation,Integer> {


}

