<template>
  <component :is="'style'">
    .btn {
        font-size: 15px;
    }
  </component>
  <div>
    <div class="row">
      <h2 class="col-md-10">Users</h2>
    </div>
    <br>
    <h4 class="col-md-4">{{this.totalNumUsers}} users</h4>
    <div class="row">
      <div class="col-md-3"><input class="form-control" type="search" placeholder="Filter by user" aria-label="Search" v-model="query" v-on:keyup.enter="sendQuery"></div>
      <div class="col-md-1"></div>
      <button class="btn btn-outline-secondary small col-md-1" v-on:click="[this.sort='new',sendQuery()]" :class="{active:sort==='new'}">New</button>
      <button class="btn btn-outline-secondary small col-md-2" v-on:click="[this.sort='reputation',sendQuery()]" :class="{active:sort==='reputation'}">Reputation</button>
      <button class="btn btn-outline-secondary small col-md-1" v-on:click="[this.sort='votes',sendQuery()]" :class="{active:sort==='votes'}">Votes</button>
      <button class="btn btn-outline-secondary small col-md-1" v-on:click="[this.sort='edits',sendQuery()]" :class="{active:sort==='edits'}">Edits</button>
      <button class="btn btn-outline-secondary small col-md-1" v-on:click="[this.sort='writes',sendQuery()]" :class="{active:sort==='writes'}">Writes</button>
      <button class="btn btn-outline-secondary small col-md-2" v-on:click="[this.sort='answers',sendQuery()]" :class="{active:sort==='answers'}">Answers</button>
    
    </div>
    <hr>
  </div>
  <div v-if="this.rendered">
  <div v-if="users" class="row">
    <div class="col-md-3" v-for="user in users" :key="user.id">    
      <div><userList :user="user"></userList></div>
    </div>
  </div>
  <button class="btn btn-outline-secondary" v-if="this.page > 0" v-on:click="goPrev">prev</button>
  <button class="btn btn-outline-secondary" v-if="this.page < totalNumUsers / numUsersToGet - 1" v-on:click="goNext">next</button>
  <span>.. {{this.page}} page</span>
  </div>
</template>

<script>
import axios from "axios"
import userList from "@/components/UserList"

export default {
  
  name: "AllUsers",

  components: {
    userList,

  },

  data() {
    return {
      users: null,
      page: 0,
      numUsersToGet: 20,
      totalNumUsers: 0,
      rendered: false,
      sort:'new',
      query: '',
    }
  },

  methods: {
    sendQuery() {
      console.log(this.query),
      this.page = 0,
      this.getAllUsers()
    },
    getAllUsers() {
      const params = new URLSearchParams([['page', this.page], ['numItems', this.numUsersToGet], ['sort', this.sort], ['query', this.query]]);
      axios.get(`/user/users`, {params})//?page=` + this.page + `&numPages=` + this.numusersToGet)
      .then((res) => {
        this.totalNumUsers = res.data.numUsers;
        this.users = res.data.users;
        this.rendered = true;
      })
      .catch()
    },
    goPrev() {
      this.page -= 1;
      console.log(this.page);
      this.getAllUsers();
    },
    goNext() {
      this.page += 1;
      console.log(this.page);
      this.getAllUsers();
    },
  },

  mounted() {
    this.getAllUsers();
  },

}
</script>