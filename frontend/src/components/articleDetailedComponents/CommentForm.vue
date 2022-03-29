<template>
{{this.id}}
{{this.formType}}
<div v-if="notClicked" class="text-muted" @click="this.notClicked=false">add comment</div>
<form v-else @submit.prevent="postCommentForm">
  <div class="row">
    <div class="form-group col-md-10">
      <textarea rows="2" class="form-control" name="content" v-model="dataForm.content" placeholder="Add comments to discuss with other users. Comments must be at least 15 characters."></textarea>
    </div>
    <div class="form-group col-md-2 p-2">
      <button type="submit" class="btn btn-primary btn-small small" >
        add&nbsp;comment
      </button>
    </div>	    
    <div class="text-muted" :class="{'text-danger': this.isvalidate === true}">Enter at least 15 characters</div>
  </div>
</form>

</template>

<script>
import axios from "axios"
export default {
  name: 'CommentForm',

  props: ['id', 'formType'],

  data() {
    return {
      dataForm: {
        content: "",
      },
      notClicked: true,
      invalidate: false,
    }
  },

  methods: {
    getPath() {
      if (this.formType == 'article') {
        return `/article/${this.id}/comment/add`;
      }
      else {
        return `/answer/${this.id}/comment/add`;
      }
    },
    postCommentForm() {
      if (this.dataForm.content.length > 15) {
       axios.post(this.getPath(), this.dataForm)
        .then(() => {
         //this.$router.push({path:'/questions'});
         this.$router.go(0);
        })
        .catch((res) => {
         console.log(res);
        })
      }
      else {
        console.log("error");
        this.invalidate = true;
      }
    },
  },
}
</script>