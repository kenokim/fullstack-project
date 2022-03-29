<template>
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
  <div class="container">
  <div>
  <img src="@/assets/Veigar_Render_3.png" width="35" height="35">
  <span href="#" class="navbar-brand px-2" v-on:click="this.$router.push({path:`/questions`})">Buffer Overflow</span>
  </div>
  <div class="collapse navbar-collapse" id="navbarCollapse">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item">
        </li>
        <li class="nav-item" href="#">
          <span class="nav-link" v-on:click="this.$router.push({path:`/home`})">About</span>
        </li>
        <li class="nav-item">
          <span class="nav-link" href="#">Products</span>
        </li>
        <!--<li class="nav-item">
          <span class="nav-link disabled" href="#">For Teams</span>
        </li>-->
        <li class="nav-item">
          <form class="form-inline">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
          </form>
        </li>
      </ul>
  </div>
  <div>
    <div v-if="this.loggedIn">
      <button type="button" class="btn btn-outline-primary btn-sm mx-1" v-on:click="this.$router.push({path:`/users/admin`, loginid:'test'})">Admin</button>
      <button type="button" class="btn btn-primary btn-sm">Messages</button>

    </div>
    <div v-else>
      <button type="button" class="btn btn-outline-primary btn-sm mx-1" v-on:click="this.$router.push({path:'/login', name:'Login'})">Log in</button>
      <button type="button" class="btn btn-primary btn-sm" v-on:click="this.$router.push({path:'/login', name:'Register'})">Sign up</button>
    </div>
  </div>
  </div>
</nav>
</template>
<script>
import axios from "axios"
export default {
  name: "Navbar",

  props: ['authorized'],

  methods: {
    checkLoggedIn() {
      console.log("로그인 정보를 확인합니다.")
      axios.post(`/login/check`)
      .then(() => {
        this.loggedIn = true;
      })
      .catch(() => {
        console.log("로그인이 필요합니다.");
      })
    },

  },
  data() {
    return {
      loggedIn: false,
      loginId: 'test',

    }
  },
  mounted() {
    this.checkLoggedIn();
  },
}
</script>

<style>
.fixed-top {
    position: fixed;
}
body {
    padding-top: 00px;
}


</style>
