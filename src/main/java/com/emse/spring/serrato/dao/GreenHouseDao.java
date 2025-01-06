package com.emse.spring.serrato.dao;

import com.emse.spring.serrato.model.SensorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GreenHouseDao extends JpaRepository<SensorEntity, Long> {
}
