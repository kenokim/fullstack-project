<template>
<div v-if="this.rendering">
<!-- Title area -->
<div class="py-4">

  <div class="row">
    <div class="col-md-10">
      <h3>{{article.title}}</h3>
    </div>
    <div class="col-md-2">
      <button class="btn btn-primary small" v-on:click="this.$router.push({path:`/questions/ask`})">Ask&nbsp;Question</button>
    </div>
  </div>
  
  <div class="row">
    <div class="col-md-2">
      <time class="col-md-2">Asked {{article.createdAt}}</time>
    </div>
    <div class="col-md-2">
      <div>Active {{article.createdAt}}</div>
    </div>
    <div class="col-md-2">
      <div>Viewed {{article.numViews}} times</div>
    </div>

  </div>

</div>

<!-- Article area --><hr>
<contentBody :contentBody="article" :contentType="'article'"/>
<hr>
<!-- Answer area -->
<br>
<div class="row">
  <h3 class="col-md-8">{{article.answers.length}}&nbsp;answers</h3>
  <!--<div class="col-md-4"><button class="btn btn-outline-secondary">New</button></div>-->
</div>
<br>
<div>
  <div v-for="answer in article.answers" :key="answer.id">
    <contentBody :contentType="'answer'" :contentBody="answer" />
  </div>
</div>

<answerForm :articleId="article.id"/>
</div>
</template>

<script>

import axios from "axios"
import contentBody from "@/components/articleDetailedComponents/ContentBody"
import answerForm from "@/components/articleDetailedComponents/AnswerForm"
export default {

  name: "ArticleDetailed",
  data() {
      return {
        deleteupdateauthorized: false,
        authorized: true,
        duplicateLike: false,
        formData: {
          content: "",
        },
        numLikes: 0,
        liked: false,
        articles: null,
        rendering: false,
      }
  },
  

  mounted() {

    axios.get(`/article/` + this.$route.params.id)
    .then((res) => {
      this.article = res.data;
      this.rendering = true;
      console.log(`${this.$route.params.id}`);
      this.checkAuthority();
      this.numLikes = this.article.numLikes;
    })
    .catch(() => {
      this.$router.push({path:`/questions`});
    })
    
    
  },
  components: {
    contentBody,
    answerForm,
  },

  methods: {
    checkAuthority() {
      if (this.article.authorized) this.deleteupdateauthorized = true
    },
    deleteArticle() {
      console.log("delete");
      axios.post(`/article/` + this.article.id + `/delete`)
      .then(() => {
        this.$router.go();  
      })
      .catch(() => {
        console.log("err");
        this.$router.go();
      })
      //this.$router.go();
      
    },
    addLike() {
        if (this.liked) {
          this.duplicateLike=true;
          return;
        }
        else{
        axios.post(`/article/` + this.article.id + `/like`)
        .then((res) => {
            console.log(res);
            this.numLikes += 1;
            if (res.response.status == '200') {
              this.numLikes += 1;
            }
        })
        .catch((res) => {
            console.log(res);
            if (res.response.status == '401') {
              this.authorized = false;
            }
            if (res.response.status == '406') {
              this.duplicateLike = true;
              this.liked = true;
              console.log("이미 좋아요 한 게시글 입니다.");
            }
            else {
                console.log("Unknown error");
            }
        })
        }
      }
    },
    
    postAnswer() {
      console.log("Ok, somebody's trying to add an answer, one participation point.")
      axios.post(`/article/${this.article.id}/answers/add`, this.formData)
      .then(() => {
        
      })
    },
    
  }
</script>