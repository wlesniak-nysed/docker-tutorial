<script setup lang="ts">
import Home from './views/Home.vue';
import Header from './components/Header.vue';
</script>

<template>
  <header>
    <Header />
  </header>

  <main>
    <Home />
  </main>
</template>

<style>
@import url("https://eservices.nysed.gov/nysed-assets/stylesheets/4.x/nysed-bootstrap.css");
</style>
<style scoped>
main {
  margin: 0 auto;
}
</style>
