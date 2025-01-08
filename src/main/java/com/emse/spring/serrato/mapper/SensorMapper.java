package com.emse.spring.serrato.mapper;

import com.emse.spring.serrato.api.SensorCommand;
import com.emse.spring.serrato.model.SensorEntity;
import com.emse.spring.serrato.record.Sensor;

public class SensorMapper {
    public static Sensor toRecord(SensorEntity sensorEntity) {
        return new Sensor(
                sensorEntity.getId(),
                sensorEntity.getName(),
                sensorEntity.getValue(),
                sensorEntity.getSensorType().name()
        );
    }

    public static SensorEntity toEntity(SensorCommand command) {
        if (command == null) {
            return null; // Gérer le cas où le SensorCommand est null
        }

        SensorEntity entity = new SensorEntity();
        entity.setName(command.name());
        entity.setSensorType(command.sensorType());
        entity.setValue(command.sensorValue());

        return entity;
    }

    public static void updateEntity(SensorEntity entity, SensorCommand command) {
        if (command == null || entity == null) {
            return; // Gérer les cas où l'entrée est invalide
        }

        entity.setName(command.name());
        entity.setSensorType(command.sensorType());
        entity.setValue(command.sensorValue());
    }

}
