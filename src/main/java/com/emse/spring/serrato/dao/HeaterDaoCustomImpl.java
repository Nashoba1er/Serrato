package com.emse.spring.serrato.dao;

import com.emse.spring.serrato.model.HeaterEntity;
import com.emse.spring.serrato.model.SensorEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

public class HeaterDaoCustomImpl implements HeaterDaoCustom {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<HeaterEntity> findGreenHouseWithHeaterOn(Long id) {
        String jpql = "select h from HeaterEntity h inner join h.status s " +
                "where h.greenhouse.id = :id and s.value > 0.0 order by h.name";
        return em.createQuery(jpql, HeaterEntity.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public List<HeaterEntity> findAllHeatersByGreenhouseName(String greenhouseName) {
        String jpql = "select h from HeaterEntity h where h.greenhouse.name = :name";
        return em.createQuery(jpql, HeaterEntity.class)
                .setParameter("name", greenhouseName)
                .getResultList();
    }

    @Override
    public List<HeaterEntity> findAllHeatersByGreenhouseID(Long greenhouseId) {
        String jpql = "select h from HeaterEntity h where h.greenhouse.id = :id";
        return em.createQuery(jpql, HeaterEntity.class)
                .setParameter("id", greenhouseId)
                .getResultList();
    }

    @Override
    @Transactional
    public void deleteAllHeatersByGreenhouseId(Long greenhouseId) {
        String jpql = "delete from HeaterEntity h where h.greenhouse.id = :id";
        em.createQuery(jpql)
                .setParameter("id", greenhouseId)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void setAllHeatersStatusByGreenhouseId(Long greenhouseId, boolean turnOn) {
        // Récupérer tous les radiateurs de la serre
        List<HeaterEntity> heaters = findAllHeatersByGreenhouseID(greenhouseId);

        // Pour chaque radiateur, changer l'état du capteur de statut
        for (HeaterEntity heater : heaters) {
            // Récupérer le capteur de statut associé
            SensorEntity statusSensor = heater.getStatus();

            // Modifier la valeur du capteur (allumer ou éteindre)
            statusSensor.setValue(turnOn ? 1.0 : 0.0);  // 1.0 pour allumer, 0.0 pour éteindre

            // Mettre à jour le capteur de statut dans la base de données
            em.merge(statusSensor); // Utilisation de "merge" sur le capteur de statut
        }
    }

}
