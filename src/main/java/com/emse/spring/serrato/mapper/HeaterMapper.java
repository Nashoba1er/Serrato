package com.emse.spring.serrato.mapper;

import com.emse.spring.serrato.model.HeaterEntity;
import com.emse.spring.serrato.record.Heater;
import com.emse.spring.serrato.record.Sensor;

public class HeaterMapper {
    public static Heater toRecord(HeaterEntity heaterEntity) {
        // Mapper le capteur de statut
        Sensor statusSensor = SensorMapper.toRecord(heaterEntity.getStatus());

        // Nous n'incluons pas les heaters liés pour éviter le cycle
        return new Heater(
                heaterEntity.getId(),
                heaterEntity.getName(),
                statusSensor,
                heaterEntity.getGreenhouse().getId() // On inclut seulement l'ID de la serre
        );
    }
}

