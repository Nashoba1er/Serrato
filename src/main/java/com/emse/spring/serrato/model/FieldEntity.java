package com.emse.spring.serrato.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SP_FIELD")
public class FieldEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToOne
    @JoinColumn(name = "outside_temperature_sensor_id", nullable = false)
    private SensorEntity outsideTemperature;

    @OneToMany(mappedBy = "field", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GreenHouseEntity> greenhouses = new ArrayList<>();

    // Default constructor
    public FieldEntity() {
    }

    // Constructor with required fields
    public FieldEntity(String name, SensorEntity outsideTemperature) {
        this.name = name;
        this.outsideTemperature = outsideTemperature;
    }

    // Methods to manage the relationship with greenhouses
    public void addGreenHouse(GreenHouseEntity greenhouse) {
        greenhouses.add(greenhouse);
        greenhouse.setField(this); // Set the field reference in GreenHouseEntity
    }

    public void removeGreenHouse(GreenHouseEntity greenhouse) {
        greenhouses.remove(greenhouse);
        greenhouse.setField(null); // Remove the field reference
    }

    // Getters and setters
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

    public SensorEntity getOutsideTemperature() {
        return outsideTemperature;
    }

    public void setOutsideTemperature(SensorEntity outsideTemperature) {
        this.outsideTemperature = outsideTemperature;
    }

    public List<GreenHouseEntity> getGreenhouses() {
        return greenhouses;
    }

    public void setGreenhouses(List<GreenHouseEntity> greenhouses) {
        this.greenhouses = greenhouses;
    }
}
