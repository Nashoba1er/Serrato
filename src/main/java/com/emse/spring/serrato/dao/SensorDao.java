package com.emse.spring.serrato.dao;

import com.emse.spring.serrato.model.SensorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorDao extends JpaRepository<SensorEntity, Long> {
    // Exemple de méthode pour trouver un capteur par son nom
    @Query("select c from SensorEntity c where c.name=:name")
    SensorEntity findByName(String name);
}
