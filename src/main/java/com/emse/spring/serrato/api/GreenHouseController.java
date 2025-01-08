package com.emse.spring.serrato.api;

import com.emse.spring.serrato.dao.GreenHouseDao;
import com.emse.spring.serrato.mapper.GreenHouseMapper;
import com.emse.spring.serrato.mapper.HeaterMapper;
import com.emse.spring.serrato.mapper.SensorMapper;
import com.emse.spring.serrato.model.GreenHouseEntity;
import com.emse.spring.serrato.model.HeaterEntity;
import com.emse.spring.serrato.model.SensorEntity;
import com.emse.spring.serrato.model.SensorType;
import com.emse.spring.serrato.record.GreenHouse;
import com.emse.spring.serrato.record.Sensor;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.emse.spring.serrato.dao.SensorDao;
import com.emse.spring.serrato.dao.HeaterDao;



import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/greenhouses")
@Transactional
public class GreenHouseController {
    private final GreenHouseDao greenHouseDao;
    private final SensorDao sensorDao;
    private final HeaterDao heaterDao;


    public GreenHouseController(GreenHouseDao greenHouseDao, SensorDao sensorDao,HeaterDao heaterDao) {
        this.greenHouseDao = greenHouseDao;
        this.sensorDao = sensorDao;
        this.heaterDao = heaterDao;
    }

    // GET: Récupérer toutes les serres
    @GetMapping
    public List<GreenHouse> findAll() {
        return greenHouseDao.findAll().stream()
                .map(GreenHouseMapper::toRecord)
                .collect(Collectors.toList());
    }

    // GET: Récupérer une serre spécifique par ID
    @GetMapping("/{id}")
    public ResponseEntity<GreenHouse> findById(@PathVariable Long id) {
        return greenHouseDao.findById(id)
                .map(GreenHouseMapper::toRecord)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


//    @PostMapping
//    public ResponseEntity<GreenHouseEntity> createGreenhouse(@RequestBody GreenHouseCommand command) {
//        // Vérifier ou créer les capteurs
//        SensorEntity temperatureSensor = sensorDao.findById(command.temperatureSensor().id())
//                .orElseGet(() -> {
//                    SensorEntity newSensor = new SensorEntity();
//                    newSensor.setId(command.temperatureSensor().id());
//                    newSensor.setName("Temperature Sensor " + command.temperatureSensor().id());
//                    newSensor.setValue(command.temperatureSensor().sensorValue());
//                    newSensor.setSensorType(SensorType.TEMPERATURE);
//                    return sensorDao.save(newSensor);
//                });
//
//        SensorEntity humiditySensor = sensorDao.findById(command.humiditySensor().id())
//                .orElseGet(() -> {
//                    SensorEntity newSensor = new SensorEntity();
//                    newSensor.setId(command.humiditySensor().id());
//                    newSensor.setName("Humidity Sensor " + command.humiditySensor().id());
//                    newSensor.setValue(command.humiditySensor().sensorValue());
//                    newSensor.setSensorType(SensorType.HUMIDITY);
//                    return sensorDao.save(newSensor);
//                });
//
//        // Créer ou mettre à jour les chauffages
//        List<HeaterEntity> heaters = command.heaters().stream()
//                .map(heaterCommand -> {
//                    HeaterEntity newHeater = heaterDao.findById(heaterCommand.id())
//                            .orElse(new HeaterEntity());
//                    newHeater.setId(heaterCommand.id());
//                    newHeater.setName(heaterCommand.name() != null ? heaterCommand.name() : "New Heater");
//
//                    // Vérifier et créer le capteur de statut si nécessaire
//                    SensorEntity statusSensor = sensorDao.findById(heaterCommand.statusSensor().id())
//                            .orElseGet(() -> {
//                                SensorEntity newStatusSensor = new SensorEntity();
//                                newStatusSensor.setId(heaterCommand.statusSensor().id());
//                                newStatusSensor.setName("Status Sensor " + heaterCommand.statusSensor().id());
//                                newStatusSensor.setSensorType(SensorType.STATUS);
//                                newStatusSensor.setValue(0.0);  // Valeur initiale du capteur de statut (par exemple, éteint)
//                                return sensorDao.save(newStatusSensor);
//                            });
//
//                    newHeater.setStatus(statusSensor); // Assigner le capteur de statut
//                    return heaterDao.save(newHeater);  // Sauvegarder ou mettre à jour le chauffage
//                })
//                .collect(Collectors.toList());
//
//        // Créer la serre
//        GreenHouseEntity greenhouse = new GreenHouseEntity();
//        greenhouse.setName(command.name());
//        greenhouse.setTargetTemperature(command.targetTemperature());
//        greenhouse.setCurrentTemperature(temperatureSensor);
//        greenhouse.setCurrentHumidity(humiditySensor);
//        greenhouse.setHeaters(heaters);
//
//        // Sauvegarder la serre
//        GreenHouseEntity savedGreenhouse = greenHouseDao.save(greenhouse);
//
//        // Retourner la réponse avec la serre créée
//        return ResponseEntity.status(HttpStatus.CREATED).body(savedGreenhouse);
//    }


    @PostMapping
    public ResponseEntity<GreenHouseEntity> create(@RequestBody GreenHouseCommand command) {
        // Récupérer ou créer les capteurs nécessaires
        SensorEntity temperatureSensor = sensorDao.findById(command.temperatureSensor().id())
                .orElseGet(() -> {
                    SensorEntity newSensor = new SensorEntity(SensorType.TEMPERATURE, "Temperature Sensor " + command.temperatureSensor().id());
                    newSensor.setValue(command.temperatureSensor().sensorValue());
                    return sensorDao.save(newSensor);
                });

        SensorEntity humiditySensor = sensorDao.findById(command.humiditySensor().id())
                .orElseGet(() -> {
                    SensorEntity newSensor = new SensorEntity(SensorType.HUMIDITY, "Humidity Sensor " + command.humiditySensor().id());
                    newSensor.setValue(command.humiditySensor().sensorValue());
                    return sensorDao.save(newSensor);
                });

        // Vérifier ou créer les chauffages associés
        List<HeaterEntity> heaters = command.heaters().stream()
                .map(heaterCommand -> heaterDao.findById(heaterCommand.id())
                        .orElseGet(() -> {
                            // Récupérer ou créer un capteur de statut pour le chauffage
                            SensorEntity statusSensor = sensorDao.findById(heaterCommand.statusSensor().id())
                                    .orElseGet(() -> {
                                        SensorEntity newSensor = new SensorEntity(
                                                heaterCommand.statusSensor().sensorType(),
                                                heaterCommand.statusSensor().name()
                                        );
                                        newSensor.setValue(heaterCommand.statusSensor().sensorValue());
                                        return sensorDao.save(newSensor);
                                    });

                            // Créer le chauffage
                            HeaterEntity newHeater = new HeaterEntity();
                            newHeater.setId(heaterCommand.id());
                            newHeater.setName(heaterCommand.name() != null ? heaterCommand.name() : "Default Heater");
                            newHeater.setStatus(statusSensor);
                            return heaterDao.save(newHeater);
                        }))
                .collect(Collectors.toList());

        // Créer l'entité Greenhouse
        GreenHouseEntity greenhouse = new GreenHouseEntity();
        greenhouse.setName(command.name());
        greenhouse.setTargetTemperature(command.targetTemperature());
        greenhouse.setCurrentTemperature(temperatureSensor);
        greenhouse.setCurrentHumidity(humiditySensor);

        // Associer les chauffages
        greenhouse.setHeaters(heaters);

        // Sauvegarder la serre
        GreenHouseEntity savedGreenhouse = greenHouseDao.save(greenhouse);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedGreenhouse);
    }




