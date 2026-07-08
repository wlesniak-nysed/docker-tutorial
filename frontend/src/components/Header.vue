<script setup lang="ts">
</script>
<template>
  <header>
    <nav class="navbar navbar-expand-sm sticky-top">
      <div class="container-xl">
        <div class="header-logo">
          <a href="http://www.nysed.gov">
            <img src="https://eservices.nysed.gov/nysed-assets/images/nysed-sm-logo.png"
                 alt="NYSED Logo">
          </a><b>NYSED unofficial Docker tutorial sample app</b>
        </div>
        <button class="navbar-toggler ml-auto" type="button" data-toggle="collapse"
                data-target=".navbars" aria-controls="navbar" aria-expanded="false"
                aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse navbars" id="main-collapse">
          <ul class="navbar-nav">
            <li class="nav-item">
              <a class="navbar-brand" href="http://www.nysed.gov">
                <img src="https://eservices.nysed.gov/nysed-assets/images/nysed-sm-logo.png"
                     alt="NYSED Logo">
              </a>
            </li>
            <li class="nav-item">
              <router-link class="nav-link mt-2" to="/">Home</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link mt-2" to="/users">Users</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link mt-2" to="/projects">Projects</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link mt-2" to="/project-memberships">Project Memberships</router-link>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <div>
      <noscript>
        <div class="alert alert-danger hidden-print">
          <span class="fa fa-times"></span> This application requires javascript. You will not be able
          to successfully complete the application without javascript enabled.
        </div>
      </noscript>
      <!--[if IE]>
      <div class="alert alert-danger hidden-print">
      <span class="fa fa-times"></span> Your browser is currently not supported. You may experience
      issues while completing the application. Please consider completing the application from a newer
      browser version.
      </div>
      <![endif]-->
    </div>
  </header>
</template>
<style scoped>
body {
  color: white
}
</style>