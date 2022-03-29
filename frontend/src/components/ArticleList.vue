<template>
<div class="row g-0 pb-2 bg-light">
<div class="col-md-1 text-center">
  <div>{{article.numLikes}}</div>
  <div class="small">Likes</div>
  <div>{{article.numAnswers}}</div>
  <div class="small">Answers</div>
  <div>{{article.numViews}}</div>
  <div class="small">Views</div>
</div>
<div class="col-md-11">
  <div class="bg-light rounded-3 text-left p-1" v-if="article.content">
    <a href class="col-md-12 fs-6"  style="text-decoration:none" :id="article.id" v-on:click="showArticleById(article.id)">{{article.title}}</a>
    <p v-if="article.content.length < 100" class="col-md-12 small">{{article.content}}</p>
    <p v-else class="col-md-12 small">{{article.content.substring(0, 100) + "..."}}</p>
    


    <!--<div class="row pb-0">
      <time class="col-md-2 small">{{article.createdAt}}</time>
      <h3 class="col-md-2 small" v-if="!article.isAnonymous">{{article.author}}</h3>
      <h3 class="col-md-2 small" v-if="article.isAnonymous">익명</h3>
      <p class="col-md-2 small">답글 {{article.numAnswers}}</p>
      <p class="col-md-2 small">조회수 {{article.numViews}}</p>
      <p class="col-md-2 small">좋아요 {{article.numLikes}}</p>
      <p class="col-md-2 small">댓글 {{article.numComments}}</p>
    </div>-->
    
    <div class="row">
      <div class="col-md-8">
      <span>Tags: </span>
      <span v-if="!article.tags.length">No tags</span>
      <!--<button class="btn btn-outline-primary btn-sm m-1" v-for="tag in article.tags" :key="tag.id">{{tag}}</button>-->
      <tagButton v-for="tag in article.tags" :key="tag.id" :tagName="tag"/>
      </div>
      <div class="col-md-4">
        <userList :user="article.author" />
      </div>
    </div>
  </div>
</div>
</div>
</template>

<script>
import userList from "@/components/UserList"
export default {

  name: "ArticleList",

  props: ['article'],

  components: {
    userList,
  },

  methods: {
    showArticleById(id) {
      console.log(id);
      this.$router.push({
          path: `/questions/${id}`,
          props: { id: `${id}` },
      })
    },
  },

}
</script>