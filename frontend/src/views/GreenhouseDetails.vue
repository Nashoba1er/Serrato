<template>
  <div class="h-screen flex bg-gray-100">
    <!-- Languette de gauche -->
    <aside class="ml-4 mb-6 w-1/3 bg-white rounded-lg shadow-lg p-6">
      <h2 class="text-lg text-center font-bold mb-6">Informations sur la serre</h2>
      <div class="mb-6">
        <p class="text-gray-700 text-lg">
          Température actuelle :
          <span class="font-bold">{{ greenhouse.currentTemperature }}</span>°C
        </p>
      </div>
      <div class="mb-6">
        <p class="text-gray-700 text-lg">
          Humidité actuelle :
          <span class="font-bold">{{ greenhouse.currentHumidity }}</span>%
        </p>
      </div>
      <div class="mb-6">
        <p class="text-gray-700 text-lg">
          Seuil de température :
          <span class="font-bold">{{ greenhouse.targetTemperature }}</span>°C
        </p>
      </div>
    </aside>

    <!-- Contenu principal avec le bouton -->
    <main class="flex-1 flex items-center justify-center">
      <button
          class="bg-blue-500 text-white py-4 px-8 rounded-lg text-lg font-bold shadow-lg hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500"
          @click="createHeater"
      >
        Créer un heaters
      </button>
    </main>
  </div>
</template>

<script>
export default {
  name: "GreenhouseDetails",
  data() {
    return {
      greenhouse: {
        id: null,
        name: "",
        targetTemperature: 22.5, // Valeur par défaut du seuil de température
        currentTemperature: null,
        currentHumidity: null,
      },
    };
  },
  mounted() {
    this.fetchGreenhouseData();
  },
  methods: {
    async fetchGreenhouseData() {
      try {
        const response = await fetch(
            `http://localhost:8080/api/greenhouses/${this.$route.params.id}`
        );
        const data = await response.json();
        this.greenhouse = {
          id: data.id,
          name: data.name,
          targetTemperature: data.targetTemperature,
          currentTemperature: data.temperatureSensor?.sensorValue, // Ajustez si nécessaire
          currentHumidity: data.humiditySensor?.sensorValue, // Ajustez si nécessaire
        };
      } catch (error) {
        console.error("Erreur lors du chargement des données de la serre :", error);
      }
    },

    // Méthode pour gérer la création d'un chauffage (heater)
    createHeater() {
      console.log("Redirection ou création d'un heater...");
      this.$router.push("/vosserres/createheater");
    },
  },
};
</script>


