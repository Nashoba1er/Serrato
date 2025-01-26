<template>
  <div class="h-screen flex flex-col items-center justify-center bg-gray-100">
    <h1 class="text-2xl font-bold mb-6">Créer un chauffage</h1>
    <form @submit.prevent="createHeater" class="bg-white p-6 rounded-lg shadow-lg w-full max-w-sm">
      <div class="mb-4">
        <label for="heaterName" class="block mb-2 font-medium text-gray-700">Nom du chauffage</label>
        <input
            id="heaterName"
            type="text"
            v-model="heater.name"
            placeholder="Entrez un nom"
            class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500"
            required
        />
      </div>
      <button
          type="submit"
          class="w-full bg-blue-500 text-white py-2 rounded-lg hover:bg-blue-600 focus:outline-none focus:ring-2 focus:ring-blue-500"
      >
        Créer le chauffage
      </button>
    </form>
  </div>
</template>

<script>
export default {
  name: "CreateHeatersPage",
  data() {
    return {
      heater: {
        name: "", // Champ à saisir par l'utilisateur
      },
    };
  },
  methods: {
    async createHeater() {
      try {
        const response = await fetch("http://localhost:8080/api/heaters", {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({
            name: this.heater.name,
            statusSensor: {
              id: 0, // Valeur par défaut
              name: "HeaterSensor",
              sensorValue: 0,
              sensorType: "STATUS",
            },
            greenhouseId: 0, // Par défaut, ajustez en fonction des besoins
          }),
        });

        if (response.ok) {
          console.log("Chauffage créé avec succès !");
          this.$router.push("/vosserres"); // Redirection après la création
        } else {
          console.error("Erreur lors de la création du chauffage :", response.statusText);
        }
      } catch (error) {
        console.error("Erreur réseau :", error);
      }
    },
  },
};
</script>
