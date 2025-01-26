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
        <RouterLink to="/login" class="text-sm font-semibold text-gray-700 hover:text-green-600">
          Login
        </RouterLink>
        <RouterLink to="/create-account" class="text-sm font-semibold text-gray-700 hover:text-green-600">
          Create Account
        </RouterLink>
      </div>
    </nav>
  </header>
</template>

<script>
export default {
  name: "HeaderNavBarBlog",
};
</script>



