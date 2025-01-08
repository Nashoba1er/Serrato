package com.emse.spring.serrato.api;

import java.util.List;

public record GreenHouseCommand(
        String name,
        Double targetTemperature,
        SensorCommand temperatureSensor,
        SensorCommand humiditySensor,
        List<HeaterCommand> heaters
) {}
