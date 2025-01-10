#include "DHT.h" //libraire pour DHT22
#include <PubSubClient.h> //libraire pour MQTT
#include <WiFi.h> //librairie pour le WIFI

//Partie WIFI
const char* ssid = "PWME";
const char* password = "welcome to PWME";

//Partie MQQT
const char* mqtt_server = "10.1.2.3";//Adresse IP du broker
const int mqtt_port = 1883; //port utilisé par le broker 
const char *topic = "pwme/groupe9/temperature";
WiFiClient espClient;           // Client Wi-Fi pour l'ESP32
PubSubClient client(espClient); // Client MQTT

#define DHTPIN 33 
#define DHTTYPE DHT22  
#define RED 21
#define GREEN 16
#define ORANGE 17
#define BUZZER 19
#define ENABLE 14
#define IN1 32
#define IN2 15
int seuil = 30;
unsigned long previousSensorMillis = 0;
unsigned long previousMQTTMillis = 0;
const long sensorInterval = 2000;
const long mqttInterval = 5000;

DHT dht(DHTPIN, DHTTYPE);

// Fonction de connexion au Wi-Fi
void setup_wifi() {
  Serial.begin(9600);
  Serial.print("Connexion au réseau Wi-Fi\n");
  WiFi.begin(ssid, password);

  while (WiFi.status() != WL_CONNECTED) {
    delay(1000);
    Serial.print("Connecting to Wifi\n");
  }
  Serial.println("\nConnecté !");
}

//Fonction de connexion au broker
void reconnect() {
  // Boucle de connexion tant que l'ESP32 n'est pas connecté
  while (!client.connected()) {
    Serial.print("Tentative de connexion au broker MQTT...\n");

    String clientId = "espClient-" + String(millis());  // Identifiant unique basé sur le temps écoulé sinon bug
    if (client.connect(clientId.c_str())) { // Connexion avec l'identifiant unique
      Serial.println("Connecté !");
    } else {
      Serial.print("Échec, tentative dans 2 secondes...\n");
      delay(2000);
    }
  }
}

void setup() {

  Serial.begin(9600); 
  dht.begin();     
  
  pinMode(RED, OUTPUT);
  pinMode(GREEN, OUTPUT);
  pinMode (ORANGE, OUTPUT);
  pinMode(BUZZER, OUTPUT);
  pinMode(ENABLE, OUTPUT);
  pinMode(IN1, OUTPUT);
  pinMode(IN2, OUTPUT);
  digitalWrite(ENABLE, HIGH);

  setup_wifi();
  
  client.setServer(mqtt_server, mqtt_port);
}

void loop(){

  unsigned long currentMillis = millis();
  float h = dht.readHumidity();
  float t = dht.readTemperature();
  if(isnan(h)){
    if (h<seuil){
      digitalWrite(RED,HIGH);
      tone (BUZZER, 600);
      digitalWrite(IN1, HIGH);
      digitalWrite(IN2, LOW);
      if (IN1 == HIGH or IN2 == LOW){
        digitalWrite(RED, LOW);
        digitalWrite(ORANGE, HIGH);
      }
    }
  }else{
    digitalWrite(GREEN, HIGH);
    digitalWrite(ORANGE, LOW);
    noTone(BUZZER);
    digitalWrite(IN1, LOW);
    digitalWrite(IN2, LOW);
  }

  //Partie capteur et OLED et Moniteur série
  if (currentMillis - previousSensorMillis >= sensorInterval) {
    previousSensorMillis = currentMillis;

    if (isnan(h) || isnan(t)) {
      Serial.println(F("Erreur : Données non reçues !"));
      return;
    }
    Serial.print(F("Humidité : "));
    Serial.print(h);
    Serial.println(F("%"));
    Serial.print(F("Température : "));
    Serial.print(t);
    Serial.println(F("°C"));

  }

  if (currentMillis - previousMQTTMillis >= mqttInterval) {
      previousMQTTMillis = currentMillis;

    if (isnan(h) || isnan(t)) {
      return;
    }

    char temp_str[8];
    char hum_str[8];
    dtostrf(t, 6, 2, temp_str); // Convertir float en chaîne pour protocole mqtt, le 6 c'est la largeur min de la chaîne et le 2 c'est la précision
    dtostrf(h, 6, 2, hum_str);
  
    //JSON
    String message = "{\"temperature\":" + String(temp_str) + ", \"humidité\":" + String(hum_str) +"}";
    client.publish(topic, message.c_str());

  }

  if (!client.connected()) {
    reconnect();
  }
  client.loop();
}
