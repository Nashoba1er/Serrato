INSERT INTO SP_SENSOR(id, name, sensor_value, sensor_type) VALUES(-10, 'Temperature exterieur', 20, 'TEMPERATURE');
INSERT INTO SP_SENSOR(id, name, sensor_value, sensor_type) VALUES(-9, 'Temperature Serre1', 21, 'TEMPERATURE');
INSERT INTO SP_SENSOR(id, name, sensor_value, sensor_type) VALUES(-8, 'Humidity Serre1', 22, 'HUMIDITY');
INSERT INTO SP_SENSOR(id, name, sensor_value, sensor_type) VALUES(-11, 'Temperature Heater1', 23, 'STATUS');


INSERT INTO SP_FIELD(id,name,outside_temperature_sensor_id) VALUES(-7,'Champ',-10);

INSERT INTO SP_GREENHOUSE(id, name, target_temperature,current_humidity_sensor_id,current_temperature_sensor_id,field_id) VALUES(-6, 'greenhouse', 20.0,-8,-9,-7);

INSERT INTO SP_HEATER(id, status_sensor_id, name, greenhouse_id) VALUES(-5, -11, 'Heater 1', -6);

INSERT INTO SP_USER(id, username, password) VALUES (0,'Titouan','1234');
INSERT INTO SP_USER(id, username, password) VALUES (1,'Antoine','0000');
INSERT INTO SP_USER(id, username, password) VALUES (2,'Eya','1234');
