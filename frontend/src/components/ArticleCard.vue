<template>
  <RouterLink :to="`blog/article/${articleInfo.id}`">
    <div class="w-40 h-44 m-2 p-2 rounded-xl bg-white shadow-lg" @mouseover="isHovering=true" @mouseleave="isHovering=false">
      <img
          :src="
         isHovering
          ? require(`@/${articleInfo.gif}`)
          : require(`@/${articleInfo.poster}`)
        "
          class="rounded-xl"
      />
      <h3 class="title my-2 text-xs ">{{articleInfo.title}}</h3>
    </div>
  </RouterLink>
</template>

<script>
export default{
  name:"ArticleCard",
  props: {
    articleInfo: Object,
  },
  data(){
    return{
      isHovering: false,
    }
  },
};
</script>

<style>
.title{
  display:-webkit-box;
  -webkit-box-orient: vertical;
  -webkit-line-clamp: 3;
  overflow: hidden;
  text-overflow:ellipsis;
  line-height:1.5em;
  font-weight:bolder;
}
</style>