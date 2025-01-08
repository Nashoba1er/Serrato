package com.emse.spring.serrato.api;

import com.emse.spring.serrato.model.SensorEntity;
import com.emse.spring.serrato.dao.SensorDao;
import com.emse.spring.serrato.mapper.SensorMapper;
import com.emse.spring.serrato.record.Sensor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.transaction.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/sensors")
@Transactional
public class SensorController {

    private final SensorDao sensorDao;

    public SensorController(SensorDao sensorDao) {
        this.sensorDao = sensorDao;
    }

    /**
     * Récupérer la liste de tous les capteurs.
     */
    @GetMapping
    public List<Sensor> findAll() {
        return sensorDao.findAll()
                .stream()
                .map(SensorMapper::toRecord)
                .sorted(Comparator.comparing(Sensor::name))
                .collect(Collectors.toList());
    }

    /**
     * Récupérer un capteur particulier par son identifiant.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Sensor> findById(@PathVariable Long id) {
        return sensorDao.findById(id)
                .map(sensor -> ResponseEntity.ok(SensorMapper.toRecord(sensor)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Créer un nouveau capteur.
     */
    @PostMapping
    public ResponseEntity<Sensor> create(@RequestBody SensorCommand sensorCommand) {
        SensorEntity entity = new SensorEntity(sensorCommand.sensorType(), sensorCommand.name());
        entity.setValue(sensorCommand.sensorValue());
        SensorEntity savedEntity = sensorDao.save(entity);
        return ResponseEntity.ok(SensorMapper.toRecord(savedEntity));
    }

    /**
     * Mettre à jour un capteur existant par son ID.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Sensor> update(@PathVariable Long id, @RequestBody SensorCommand sensorCommand) {
        SensorEntity entity = sensorDao.findById(id).orElse(null);
        if (entity == null) {
            return ResponseEntity.notFound().build();
        }

        // Mise à jour des champs
        entity.setName(sensorCommand.name());
        entity.setSensorType(sensorCommand.sensorType());

        // Assure-toi que sensorValue est bien transmis et mappé
        if (sensorCommand.sensorValue() != null) {
            entity.setValue(sensorCommand.sensorValue());
        } else {
            // Si tu veux gérer une valeur nulle, tu peux définir une logique ici
            entity.setValue(0.0); // Ou autre valeur par défaut si nécessaire
        }

        // Sauvegarde de l'entité
        sensorDao.save(entity);
        return ResponseEntity.ok(SensorMapper.toRecord(entity));
    }


    /**
     * Supprimer un capteur par son ID.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            if (!sensorDao.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            sensorDao.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
