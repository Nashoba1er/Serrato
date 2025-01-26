<template>
  <div class="h-screen flex items-center justify-center bg-gray-100">
    <div class="absolute top-44 left-1/2 transform -translate-x-1/2 bg-white p-8 rounded-lg shadow-lg w-full max-w-sm">
      <h1 class="text-3xl font-bold text-center mb-16 text-gray-800">Créez votre compte svp !</h1>
      <form @submit.prevent="ajout ">
        <div class="mb-4">
          <label for="user_name" class="sr-only">Identifiant</label>
          <input type="text" id="user_name" v-model="user.username" placeholder="Votre identifiant" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500"/>
        </div>
        <div class="mb-6">
          <label for="user_password" class="sr-only">Mot de passe</label>
          <input type="password" id="user_password" v-model="user.password" placeholder="Votre mot de passe" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500"/>
        </div>
        <div class="flex justify-center mb-4">
          <button type="submit" class="w-full bg-green-500 text-white py-2 rounded-lg hover:bg-green-600 focus:outline-none focus:ring-2 focus:ring-green-500">
            Créez mon compte
          </button>
        </div>
      </form>
      <p class="text-center text-sm">
        <router-link to="/login" class="text-gray-800 hover:underline">
          Vous avez déjà un compte ?
        </router-link>
      </p>
    </div>
  </div>
</template>


<script>
export default {
  name: "CreateAccountPage",
  data() {
    return {
      user: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    async ajout() {
      console.log("Création d'utilisateur :", this.user);


      try {
        const response = await fetch("http://localhost:8080/api/users", {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(this.user),
        });


        if (!response.ok) {
          const errorData = await response.json();
          throw new Error(errorData.message || "Une erreur est survenue.");
        }


        const data = await response.json();
        console.log("Utilisateur créé avec succès :", data);


        alert("Compte créé avec succès !");
        this.$router.push("/login");
      } catch (error) {
        console.error("Erreur lors de la création de l'utilisateur :", error);
        alert("Erreur : " + error.message);
      }
    },
  },
};
</script>
