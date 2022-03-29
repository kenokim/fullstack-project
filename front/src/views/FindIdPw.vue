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
      
      @media (min-width: 468px) {
        .container {
            width: 468px;
        }
      }
      .router-link {
        color: black;
      }

}
 </component>
</head>




<main class="form-signin row">

<div class="container col-md-4 p-3 h-100">
  <h1 class="h3 mb-3 fw-normal title-font p-2 button" v-on:click="this.$router.go()">Buffer Overflow</h1>
  <h1>테스트용 아이디: test  패스워드: test! 입니다</h1>
  <p v-on:click="this.$router.push({path:'/login', name:'Login'})">로그인 페이지로 가기</p>
  <p class="mt-5 mb-3 text-muted">&copy; 2022 Buffer Overflow</p>
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
          axios.post(`/login`, this.formData)
          .then((res) => {
            if (res.status === 200) {
              this.$router.push({path:'/board'});
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
.container {
  margin-top: 80px;
}
</style>