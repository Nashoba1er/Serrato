<template>
  <header class="m-8 mt-4 rounded-xl flex justify-center bg-white shadow-sm">
    <nav class="w-full mx-12 flex felx-row justify-between items-center">
      <RouterLink to="/" class="w-10"> <!--clique sur le logo retourne page d'accueil (localhost)-->
        <img src="/logo.png" alt="logo"/>
      </RouterLink>
      <form>
        <input type ="text" placeholder="Search..." class="my-3 ml-8 px-4 py3 rounded-xl bg-gray-100 text-sm"/>
      </form>
      <font-awesome-icon icon="magnifying-glass" class="text-2xl ml-6"/>
    </nav>
  </header>
</template>

<script>
  export default{
    name:"HeaderNavBar"
  }
</script>

