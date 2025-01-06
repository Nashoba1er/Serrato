package com.emse.spring.serrato.model;

import static com.emse.spring.serrato.model.SensorType.HUMIDITY;
import static com.emse.spring.serrato.model.SensorType.TEMPERATURE;
import static com.emse.spring.serrato.model.SensorType.STATUS;

public class FakeEntityBuilder {

    public static HeaterEntity createHeaterEntity(Long id, String name, GreenHouseEntity greenHouse, Long sensorId, String sensorName, Double sensorValue) {        // Sensor is recreated before each test
        HeaterEntity heaterEntity = new HeaterEntity(
                name,
                greenHouse,
                createSensorEntity(sensorId, sensorName, STATUS, sensorValue)
        );
        heaterEntity.setId(id);
        return heaterEntity;
    }

    public static GreenHouseEntity createGreenHouseEntity(Long id, String name, FieldEntity field) {
        // Sensor is recreated before each test
        GreenHouseEntity entity  = new GreenHouseEntity(
                name,
                createSensorEntity(-10L, "Temperature greenhouse 2", TEMPERATURE, 21.3),
                20.0,
                createSensorEntity(-5L, "Humidity greenhouse 1", HUMIDITY, 22.0),
                field);

        entity.setId(id);
        entity.addHeater(createHeaterEntity(-10L, "Heater 1", entity, -9L, "Heater 1 status greenhouse 1", 1.0));
        entity.addHeater(createHeaterEntity(-9L, "Heater 2", entity, -8L, "Heater 2 status greenhouse 1", 0.0));        return entity;
    }

    public static FieldEntity createFieldEntity(Long id, String name) {
        FieldEntity field = new FieldEntity(
                name,
                createSensorEntity(-10L, "Field Sensor Temp", TEMPERATURE, 0.0)
        );
        field.setId(id);

        // Ajout des serres liées au champ
        field.addGreenHouse(createGreenHouseEntity(-10L, "greenhouse1", field));
        field.addGreenHouse(createGreenHouseEntity(-9L, "greenhouse2", field));

        return field;
    }


    public static SensorEntity createSensorEntity(Long id, String name, SensorType type, Double value) {
        // Sensor is recreated before each test
        SensorEntity sensorEntity = new SensorEntity(type, name);
        sensorEntity.setId(id);
        sensorEntity.setValue(value);
        return sensorEntity;
    }
}