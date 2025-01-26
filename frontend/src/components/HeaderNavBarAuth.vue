<template>
  <header class="m-8 mt-4 rounded-xl flex justify-center bg-white shadow-sm">
    <nav class="w-full mx-12 flex flex-row justify-between items-center">
      <RouterLink to="/" class="w-24">
        <img src="/logo.png" alt="logo"/>
      </RouterLink>
      <div class="text-2xl font-bold text-gray-800 ml-8">
        Serrato
      </div>
      <!-- Navigation des liens -->
      <div class="flex space-x-14 rounded-xl">
        <RouterLink to="/blog" class="text-sm font-semibold text-gray-700 hover:text-green-600">
          Blog
        </RouterLink>
        <RouterLink to="/about" class="text-sm font-semibold text-gray-700 hover:text-green-600">
          About
        </RouterLink>
        <RouterLink to="/vosserres" class="text-sm font-semibold text-gray-700 hover:text-green-600">
          Vos serres
        </RouterLink>
        <button class="text-sm font-semibold text-white bg-green-500 rounded-lg px-4 py-1 focus:outline-none hover:bg-green-600">
          {{ username }}
        </button>
        <button @click="logout" class="text-sm font-semibold text-gray-700 hover:text-green-600 focus:outline-none">
          Déconnexion
        </button>
      </div>
    </nav>
  </header>
</template>

<script>
export default {
  name: "HeaderNavBarAuth",
  data (){
    return{
      username: localStorage.getItem("username") || "Utilisateur",
    };
  },
  methods: {
    logout() {
      localStorage.removeItem("token");
      localStorage.removeItem("username");
      this.$router.push("/");
      this.$emit("logout");
    },
  },
};

</script>



