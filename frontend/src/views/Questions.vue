<template>
  <mainNavbar />
  <div class="container">
    <div class="row">
      <div class="col-md-2 float">
        <mainSidebar />
      </div>
      <div class="col-md-10">
        <router-view :key="$route.query"/>
      </div>
    </div>
  </div>
  <mainFooter />
</template>

<script>
  
</script>

<style>

</style>