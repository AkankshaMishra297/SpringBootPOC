package com.neo.spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neo.spring.demo.model.UserEducation;
import com.neo.spring.demo.model.UserEmployment;

@Repository
public interface UserEmploymentRepository extends JpaRepository<UserEmployment,Integer> {


}
