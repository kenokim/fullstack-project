<template>
  <div>User information would be included here!</div>
  <button class="btn btn-outline-primary" v-on:click="logout">로그아웃</button>
</template>
<script>
import axios from "axios"
export default {
  methods: {
    logout() {
      axios.post(`/login/logout`)
      .then(this.$router.push({path:'/questions'}))
    }
  },
}
</script>