package com.khandelwal.SpringbootRedis.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khandelwal.SpringbootRedis.domainmodel.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
