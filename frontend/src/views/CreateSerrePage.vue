<template>
  <div class="p-8">
  <h1 class="text-2xl font-bold mb-6 text-center">Créer une serre</h1>
  <form @submit.prevent="submitForm" class="max-w-lg mx-auto bg-white p-6 rounded-lg shadow-md">
    <!-- Nom de la serre -->
    <div class="mb-4">
      <label for="name" class="block text-sm font-medium text-gray-700">Nom de la serre</label>
      <input
          id="name"
          v-model="formData.name"
          type="text"
          placeholder="Nom de la serre"
          class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring focus:ring-blue-500 focus:outline-none"
          required
      />
    </div>


    <!-- Température cible -->
    <div class="mb-4">
      <label for="targetTemperature" class="block text-sm font-medium text-gray-700">Température cible</label>
      <input
          id="targetTemperature"
          v-model.number="formData.targetTemperature"
          type="number"
          placeholder="Température cible"
          class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring focus:ring-blue-500 focus:outline-none"
          required
      />
    </div>


    <!-- Nom du capteur d'humidité -->
    <div class="mb-4">
      <label for="humiditySensor" class="block text-sm font-medium text-gray-700">Nom du capteur d'humidité</label>
      <input
          id="humiditySensor"
          v-model="formData.humiditySensor.name"
          type="text"
          placeholder="Nom du capteur d'humidité"
          class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring focus:ring-blue-500 focus:outline-none"
          required
      />
    </div>


    <!-- Nom du capteur de température -->
    <div class="mb-4">
      <label for="temperatureSensor" class="block text-sm font-medium text-gray-700">Nom du capteur de température</label>
      <input
          id="temperatureSensor"
          v-model="formData.temperatureSensor.name"
          type="text"
          placeholder="Nom du capteur de température"
          class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:ring focus:ring-blue-500 focus:outline-none"
          required
      />
    </div>


    <!-- Bouton d'envoi -->
    <button
        type="submit"
        class="w-full bg-green-500 text-white py-2 rounded-lg hover:bg-green-600 focus:outline-none focus:ring focus:ring-green-500"
    >
      Créer la serre
    </button>
  </form>
  </div>
</template>


<script>
export default {
  name: "CreateSerrePage",
  data() {
    return {
      formData: {
        name: "",
        targetTemperature: 0,
        humiditySensor: {
          id: null,
          name: "",
          sensorValue: 0,
          sensorType: "Humidity",
        },
        temperatureSensor: {
          id: null,
          name: "",
          sensorValue: 0,
          sensorType: "Temperature",
        },
        heaters: [], // Vous pouvez ajouter un champ pour les chauffages si nécessaire
      },
    };
  },
  methods: {
    async submitForm() {
      const url = "http://localhost:8080/api/greenhouses"; // À adapter selon votre backend
      const options = {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(this.formData),
      };


      try {
        const response = await fetch(url, options);
        if (!response.ok) throw new Error("Erreur lors de la création de la serre");
        const data = await response.json();
        alert("Serre créée avec succès !");
        console.log(data);
        // Vous pouvez rediriger l'utilisateur après la création :
        this.$router.push("/vosserres");
      } catch (error) {
        console.error("Erreur lors de l'envoi des données :", error);
        alert("Une erreur est survenue lors de la création de la serre.");
      }
    },
  },
};
</script>
