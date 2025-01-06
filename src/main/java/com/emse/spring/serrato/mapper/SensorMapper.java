package com.emse.spring.serrato.mapper;

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
}
