package com.emse.spring.serrato.api;

import com.emse.spring.serrato.dao.HeaterDao;
import com.emse.spring.serrato.dao.SensorDao;
import com.emse.spring.serrato.dao.GreenHouseDao;
import com.emse.spring.serrato.mapper.HeaterMapper;
import com.emse.spring.serrato.model.GreenHouseEntity;
import com.emse.spring.serrato.model.HeaterEntity;
import com.emse.spring.serrato.model.SensorEntity;
import com.emse.spring.serrato.model.SensorType;
import com.emse.spring.serrato.record.Heater;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/heaters")
@Transactional
public class HeaterController {
    private final HeaterDao heaterDao;
    private final SensorDao sensorDao;
    private final GreenHouseDao greenHouseDao;

    public HeaterController(HeaterDao heaterDao, SensorDao sensorDao, GreenHouseDao greenHouseDao) {
        this.heaterDao = heaterDao;
        this.sensorDao = sensorDao;
        this.greenHouseDao = greenHouseDao;
    }

    // GET: Récupérer tous les chauffages
    @GetMapping
    public List<Heater> findAll() {
        return heaterDao.findAll().stream()
                .map(HeaterMapper::toRecord)
                .collect(Collectors.toList());
    }

    // GET: Récupérer un chauffage par ID
    @GetMapping("/{id}")
    public ResponseEntity<Heater> findById(@PathVariable Long id) {
        return heaterDao.findById(id)
                .map(HeaterMapper::toRecord)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST: Créer un chauffage
    @PostMapping
    public ResponseEntity<Heater> create(@RequestBody HeaterCommand heaterCommand) {
        // Récupérer ou créer une nouvelle serre
        GreenHouseEntity greenhouse = greenHouseDao.findById(heaterCommand.greenhouseId())
                .orElseGet(() -> {
                    // Créer les capteurs nécessaires pour la nouvelle serre
                    SensorEntity temperatureSensor = new SensorEntity(SensorType.TEMPERATURE, "Default Temperature Sensor");
                    temperatureSensor.setValue(20.0); // Valeur par défaut
                    SensorEntity savedTemperatureSensor = sensorDao.save(temperatureSensor);

                    SensorEntity humiditySensor = new SensorEntity(SensorType.HUMIDITY, "Default Humidity Sensor");
                    humiditySensor.setValue(50.0); // Valeur par défaut
                    SensorEntity savedHumiditySensor = sensorDao.save(humiditySensor);

                    // Créer la nouvelle serre
                    GreenHouseEntity newGreenhouse = new GreenHouseEntity();
                    newGreenhouse.setId(heaterCommand.greenhouseId());
                    newGreenhouse.setName("New Greenhouse " + heaterCommand.greenhouseId());
                    newGreenhouse.setTargetTemperature(20.0); // Valeur par défaut
                    newGreenhouse.setCurrentTemperature(savedTemperatureSensor);
                    newGreenhouse.setCurrentHumidity(savedHumiditySensor);
                    return greenHouseDao.save(newGreenhouse);
                });

        // Récupérer ou créer un nouveau capteur de statut
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
        HeaterEntity heaterEntity = new HeaterEntity(
                heaterCommand.name(),
                greenhouse,
                statusSensor
        );

        // Sauvegarder le chauffage
        HeaterEntity savedHeater = heaterDao.save(heaterEntity);

        return ResponseEntity.ok(HeaterMapper.toRecord(savedHeater));
    }



    // PUT: Mettre à jour un chauffage
    @PutMapping("/{id}")
    public ResponseEntity<Heater> update(@PathVariable Long id, @RequestBody HeaterCommand heaterCommand) {
        // Vérifier si le chauffage existe
        HeaterEntity existingHeater = heaterDao.findById(id).orElse(null);
        if (existingHeater == null) {
            return ResponseEntity.notFound().build();
        }

        // Récupérer ou créer une nouvelle serre si l'ID de la serre a changé
        GreenHouseEntity greenhouse = greenHouseDao.findById(heaterCommand.greenhouseId())
                .orElseGet(() -> {
                    // Créer les capteurs nécessaires pour la nouvelle serre
                    SensorEntity temperatureSensor = new SensorEntity(SensorType.TEMPERATURE, "Default Temperature Sensor");
                    temperatureSensor.setValue(20.0); // Valeur par défaut
                    SensorEntity savedTemperatureSensor = sensorDao.save(temperatureSensor);

                    SensorEntity humiditySensor = new SensorEntity(SensorType.HUMIDITY, "Default Humidity Sensor");
                    humiditySensor.setValue(50.0); // Valeur par défaut
                    SensorEntity savedHumiditySensor = sensorDao.save(humiditySensor);

                    // Créer la nouvelle serre
                    GreenHouseEntity newGreenhouse = new GreenHouseEntity();
                    newGreenhouse.setId(heaterCommand.greenhouseId());
                    newGreenhouse.setName("New Greenhouse " + heaterCommand.greenhouseId());
                    newGreenhouse.setTargetTemperature(20.0); // Valeur par défaut
                    newGreenhouse.setCurrentTemperature(savedTemperatureSensor);
                    newGreenhouse.setCurrentHumidity(savedHumiditySensor);
                    return greenHouseDao.save(newGreenhouse);
                });

        // Récupérer ou créer un nouveau capteur de statut
        SensorEntity statusSensor = sensorDao.findById(heaterCommand.statusSensor().id())
                .orElseGet(() -> {
                    SensorEntity newSensor = new SensorEntity(
                            heaterCommand.statusSensor().sensorType(),
                            heaterCommand.statusSensor().name()
                    );
                    newSensor.setValue(heaterCommand.statusSensor().sensorValue());
                    return sensorDao.save(newSensor);
                });

        // Mettre à jour les propriétés du chauffage existant
        existingHeater.setName(heaterCommand.name());
        existingHeater.setGreenhouse(greenhouse);
        existingHeater.setStatus(statusSensor);

        // Sauvegarder les modifications
        HeaterEntity updatedHeater = heaterDao.save(existingHeater);

        return ResponseEntity.ok(HeaterMapper.toRecord(updatedHeater));
    }


    // DELETE: Supprimer un chauffage par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!heaterDao.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        heaterDao.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // GET: Récupérer tous les chauffages d'une serre par ID de la serre
    @GetMapping("/greenhouse/{greenhouseId}")
    public List<Heater> findAllByGreenhouse(@PathVariable Long greenhouseId) {
        return heaterDao.findAllHeatersByGreenhouseID(greenhouseId).stream()
                .map(HeaterMapper::toRecord)
                .collect(Collectors.toList());
    }
}
