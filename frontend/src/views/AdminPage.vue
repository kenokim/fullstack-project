<template>
  <mainNavbar />
  <div class="container">
      <div>
        <router-view :key="$route.query"/>
      </div>
  </div>
</template>

<script>
  
</script>

<style>

</style>