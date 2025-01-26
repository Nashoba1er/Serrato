<template>
  <div class="h-screen flex gap-4 p-4">
    <!-- Partie gauche (50%) -->
    <div
        class="w-1/2 bg-cover bg-center flex flex-col justify-center items-center text-white p-6 rounded-lg mr-4"
        style="background-image: url('/background.jpg');"
    >
      <h1 class="text-4xl font-bold mb-6 text-center text-white ">
        Accédez aux données de vos serres en temps réel !
      </h1>
      <RouterLink to="/login" class="px-6 py-3 bg-green-500 hover:bg-green-700 text-white text-lg rounded-lg shadow-lg mb-4">
        Connectez-vous
      </RouterLink>
      <div class="text-center text-sm">
        <p class="text-white mb-2">Vous n'avez pas de compte ?</p>
        <RouterLink to="/create-account" class="text-white hover:underline">
          Inscrivez-vous
        </RouterLink>
      </div>
    </div>

    <!-- Partie droite (50%) -->
    <div class="w-1/2 flex flex-col gap-4">
      <!-- Section supérieure (25%) -->
      <div class="h-1/2 bg-white flex flex-col justify-center items-center rounded-lg p-4">
        <h2 class="text-3xl font-bold text-green-700 mb-4">Bienvenue chez Serrato</h2>
        <p class="text-gray-700  text-center leading-relaxed">
          Chez Serrato, nous innovons pour transformer votre production horticole. Nos serres intelligentes allient technologie et durabilité pour optimiser vos cultures et minimiser vos efforts.
        </p>
      </div>
      <!-- Section inférieure (25%) -->
      <div class="h-1/2 bg-white flex flex-col justify-center items-center rounded-lg p-4">
        <h2 class="text-blue-700 text-3xl font-bold mb-2">Nos Services</h2>
        <p class="text-gray-700 text-center leading-relaxed">
          Suivi précis de la température et de l'humidité des serres à distance grâce à des capteurs de haut niveau. Bénéficiez d'une gestion parfaite de vos serres avec des actions à distance comme l'arrosage ou l'aération.
        </p>
      </div>
    </div>
  </div>
</template>


<script>
export default{
  name: "PageAccueil",
};
</script>