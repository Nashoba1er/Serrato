<template>
  <div class="flex flex-col items-center px-8">
    <!-- Section supérieure : Message du fondateur -->
    <div class="bg-white w-full max-w-5xl p-8 mt-6 shadow-lg rounded-lg">
      <h1 class="text-3xl font-bold text-gray-800 mb-4">Les mots du fondateur</h1>
      <p class="text-gray-700 leading-relaxed">
        "Chez Serrato, notre mission est d’apporter une révolution dans le monde de l’agriculture grâce à nos serres intelligentes. Nous croyons que la technologie peut transformer la manière dont nous cultivons, en optimisant les ressources, en augmentant la production et en réduisant l’impact environnemental. Merci de faire partie de cette vision avec nous."
        <br />
        – <span class="font-semibold">L'équipe Serrato</span>
      </p>
    </div>

    <!-- Section inférieure -->
    <div class="w-full max-w-5xl mt-12 flex flex-col sm:flex-row justify-between">
      <!-- Partie gauche : Réseaux sociaux -->
      <div class="flex flex-col items-start w-full sm:w-1/2 mb-8 sm:mb-0">
        <h2 class="text-xl font-bold text-gray-800 mb-4">Retrouvez-nous sur les réseaux !</h2>
        <ul class="flex space-x-12 bg-white rounded-lg mt-2 p-8 shadow-lg">
          <li>
            <a href="https://www.instagram.com" target="_blank" class="block">
              <img src="/instagram.jpg" alt="Instagram" class="w-10 h-10 hover:opacity-80 transition-opacity duration-300"/>
            </a>
          </li>
          <li>
            <a
                href="https://www.facebook.com"
                target="_blank"
                class="block"
            >
              <img
                  src="/facebook.png"
                  alt="Facebook"
                  class="w-10 h-10 hover:opacity-80 transition-opacity duration-300"
              />
            </a>
          </li>
          <li>
            <a
                href="https://www.linkedin.com"
                target="_blank"
                class="block"
            >
              <img
                  src="/linkedin.png"
                  alt="LinkedIn"
                  class="w-10 h-10 hover:opacity-80 transition-opacity duration-300"
              />
            </a>
          </li>
        </ul>

      </div>

      <!-- Partie droite : Contacts -->
      <div class="flex flex-col items-start w-full sm:w-1/2 p-4 bg-white rounded-lg shadow-md">
        <h2 class="text-xl font-bold text-gray-800 mb-4">Contacts</h2>
        <p class="text-gray-700 leading-relaxed">
          <strong>Email :</strong> contact@serrato.com
        </p>
        <p class="text-gray-700 leading-relaxed mt-2">
          <strong>Téléphone :</strong> +33 6 23 45 67 89
        </p>
        <p class="text-gray-700 leading-relaxed mt-2">
          <strong>Adresse :</strong> 123 Rue des Serres, Paris, France
        </p>
      </div>
    </div>
  </div>
</template>


<script>
export default {
  name: "AboutPage",
};
</script>

