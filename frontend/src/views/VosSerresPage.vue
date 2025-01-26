<template>
  <div class="sm:px-32 flex flex-wrap justify-center">
    <!-- Cartes des serres -->
    <GreenhouseCard v-for="greenhouse in greenhouses" :key="greenhouse.id" :greenhouseInfo="greenhouse"/>
    <!-- Bouton pour créer une nouvelle serre -->
    <RouterLink to="/vosserres/createserre">
      <div class="w-40 h-44 m-2 p-2 rounded-xl bg-green-500 shadow-lg flex items-center justify-center text-white hover:bg-green-600">
        <h3 class="text-center font-bold">Ajouter une nouvelle serre !</h3>
      </div>
    </RouterLink>
  </div>
</template>

<script>
import GreenhouseCard from "../components/GreenhouseCard.vue";

export default {
  name: "VosSerresPage",
  components: {
    GreenhouseCard,
  },
  data() {
    return {
      greenhouses: [], // Liste des serres
    };
  },
  mounted() {
    this.fetchGreenhouses(); // Récupérer les serres au montage du composant
  },
  methods: {
    async fetchGreenhouses() {
      const url = "http://localhost:8080/api/greenhouses";
      try {
        const response = await fetch(url);
        if (!response.ok) throw new Error("Erreur lors de la récupération des serres.");
        this.greenhouses = await response.json();
      } catch (error) {
        console.error("Erreur fetch:", error);
      }
    },
  },
};
</script>
