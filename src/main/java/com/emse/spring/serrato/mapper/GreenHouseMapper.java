package com.emse.spring.serrato.mapper;

import com.emse.spring.serrato.model.GreenHouseEntity;
import com.emse.spring.serrato.record.*;

import java.util.List;
import java.util.stream.Collectors;

public class GreenHouseMapper {
    public static GreenHouse toRecord(GreenHouseEntity greenHouseEntity) {
        // Vérifier que les capteurs sont non nulls avant de les mapper
        Sensor humiditySensor = null;
        if (greenHouseEntity.getCurrentHumidity() != null) {
            humiditySensor = SensorMapper.toRecord(greenHouseEntity.getCurrentHumidity());
        }

        Sensor temperatureSensor = null;
        if (greenHouseEntity.getCurrentTemperature() != null) {
            temperatureSensor = SensorMapper.toRecord(greenHouseEntity.getCurrentTemperature());
        }

        // Mapper la liste des radiateurs
        List<Heater> heaters = greenHouseEntity.getHeaters().stream()
                .map(HeaterMapper::toRecord) // Utilisation du HeaterMapper pour chaque HeaterEntity
                .collect(Collectors.toList());

        // Retourner l'objet GreenHouse avec tous les capteurs et chauffages
        return new GreenHouse(
                greenHouseEntity.getId(),
                greenHouseEntity.getName(),
                greenHouseEntity.getTargetTemperature(),
                humiditySensor,
                temperatureSensor,
                heaters
        );
    }
}


