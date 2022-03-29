<template>
<div class="mt-2">
<div class="row">
  <div class="col-md-1">
    <!--<likeComponent :likeType="'article'" :numLikes="contentBody.numLikes" />-->
    <div class="row">
    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-emoji-heart-eyes-fill" viewBox="0 0 16 16" @click="upvote">
      <path d="M8 0a8 8 0 1 0 0 16A8 8 0 0 0 8 0zM4.756 4.566c.763-1.424 4.02-.12.952 3.434-4.496-1.596-2.35-4.298-.952-3.434zm6.559 5.448a.5.5 0 0 1 .548.736A4.498 4.498 0 0 1 7.965 13a4.498 4.498 0 0 1-3.898-2.25.5.5 0 0 1 .548-.736h.005l.017.005.067.015.252.055c.215.046.515.108.857.169.693.124 1.522.242 2.152.242.63 0 1.46-.118 2.152-.242a26.58 26.58 0 0 0 1.109-.224l.067-.015.017-.004.005-.002zm-.07-5.448c1.397-.864 3.543 1.838-.953 3.434-3.067-3.554.19-4.858.952-3.434z"/>
    </svg>
    <br>
    <h4 class="text-center">{{contentBody.numLikes}}</h4>
    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-emoji-frown-fill" viewBox="0 0 16 16" @click="downvote">
      <path d="M8 16A8 8 0 1 0 8 0a8 8 0 0 0 0 16zM7 6.5C7 7.328 6.552 8 6 8s-1-.672-1-1.5S5.448 5 6 5s1 .672 1 1.5zm-2.715 5.933a.5.5 0 0 1-.183-.683A4.498 4.498 0 0 1 8 9.5a4.5 4.5 0 0 1 3.898 2.25.5.5 0 0 1-.866.5A3.498 3.498 0 0 0 8 10.5a3.498 3.498 0 0 0-3.032 1.75.5.5 0 0 1-.683.183zM10 8c-.552 0-1-.672-1-1.5S9.448 5 10 5s1 .672 1 1.5S10.552 8 10 8z"/>
    </svg>
  </div>
    </div>
    <div class="col-md-11">
      <div v-html="contentBody.content"></div>
      <hr>
      <div class="row">

        <!-- Author of this component -->
        <div v-if="contentBody.isAuthor" class="col-md-8">
          <span><a href="#" class="text-muted" style="text-decoration: none;">edit &nbsp; </a></span>
          <span><a href="#" class="text-muted" style="text-decoration: none;">delete</a></span>
        </div>
        <!-- else -->
        <div v-else class="col-md-8">
          <span><a href="#" class="text-muted" style="text-decoration: none;">follow &nbsp; </a></span>
          <span><a href="#" class="text-muted" style="text-decoration: none;">flag &nbsp; </a></span>
        </div>

        <div class="col-md-4">
          <div v-if="contentType == 'article'">
            <userList :user="contentBody.author" :addition="'Asked at ' + contentBody.createdAt" />
          </div>
          <div v-else>
            <userList :user="contentBody.author" :addition="'Answered at ' + contentBody.createdAt" />
          </div>
        </div>
      </div>
      <div class="mx-4">
        <tagButton v-for="tag in contentBody.tags" :key="tag.id" :tagName="tag" />
      </div>
      <div v-for="comment in contentBody.comments" :key="comment.id">
        <hr>
        <comment :comment="comment" />
      </div><hr>
      <commentForm :id="contentBody.id" :formType="this.contentType" />
    </div>
  </div>
</div>
<br>
</template>

<script>
import axios from "axios"
import userList from "@/components/UserList"
import comment from "@/components/articleDetailedComponents/Comment"
import commentForm from "@/components/articleDetailedComponents/CommentForm"
//import likeComponent from "@/components/articleDetailedComponents/LikeComponent"

export default {
  props: ['contentType', 'contentBody'],
  components: {
    userList,
    comment,
    commentForm,
    //likeComponent,
  },
  methods: {
    getPath() {
      return `/${this.contentType}/${this.contentBody.id}`;
    },  
    upvote() {
      console.log("upvote");
      axios.post(this.getPath() + `/like`).
      then(() => {
        
      })
      .catch((e) => {
        console.log("Already liked it");
        console.log(e);
      })
    },

    downvote() {
      console.log("downvote");
      axios.post(this.getPath() + `/dislike`);
    },
  },

}
</script>