<script setup lang="ts">
let appVersion = import.meta.env.VITE_APP_VERSION;
</script>
<template>
  <footer class="footer text-center">
    <div class="card">
      <div class="card-header bg-inverse">
        <div class="footer-wrapper">
          <div class="row d-flex justify-content-center flex-nowrap footer-logo">
            <a href="https://www.nysed.gov">
              <img src="https://eservices.nysed.gov/nysed-assets/images/nysed-sm-logo.png" alt="NYSED Logo" />
            </a>
          </div>
          <h6 class="text-md-center">New York State Education Department</h6>
          <br />
          <ul class="list-inline text-center footer-links">
            <li class="list-inline-item">
              <a class="text-white" href="https://www.nysed.gov" target="_blank" rel="noopener noreferrer">NYSED Home</a>
            </li>
            <li class="list-inline-item">
              <a class="text-white" href="https://www.nysed.gov/terms-of-use" target="_blank" rel="noopener noreferrer"
              >Terms of Use</a
              >
            </li>
            <li class="list-inline-item">
              <a class="text-white" href="https://www.nysed.gov/privacy-policy" target="_blank" rel="noopener noreferrer"
              >Privacy Policy</a
              >
            </li>
          </ul>
          <h6 v-if="appVersion" class="text-md-center">Build Version {{ appVersion }}</h6>
        </div>
      </div>
    </div>
  </footer>
</template>
<style scoped>
footer {
  margin-top: 2rem;
}
</style>
