<template>
  <div class="sm:px-32 flex flex-wrap justify-center">
    <ArticleCard v-for="article in articles" :key="article.id" :articleInfo="article"/>
  </div>
</template>


<script>
import ArticleCard from "../components/ArticleCard.vue";
export default {
  name: "WelcomeBlog",
  components:{
    ArticleCard,
  },
  data(){
    return{
      articles: [],
    }
  },
  mounted() {
    const url = 'http://localhost:3000/articles';
    const options = {
      method: 'GET',
    };
    fetch(url, options).then(response => response.json()).then(data => this.articles = data).catch(error => console.error("Erreur fetch", error));
  },
};
</script>