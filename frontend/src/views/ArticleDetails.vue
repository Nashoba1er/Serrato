<template>
  <div class="px-8 flex flex-col items-center">
    <div class="flex sm:w-6/7 w-full p-4 bg-white rounded-lg shadow-lg" v-if="article.title && article.content">
      <div class="w-8/9 pl-4">
        <h1 class="text-3xl font-bold mb-4">{{ article.title }}</h1>
        <p class="text-gray-700 leading-relaxed max-w-7xl overflow-wrap-anywhere">
          {{ article.content }}
        </p>
      </div>
    </div>
    <!-- Optionally, you can add a loading state -->
    <div v-else>
      <p>Chargement en cours...</p>
    </div>
  </div>
</template>






<script>
export default {
  name: "ArticleDetails",
  data() {
    return {
      article: {}, // Nous allons stocker ici l'article spécifique
      articles: [], // Contiendra la liste de tous les articles
    };
  },
  mounted() {
    // Récupérer tous les articles
    const url = 'http://localhost:3000/articles/';
    const options = {
      method: 'GET',
    };
    fetch(url, options)
        .then(response => response.json())
        .then(data => {
          // Filtrer l'article avec l'ID passé dans la route
          this.article = data.find(item => item.id === parseInt(this.$route.params.id));
        })
        .catch(error => console.error("Erreur fetch", error));
  },
};
</script>



