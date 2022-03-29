<template>
<div class="px-3 bg-light">
<div class="small ">{{addition}}</div>
<div class="row small">
  <div class="col-md-2 px-1 py-2">
    <img src="@/assets/eaf6bf97-6c5f-4b56-bc21-75ee37980968.png" width="35" height="35">
  </div>
  <div class="col-md-10">
    <a span class="m-1" style="text-decoration: none;"> {{user.loginId}}</a>
    <br>
    <span class="m-1">Qs: {{user.numArticles}}</span>
    <span class="m-1">Ans: {{user.numAnswers}}</span>
    <br>
    <span class="m-1">Reputation: {{user.reputation}}</span>
  </div>
</div>
</div>
</template>

<script>
export default {

  name: "UserList",

  props: ['user', 'addition'],

  methods: {
    showUserById(id) {
      console.log(id);
      this.$router.push({
          path: `/users/${id}`,
          props: { id: `${id}` },
      })
    },
  },

}
</script>