package com.emse.spring.serrato.dao;

import com.emse.spring.serrato.model.HeaterEntity;

import java.util.List;

public interface HeaterDaoCustom {
    List<HeaterEntity> findGreenHouseWithHeaterOn(Long id);
    List<HeaterEntity> findAllHeatersByGreenhouseName(String greenhouseName);
    List<HeaterEntity> findAllHeatersByGreenhouseID(Long greenhouseId);
    void deleteAllHeatersByGreenhouseId(Long greenhouseId);
    void setAllHeatersStatusByGreenhouseId(Long greenhouseId, boolean status);
}
