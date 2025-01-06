package com.emse.spring.serrato.record;

import java.util.List;

public record GreenHouse(Long id,
                         String name,
                         Double targetTemperature,
                         Sensor humiditySensor,
                         Sensor temperatureSensor,
                         List<Heater> heaters) {
}
