package com.emse.spring.serrato.dao;

import com.emse.spring.serrato.model.GreenHouseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GreenHouseDao extends JpaRepository<GreenHouseEntity, Long> {
}