    @PutMapping("/{id}")
    public ResponseEntity<GreenHouse> update(@PathVariable Long id, @RequestBody GreenHouseCommand greenHouseCommand) {
        // Récupérer la serre existante
        GreenHouseEntity existingGreenHouse = greenHouseDao.findById(id)
                .orElseThrow(() -> new RuntimeException("GreenHouse not found"));

        // Mettre à jour les propriétés de la serre
        existingGreenHouse.setName(greenHouseCommand.name());
        existingGreenHouse.setTargetTemperature(greenHouseCommand.targetTemperature());

        // Mettre à jour les capteurs
        SensorEntity temperatureSensor = sensorDao.findById(greenHouseCommand.temperatureSensor().id())
                .orElseThrow(() -> new RuntimeException("Temperature sensor not found"));
        existingGreenHouse.setCurrentTemperature(temperatureSensor);

        SensorEntity humiditySensor = sensorDao.findById(greenHouseCommand.humiditySensor().id())
                .orElseThrow(() -> new RuntimeException("Humidity sensor not found"));
        existingGreenHouse.setCurrentHumidity(humiditySensor);

        // Mettre à jour les chauffages
        List<HeaterEntity> updatedHeaters = new ArrayList<>();
        for (HeaterCommand heaterCommand : greenHouseCommand.heaters()) {
            HeaterEntity heaterEntity;

            if (heaterCommand.id() != null) {
                // Mettre à jour un chauffage existant
                heaterEntity = heaterDao.findById(heaterCommand.id())
                        .orElseThrow(() -> new RuntimeException("Heater not found"));
                heaterEntity.setName(heaterCommand.name());

                // Mettre à jour le capteur de statut
                SensorEntity statusSensor = sensorDao.findById(heaterCommand.statusSensor().id())
                        .orElseThrow(() -> new RuntimeException("Status sensor not found"));
                heaterEntity.setStatus(statusSensor);
            } else {
                // Ajouter un nouveau chauffage
                SensorEntity statusSensor = sensorDao.findById(heaterCommand.statusSensor().id())
                        .orElseThrow(() -> new RuntimeException("Status sensor not found"));
                heaterEntity = new HeaterEntity(
                        heaterCommand.name(),
                        existingGreenHouse,
                        statusSensor
                );
            }
            updatedHeaters.add(heaterEntity);
        }

        // Remplacer les chauffages existants par les nouveaux
        existingGreenHouse.setHeaters(updatedHeaters);

        // Sauvegarder la serre mise à jour
        GreenHouseEntity savedEntity = greenHouseDao.save(existingGreenHouse);
        return ResponseEntity.ok(GreenHouseMapper.toRecord(savedEntity));
    }


    // DELETE: Supprimer une serre par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!greenHouseDao.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        greenHouseDao.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

