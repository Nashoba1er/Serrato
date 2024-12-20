package com.emse.spring.serrato.model;

import jakarta.persistence.*;

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

    @Column(name = "target_temperature", nullable = false) // Température cible stockée directement
    private Double targetTemperature;

    public GreenHouseEntity() {
    }

    public GreenHouseEntity(String name, SensorEntity currentTemperature, Double targetTemperature) {
        this.name = name;
        this.currentTemperature = currentTemperature;
        this.targetTemperature = targetTemperature;
    }

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

    public Double getTargetTemperature() {
        return targetTemperature;
    }

    public void setTargetTemperature(Double targetTemperature) {
        this.targetTemperature = targetTemperature;
    }
}
