<!--Composante racine de mon application Vue, il est monté dans le fichier index.html
HeadNavBar sera visible sur toutes les pages de l'application-->
<template>
  <HeaderNavBarBlog/>
  <RouterView/> <!--permet d'ouvrir les différentes pages avec le router-->
</template>

<script>
import HeaderNavBarBlog from "@/components/HeaderNavBarBlog.vue";

export default {
  name: 'App',
  components: {HeaderNavBarBlog},
};
</script>


