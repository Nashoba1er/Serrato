package com.emse.spring.serrato;

import com.emse.spring.serrato.model.GreenHouseEntity;
import com.emse.spring.serrato.model.FakeEntityBuilder;
import com.emse.spring.serrato.record.GreenHouse;
import com.emse.spring.serrato.mapper.GreenHouseMapper;
import com.emse.spring.serrato.record.Heater;
import com.emse.spring.serrato.record.Sensor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;


public class GreenHouseMapperTest {
    @Test
    void shouldMapRoom() {
        // Arrange
        GreenHouseEntity greenHouseEntity = FakeEntityBuilder.createFieldEntity(-10L, "Field")
                .getGreenhouses()
                .stream()
                .min(Comparator.comparing(GreenHouseEntity::getName))
                .orElseThrow(IllegalArgumentException::new);

        // Act
        GreenHouse greenHouse = GreenHouseMapper.toRecord(greenHouseEntity);

        // Assert
        GreenHouse expectedGreenHouse = new GreenHouse(
                -10L,
                "greenhouse1",
                20.0,
                new Sensor(
                        -5L,
                        "Humidity greenhouse 1",
                        22.0,
                        "HUMIDITY"
                ),
                new Sensor(
                        -10L,
                        "Temperature greenhouse 2",
                        21.3,
                        "TEMPERATURE"
                ),
                List.of(
                        new Heater(
                                -10L,
                                "Heater 1",
                                new Sensor(
                                        -9L,
                                        "Heater 1 status greenhouse 1",
                                        1.0,
                                        "STATUS"
                                ),
                                -10L
                        ),
                        new Heater(
                                -9L,
                                "Heater 2",
                                new Sensor(
                                        -8L,
                                        "Heater 2 status greenhouse 1",
                                        0.0,
                                        "STATUS"
                                ),
                                -10L
                        )
                )
        );

        Assertions.assertThat(greenHouse).usingRecursiveAssertion().isEqualTo(expectedGreenHouse);
    }

}