package com.emse.spring.serrato.model;

import jakarta.persistence.*;

@Entity
@Table(name = "SP_HEATER") // Table pour la classe HeaterEntity
public class HeaterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "greenhouse_id", nullable = false) // Association avec une serre
    private GreenHouseEntity greenhouse;

    @ManyToOne
    @JoinColumn(name = "status_sensor_id", nullable = false) // Association avec un capteur pour le statut
    private SensorEntity status;

    // Constructeur par défaut
    public HeaterEntity() {
    }

    // Constructeur avec les champs non nullables
    public HeaterEntity(String name, GreenHouseEntity greenhouse, SensorEntity status) {
        this.name = name;
        this.greenhouse = greenhouse;
        this.status = status;
    }

    // Getters et setters
    public Long getId() {
        return id;
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

    public GreenHouseEntity getGreenhouse() {
        return greenhouse;
    }

    public void setGreenhouse(GreenHouseEntity greenhouse) {
        this.greenhouse = greenhouse;
    }

    public SensorEntity getStatus() {
        return status;
    }

    public void setStatus(SensorEntity status) {
        this.status = status;
    }
}
