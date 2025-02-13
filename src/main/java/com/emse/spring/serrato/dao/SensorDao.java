package com.emse.spring.serrato.dao;

import com.emse.spring.serrato.model.SensorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorDao extends JpaRepository<SensorEntity, Long> {
}
