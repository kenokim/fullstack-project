<template>
<div class="container">
  <footer class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
    <p class="col-md-4 mb-0 text-muted">&copy; Buffer Overflow</p>


    <ul class="nav col-md-4 justify-content-end">
      <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">Home</a></li>
      <li class="nav-item"><a href="#" class="nav-link px-2 text-muted">FAQs</a></li>
      <li class="nav-item"><a v-on:click="this.$router.push({path:'/home'})" class="nav-link px-2 text-muted">About</a></li>
    </ul>
  </footer>
</div>

</template>

<script>
</script>

<style>
.fixed {
    position: fixed;
}
</style>