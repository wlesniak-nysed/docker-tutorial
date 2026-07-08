<script setup lang="ts">
import Header from './components/Header.vue';
import Footer from './components/Footer.vue';
</script>

<template>
  <Header />
  <main>
    <router-view />
  </main>
  <Footer />
</template>

<style>
@import url("https://eservices.nysed.gov/nysed-assets/stylesheets/4.x/nysed-bootstrap.css");
</style>
<style scoped>
main {
  margin: 0 auto;
}
</style>
