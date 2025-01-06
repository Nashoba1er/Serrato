package com.emse.spring.serrato.dao;

import com.emse.spring.serrato.model.HeaterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HeaterDao extends JpaRepository<HeaterEntity, Long>, HeaterDaoCustom {

}
