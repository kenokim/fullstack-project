<template>
<nav class="sidebar bg-light">
  <div class="p-2">
    <br>
    <p v-on:click="goHome">Home</p>
    <p v-on:click="goQuestions">Questions</p>
    <p v-on:click="goTags">Tags</p>
    <p v-on:click="goUsers">Users</p>
  </div>
</nav>
</template>

<script>
export default {
  methods: {
    goHome() {
      this.$router.push({path: `/home`})
    },
    goQuestions() {
      this.$router.push({path: `/questions`})
      .then()
    },
    goUsers() {
      this.$router.push({path: `/users`})
      .then()
    },
    goTags() {
      this.$router.push({path: `/tags`})
      .then()
    }
  },
}
</script>

<style>

.sidebar {
  height: 100%;
}

@media(max-width: 768px) {
  .sidebar {
    display: none;
  }
}
</style>

