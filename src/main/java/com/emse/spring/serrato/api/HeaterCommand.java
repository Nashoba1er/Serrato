package com.emse.spring.serrato.api;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Requête pour créer une serre", example = """
{
  "id": 0,
  "name": "string",
  "statusSensor": {
    "id": 0,
    "name": "HeaterSensor",
    "sensorValue": 0,
    "sensorType": "STATUS"
  },
  "greenhouseId": 0
}
""")
public record HeaterCommand(
        Long id,
        String name,         // Nom du chauffage
        SensorCommand statusSensor, // Capteur associé pour l'état du chauffage
        Long greenhouseId //id de la serre
) {}

