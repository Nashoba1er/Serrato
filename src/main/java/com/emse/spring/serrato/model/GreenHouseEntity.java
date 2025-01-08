package com.emse.spring.serrato.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SP_GREENHOUSE") // Table pour la classe GreenHouseEntity
public class GreenHouseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "current_temperature_sensor_id", nullable = false) // Associe le capteur mesurant la température actuelle
    private SensorEntity currentTemperature;

    @OneToOne
    @JoinColumn(name = "current_humidity_sensor_id", nullable = false) // Capteur pour l'humidité
    private SensorEntity currentHumidity;

    @Column(name = "target_temperature", nullable = false) // Température cible stockée directement
    private Double targetTemperature;

    @OneToMany(mappedBy = "greenhouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HeaterEntity> heaters = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "field_id") // Une serre appartient à un champ
    private FieldEntity field;


    public GreenHouseEntity() {
    }

    public GreenHouseEntity(String name, SensorEntity currentTemperature, Double targetTemperature, SensorEntity currentHumidity, FieldEntity field) {
        this.name = name;
        this.currentTemperature = currentTemperature;
        this.targetTemperature = targetTemperature;
        this.currentHumidity = currentHumidity;
        this.field = field;
    }

    // Méthodes pour gérer la liste de chauffages
    public void addHeater(HeaterEntity heater) {
        heaters.add(heater);
        heater.setGreenhouse(this); // Maintenir la cohérence bidirectionnelle
    }

    public void removeHeater(HeaterEntity heater) {
        heaters.remove(heater);
        heater.setGreenhouse(null); // Maintenir la cohérence bidirectionnelle
    }

    // Ajouter cette méthode dans GreenHouseEntity
    public void setHeaters(List<HeaterEntity> newHeaters) {
        if (this.heaters == null) {
            this.heaters = new ArrayList<>();
        }

        // Dissocier les chauffages qui ne sont plus dans la nouvelle liste
        for (HeaterEntity existingHeater : new ArrayList<>(this.heaters)) {
            if (!newHeaters.contains(existingHeater)) {
                existingHeater.setGreenhouse(null); // Supprimer l'association
                this.heaters.remove(existingHeater); // Retirer de la collection
            }
        }

        // Ajouter ou mettre à jour les chauffages dans la collection
        for (HeaterEntity newHeater : newHeaters) {
            if (!this.heaters.contains(newHeater)) {
                newHeater.setGreenhouse(this); // Associer à cette serre
                this.heaters.add(newHeater); // Ajouter à la collection
            }
        }
    }

    // Getters et setters
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SensorEntity getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(SensorEntity currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public SensorEntity getCurrentHumidity() {
        return currentHumidity;
    }

    public void setCurrentHumidity(SensorEntity currentHumidity) {
        if (currentHumidity != null && currentHumidity.getSensorType() != SensorType.HUMIDITY) {
            throw new IllegalArgumentException("Le capteur doit être de type HUMIDITY.");
        }
        this.currentHumidity = currentHumidity;
    }


    public Double getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(Double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }

    public FieldEntity getField() {
        return field;
    }

    public void setField(FieldEntity field) {
        this.field = field;
    }

    public List<HeaterEntity> getHeaters() {
        return heaters;
    }

}
