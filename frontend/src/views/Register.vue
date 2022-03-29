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

  <form @submit.prevent="postRegisterForm">
    <h1 class="h3 mb-3 fw-normal title-font p-2 button" v-on:click="this.$router.push({path:'/login'})">Sign up</h1>
    <div class="form-floating p-1">
      <input type="text" class="form-control" id="loginId" v-model="formData.loginId">
      <label for="loginId">Login Id</label>
    </div>
    <div class="form-floating p-1">
      <input type="password" class="form-control" id="password"  v-model="formData.password">
      <label for="password">Password</label>
    </div>
    <div class="form-floating p-1">
      <input type="password" class="form-control" id="password"  v-model="formData.passwordConfirm">
      <label for="password">Password Confirm</label>
    </div>
    <button class="w-100 btn login-color p-2" type="submit">Sign up</button>
    

    <div class="row">
    <div class="checkbox col-md-12 p-2">
      <label>
        <span>Already have an account?&nbsp;</span><a style="text-decoration: none;"><router-link to="/login" style="text-decoration: none; color: orange">Log in</router-link></a>
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
  
  data() {
    return {
      formData: {
        loginId: "",
        password: "",
        passwordConfirm: "",
      }
    }
  },
  methods: {
    postRegisterForm() {
      axios.post(`/login/register`, this.formData)
      .then((res) => {
        console.log(res);
        this.$router.go();
      })
      .catch(() => {
        console.log("error");
      })
    }
  }
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