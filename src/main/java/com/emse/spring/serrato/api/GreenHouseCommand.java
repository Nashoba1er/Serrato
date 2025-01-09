package com.emse.spring.serrato.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record GreenHouseCommand(
        @Schema(description = "Nom de la serre", example = "Ma Serre")
        String name,

        @Schema(description = "Température cible", example = "22.5")
        Double targetTemperature,

        @Schema(description = "Capteur de Température de la serre")
        SensorCommand temperatureSensor,

        @Schema(description = "Capteur d'Humidité de la serre")
        SensorCommand humiditySensor,

        @Schema(description = "Chauffages (laisser vide)", example = "[]")
        List<HeaterCommand> heaters
) {}
