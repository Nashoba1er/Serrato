package com.emse.spring.serrato;

import com.emse.spring.serrato.dao.HeaterDao;
import com.emse.spring.serrato.model.HeaterEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class HeaterDaoTest {

    @Autowired
    private HeaterDao heaterDao;

    @Test
    public void shouldFindHeatersByGreenhouseName() {
        // Rechercher tous les radiateurs dans la serre "greenhouse1"
        List<HeaterEntity> heaters = heaterDao.findAllHeatersByGreenhouseName("greenhouse1");

        // Vérifier que nous avons bien les 2 radiateurs de "greenhouse1"
        assertThat(heaters).hasSize(2);
        assertThat(heaters.get(0).getName()).isEqualTo("Heater 1");
        assertThat(heaters.get(1).getName()).isEqualTo("Heater 2");
    }

    @Test
    public void shouldDeleteAllHeatersByGreenhouseId() {
        // Supprimer tous les radiateurs de la serre avec l'ID -10 (greenhouse1)
        heaterDao.deleteAllHeatersByGreenhouseId(-10L);

        // Vérifier qu'il n'y a plus de radiateurs pour "greenhouse1"
        List<HeaterEntity> heaters = heaterDao.findAllHeatersByGreenhouseName("greenhouse1");
        assertThat(heaters).isEmpty();
    }

    @Test
    public void shouldTurnOnAllHeatersInGreenhouse() {
        // Allumer tous les radiateurs de la serre avec l'ID -10 (greenhouse1)
        heaterDao.setAllHeatersStatusByGreenhouseId(-10L, true);

        // Vérifier que tous les radiateurs ont le statut "allumé" (1.0)
        List<HeaterEntity> heaters = heaterDao.findAllHeatersByGreenhouseName("greenhouse1");
        assertThat(heaters).allMatch(heater -> heater.getStatus().getValue().equals(1.0));
    }

    @Test
    public void shouldTurnOffAllHeatersInGreenhouse() {
        // Éteindre tous les radiateurs de la serre avec l'ID -10 (greenhouse1)
        heaterDao.setAllHeatersStatusByGreenhouseId(-10L, false);

        // Vérifier que tous les radiateurs ont le statut "éteint" (0.0)
        List<HeaterEntity> heaters = heaterDao.findAllHeatersByGreenhouseName("greenhouse1");
        assertThat(heaters).allMatch(heater -> heater.getStatus().getValue().equals(0.0));
    }

    @Test
    public void shouldFindGreenHouseWithHeaterOn() {
        // Vérifier que nous pouvons trouver des radiateurs allumés dans la serre avec l'ID -10 (greenhouse1)
        List<HeaterEntity> heaters = heaterDao.findGreenHouseWithHeaterOn(-10L);

        // Vérifier qu'il y a un radiateur allumé (Heater 1)
        assertThat(heaters).hasSize(1);
        assertThat(heaters.get(0).getName()).isEqualTo("Heater 1");
    }
}
