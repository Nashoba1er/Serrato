<template>
  <RouterLink :to="`vosserres/${greenhouseInfo.id}`">
    <div class="w-40 h-48 m-2 p-4 rounded-xl bg-white shadow-lg hover:shadow-2xl transition-shadow">
      <h3 class="title my-2 text-sm font-bold text-center">{{ greenhouseInfo.name }}</h3>
      <p class="text-xs text-gray-600">
        Température cible : <span class="font-bold">{{ greenhouseInfo.targetTemperature }}°C</span>
      </p>
      <RouterLink
          :to="`vosserres/${greenhouseInfo.id}`"
          class="block mt-4 text-blue-500 text-center hover:underline"
      >
        Voir les informations
      </RouterLink>
    </div>
  </RouterLink>
</template>

<script>
export default {
  name: "GreenhouseCard",
  props: {
    greenhouseInfo: {
      type: Object,
    },
  },
};
</script>

