package com.emse.spring.serrato.mapper;

import com.emse.spring.serrato.model.GreenHouseEntity;
import com.emse.spring.serrato.record.*;

import java.util.List;
import java.util.stream.Collectors;

public class GreenHouseMapper {
    public static GreenHouse toRecord(GreenHouseEntity greenHouseEntity) {
        // Mapper les capteurs de température et d'humidité
        Sensor humiditySensor = SensorMapper.toRecord(greenHouseEntity.getField().getOutsideTemperature()); // Exemple, à adapter si besoin
        Sensor temperatureSensor = SensorMapper.toRecord(greenHouseEntity.getCurrentTemperature());

        // Mapper la liste des radiateurs
        List<Heater> heaters = greenHouseEntity.getHeaters().stream()
                .map(HeaterMapper::toRecord) // Utilisation du HeaterMapper pour chaque HeaterEntity
                .collect(Collectors.toList());

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

