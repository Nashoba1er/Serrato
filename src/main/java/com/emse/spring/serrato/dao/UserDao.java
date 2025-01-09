package com.emse.spring.serrato.dao;

import com.emse.spring.serrato.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserEntity, Long> {
}
