package com.emse.spring.serrato.api;

public record HeaterCommand(
        Long id,
        String name,         // Nom du chauffage
        SensorCommand statusSensor, // Capteur associé pour l'état du chauffage
        Long greenhouseId //id de la serre
) {}

