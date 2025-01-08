<template>
  <div class="h-screen flex items-center justify-center bg-gray-100">
    <div class="absolute top-44 left-1/2 transform -translate-x-1/2 bg-white p-8 rounded-lg shadow-lg w-full max-w-sm">
      <h1 class="text-3xl font-bold text-center mb-16 text-gray-800">Connectez-vous!</h1>
      <div class="mb-4">
        <input type="text" v-model="name" placeholder="Votre identifiant" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500"/>
      </div>
      <div class="mb-6">
        <input type="password" v-model="password" placeholder="Votre mot de passe" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500"/>
      </div>
      <div class="flex justify-center mb-4">
        <button v-on:click="Login" class="w-full bg-green-500 text-white py-2 rounded-lg hover:bg-green-600 focus:outline-none focus:ring-2 focus:ring-green-500">
          Connexion
        </button>
      </div>
      <p class="text-center text-sm">
        <router-link to="/create-account" class="text-gray-800 hover:underline">Vous n'avez pas de compte ?</router-link>
      </p>
    </div>
  </div>
</template>


<script>
//méthode login à faire au niveau du v-on: click ce sera avec l'API d'Antoine
export default {
  name:"LoginPage",
}

</script>