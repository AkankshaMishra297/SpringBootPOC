package com.neo.spring.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.neo.spring.demo.model.Role;
import com.neo.spring.demo.model.UserEmployment;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {


}
