<template>
  <div class="h-screen flex items-center justify-center bg-gray-100">
    <div class="absolute top-44 left-1/2 transform -translate-x-1/2 bg-white p-8 rounded-lg shadow-lg w-full max-w-sm">
      <h1 class="text-3xl font-bold text-center mb-16 text-gray-800">Connectez-vous!</h1>
      <!-- Message conditionnel -->
      <p v-if="message" class="text-center text-sm text-red-500 mb-6">{{ message }}</p>
      <form @submit.prevent="login">
        <div class="mb-4">
          <label for ="user_login"></label>
          <input type="text" id="user_login" v-model="user.username" placeholder="Votre identifiant" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500"/>
        </div>
        <div class="mb-6">
          <label for ="user_password"></label>
          <input type="text" id="user_password" v-model="user.password" placeholder="Votre mot de passe" class="w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500"/>
        </div>
        <div class="flex justify-center mb-4">
          <button type="submit" class="w-full bg-green-500 text-white py-2 rounded-lg hover:bg-green-600 focus:outline-none focus:ring-2 focus:ring-green-500">
            Connexion
          </button>
        </div>
      </form>
      <p class="text-center text-sm">
        <router-link to="/create-account" class="text-gray-800 hover:underline">Vous n'avez pas de compte ?</router-link>
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name:"LoginPage",
  data(){
    return {
      user: {
        username:'',
        password:''
      }
    }
  },
  computed: {
    message() {
      const reason = this.$route.query.reason;
      if (reason === "auth-required") {
        return "Veuillez vous connecter pour accéder à cette page.";
      }
      return null;
    },
  },
  methods: {
    login(){
      fetch('http://localhost:8080/api/users',{
        headers:{
          'Accept':'application/json',
          'Content-type':'application/json'

        },
        method:"POST",
        body:JSON.stringify(this.user)
      })
          .then(blob =>blob.json())
          .then(data => {
            console.log(data)
            localStorage.setItem('username', this.user.username);
            localStorage.setItem('token',data.access_token)
            this.$emit("login-success"); //pour que ça charge tout seul la bonne HeadNavBar
            console.log("Événement login-success émis");
            this.$router.push('/vosserres')
          })
          .catch(error => console.log(error))
    }
  }
}
</script>
