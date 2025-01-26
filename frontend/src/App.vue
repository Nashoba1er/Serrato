<!--Composante racine de mon application Vue, il est monté dans le fichier index.html
HeadNavBar sera visible sur toutes les pages de l'application-->
<template>
  <HeaderNavBarBlog v-if="!isAuthenticated" @login-success="handleLoginSuccess"/>
  <HeaderNavBarAuth v-else  @logout="handleLogout"/>
  <RouterView @login-success="handleLoginSuccess" /> <!--permet d'ouvrir les différentes pages avec le router-->
</template>

<script>
import HeaderNavBarBlog from "@/components/HeaderNavBarBlog.vue";
import HeaderNavBarAuth from "@/components/HeaderNavBarAuth.vue";

export default {
  name: 'App',
  components: {HeaderNavBarAuth, HeaderNavBarBlog},
  data(){
    return {
      isAuthenticated: !!localStorage.getItem("token"), // Propriété réactive
    };
  },
  methods: {
    handleLogout() {
      this.isAuthenticated = false;
    },
    handleLoginSuccess(){
      this.isAuthenticated = true;
    },
  },
};
</script>


