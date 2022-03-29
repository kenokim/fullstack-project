<template>
<div>answer</div>
<!--  <div class="row">
    <div class="col-md-1">
    <likeComponent :likeType="'answer'" :numLikes="answer.numLikes" />
    </div>
    <div class="col-md-11">
      <div v-html="answer.content"></div>
      <hr>
      <div class="row">
        <div class="col-md-8">
          <span><a href="#" class="text-muted" style="text-decoration: none;">edit &nbsp; </a></span>
          <span><a href="#" class="text-muted" style="text-decoration: none;">delete</a></span>
        </div>
        <div class="col-md-4">
          <userList :user="answer.author" :addition="'Asked at ' + answer.createdAt" />
        </div>
      </div>

      <div v-for="comment in answer.comments" :key="comment.id">
        <hr>
        <comment :comment="comment" />
      </div><hr>
      <commentForm :id="article.id" :formType="'article'" />
    </div>
    




  </div>-->
</template>
<script>
export default {
  props: ['answer'],

}
</script>