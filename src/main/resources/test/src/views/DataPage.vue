<template>
  <div class="h-screen flex bg-gray-100">
    <!-- Languette de gauche -->
    <aside class="ml-4 mb-6 w-1/5 bg-white rounded-lg shadow-lg p-6">
      <h2 class="text-lg text-center font-bold mb-4">Vos seuils</h2>
      <div class="mb-6">
        <label for="humidity" class="block mb-2 font-medium text-gray-700">Seuil d'humidité</label>
        <input
            id="humidity"
            type="range"
            min="0"
            max="100"
            step="1"
            class="w-full cursor-pointer"
            v-model="humidity"
        />
        <p class="mt-2 text-gray-500">Valeur choisie : <span class="font-bold">{{ humidity }}</span>%</p>
      </div>
      <div>
        <label for="temperature" class="block mb-2 font-medium text-gray-700">Seuil de température</label>
        <input
            id="temperature"
            type="range"
            min="-10"
            max="40"
            step="1"
            class="w-full cursor-pointer"
            v-model="temperature"
        />
        <p class="mt-2 text-gray-500">Valeur choisie : <span class="font-bold">{{ temperature }}</span>°C</p>
      </div>
    </aside>

    <!-- Contenu principal -->
    <main class="flex-1 flex flex-col items-center p-6">
      <h1 class="text-2xl font-bold mb-6">Votre serre</h1>
      <div class="w-full h-full bg-white shadow-lg rounded-lg flex items-center justify-center">
        <!-- Graphiques -->
        <p class="text-gray-500 text-lg">[Graphiques de température et d'humidité ici]</p>
      </div>
    </main>
  </div>
</template>

<script>
//utiliser partie Antoine pour stocker la valeur du seuil !
export default {
  name: "DataPage",
  data(){
    return{
      humidity:50,
      temperature:30,
    };
  },
};
</script>
