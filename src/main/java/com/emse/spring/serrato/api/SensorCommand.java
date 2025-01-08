package com.emse.spring.serrato.api;

import com.emse.spring.serrato.model.SensorType;

public record SensorCommand(Long id, String name, Double sensorValue, SensorType sensorType) {
}
