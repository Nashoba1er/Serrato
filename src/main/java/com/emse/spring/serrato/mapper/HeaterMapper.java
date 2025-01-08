package com.emse.spring.serrato.mapper;

import com.emse.spring.serrato.api.HeaterCommand;
import com.emse.spring.serrato.model.GreenHouseEntity;
import com.emse.spring.serrato.model.HeaterEntity;
import com.emse.spring.serrato.model.SensorEntity;
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

    public static HeaterEntity toEntity(HeaterCommand command, GreenHouseEntity greenhouse) {
        if (command == null) {
            return null; // Gérer le cas où HeaterCommand est null
        }

        HeaterEntity entity = new HeaterEntity();
        entity.setName(command.name());
        entity.setGreenhouse(greenhouse);

        // Mapper le capteur de statut du radiateur (SensorCommand -> SensorEntity)
        SensorEntity statusSensor = SensorMapper.toEntity(command.statusSensor());
        entity.setStatus(statusSensor);

        return entity;
    }
}

