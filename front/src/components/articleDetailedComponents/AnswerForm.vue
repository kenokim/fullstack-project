<template>
<div>
  <h4>Your answer</h4>
  <form @submit.prevent="postAnswerForm">
    <div class="form-group">
      <textarea rows="5" class="form-control" name="content" v-model="dataForm.content"></textarea>
    </div>
    <div class="text-muted" :class="{'text-danger': this.isvalidate === true}">Answer must be at least 30 characters</div>
    <div class="form-group p-2">
      <button type="submit" class="btn btn-primary btn-small small" >
        Post&nbsp;your&nbsp;answer
      </button>
    </div>	        
  </form>
</div>
</template>
<script>
import axios from 'axios'
export default {
  data() {
    return {
      dataForm: {
        articleId: this.articleId,
        content:"",
      },
    }
  },
  props: ['articleId'],
  methods: {
    postAnswerForm() {
      axios.post(`/answer/add`, this.dataForm)
      .then(() => {
        this.$router.go();
      })
    }  
  },
}
</script>