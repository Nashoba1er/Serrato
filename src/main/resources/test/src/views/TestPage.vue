<template>
  <div class="h-screen flex flex-col items-center justify-center bg-gray-100">
    <h1 class="text-3xl font-bold mb-6">Test de Données</h1>
    <div class="bg-white p-6 rounded-lg shadow-lg w-1/2 text-center">
      <p class="text-lg font-semibold mb-4">
        Température : <span class="text-blue-500">{{ data.temperature }}°C</span>
      </p>
      <button
          v-on:click="fetchData"
          class="bg-blue-500 text-white px-4 py-2 rounded-lg hover:bg-blue-600"
      >
        Rafraîchir les données
      </button>
    </div>
  </div>
</template>

<script>
export default {
  name: "TestPage",
  data() {
    return {
      data: {
        temperature: "N/A",
      },
    };
  },
  methods: {
    async fetchData() {
      try {
        const response = await fetch("http://localhost:8080/api/data");
        if (!response.ok) {
          throw new Error(`Erreur HTTP : ${response.status}`);
        }
        const result = await response.json();
        this.data.temperature = result.temperature;
        this.data.humidity = result.humidity;
      } catch (error) {
        console.error("Erreur lors de la récupération des données :", error);
        alert("Impossible de récupérer les données !");
      }
    },
  },
  mounted() {
    this.fetchData();
  },
};
</script>
