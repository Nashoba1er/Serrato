INSERT INTO SP_SENSOR(id, name, sensor_value, sensor_type) VALUES(-10, 'Temperature greenhouse 2', 21.3, 'TEMPERATURE');
INSERT INTO SP_SENSOR(id, name, sensor_value, sensor_type) VALUES(-9, 'Heater 1 status greenhouse 1', 1.0, 'STATUS');
INSERT INTO SP_SENSOR(id, name, sensor_value, sensor_type) VALUES(-8, 'Heater 2 status greenhouse 1', 0.0, 'STATUS');
INSERT INTO SP_SENSOR(id, name, sensor_value, sensor_type) VALUES(-7, 'Heater 1 status greenhouse 2', 0.0, 'STATUS');
INSERT INTO SP_SENSOR(id, name, sensor_value, sensor_type) VALUES(-6, 'Heater 2 status greenhouse 2', 0.0, 'STATUS');

INSERT INTO SP_SENSOR(id, name, sensor_value, sensor_type) VALUES(-5, 'Humidity greenhouse 1', 22, 'HUMIDITY');
INSERT INTO SP_SENSOR(id, name, sensor_value, sensor_type) VALUES(-4, 'Humidity greenhouse 2', 23, 'HUMIDITY');


INSERT INTO SP_GREENHOUSE(id, name, target_temperature,current_humidity_sensor_id,current_temperature_sensor_id) VALUES(-10, 'greenhouse1',20.0,-5,-8);
INSERT INTO SP_GREENHOUSE(id, name, target_temperature,current_humidity_sensor_id,current_temperature_sensor_id) VALUES(-9, 'greenhouse2', 20.0,-4,-9);

INSERT INTO SP_HEATER(id, status_sensor_id, name, greenhouse_id) VALUES(-10, -9, 'Heater 1', -10);
INSERT INTO SP_HEATER(id, status_sensor_id, name, greenhouse_id) VALUES(-9, -8, 'Heater 2', -10);
INSERT INTO SP_HEATER(id, status_sensor_id, name, greenhouse_id) VALUES(-8, -7, 'Heater 1', -9);
INSERT INTO SP_HEATER(id, status_sensor_id, name, greenhouse_id) VALUES(-7, -6, 'Heater 2', -9);