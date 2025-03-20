package com.mk.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mk.entity.UserManagement;

public interface UserManageRepository extends JpaRepository<UserManagement, Integer> {

	Optional<UserManagement> findByEmail(String email);

	UserManagement findByEmailAndPassword(String email, String password);
}