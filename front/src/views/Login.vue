<template>
<head>
 <component :is="'style'">
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      .title-font {
          color: black;
      }

      .login-color {
         background-color: orange;
      }
      .vertical-center {
        display: table;
        vertical-align: middle;
      }
      
      @media (min-width: 368px) {
        .container-login {
            width: 368px;
        }
      }
      .router-link {
        color: black;
      }

}
 </component>
</head>




<main class="form-signin row">
<div v-show="this.loginIdError" class="alert alert-danger" role="alert" v-on:click="cancel">로그인 아이디를 입력하세요.</div>
<div v-show="this.passwordError" class="alert alert-danger" role="alert" v-on:click="cancel">패스워드를 입력하세요.</div>
<div v-show="this.unauthorized" class="alert alert-danger" role="alert" v-on:click="cancel">아이디나 비밀번호를 바르게 입력하세요.</div>
<div class="container container-login col-md-4 p-3 h-100">

  <form @submit.prevent="postLoginForm">
    <h1 class="h3 mb-3 fw-normal title-font p-2 button" v-on:click="this.$router.go()">Log in</h1>
    <div class="form-floating p-1">
      <input type="text" class="form-control" id="loginId" v-model="formData.loginId">
      <label for="loginId">Login&nbsp;Id</label>
    </div>
    <div class="form-floating p-1">
      <input type="password" class="form-control" id="password" v-model="formData.password">
      <label for="password">Password</label>
    </div>
    <button class="w-100 btn login-color p-2" type="submit">Log in</button>
    
    <div class="row">
    <div class="checkbox col-md-12 p-2">
      <label>
        <span>Don't have an account?&nbsp;</span>
        <a style="text-decoration: none;"><span v-on:click="this.$router.push({path:'/login', name:'Register'})" style="color: orange;">Sign Up</span></a>
        <!--<router-link to="/register" style="text-decoration: none; color: orange"> 회원가입</router-link>-->
      </label>
    </div>
    </div>

    <p class="mt-5 mb-3 text-muted">&copy; 2022 Buffer Overflow</p>
  </form>
</div>
</main>



</template>

<script>
import axios from "axios"

export default {

  name: "Login",

  components: {
    
  },

  data() {
      return {
        articles: null,
        formData: {
          loginId: "",
          password: "",
        },
    
        loginIdError: false,
        passwordError: false,
        unauthorized: false,
    }
  },
  
  methods: {
    validation() {
        this.loginIdError = false;
        this.passwordError = false;
        this.unauthorized = false;
      if (!this.formData.loginId) {
          this.loginIdError = true;
      }
      if (!this.formData.password) {
          this.passwordError = true;
      }
      if (!this.loginIdError && !this.passwordError) {
          return true;
      }
      return false;
    },
    postLoginForm() {
        this.validation();
        if (!this.passwordError && !this.loginIdError) {
          axios.post(`/login/login`, this.formData)
          .then((res) => {
            if (res.status === 200) {
              this.$router.push({path:'/questions'});
            }
            else {
              this.unauthorized = true;
            }
          })
          .catch((res) => {
              console.log(res);
              this.unauthorized = true;
          })
        }
    },
    cancel() {
      this.loginIdError = false;
      this.passwordError = false;
      this.unauthorized = false;
    },

    registerPage: function() {
      this.$router.push({path:'/register'});
    },


  },

}

</script>

<style scoped>
.alert {
  position: relative;
}
.container-login {
  margin-top: 80px;
}
</style>