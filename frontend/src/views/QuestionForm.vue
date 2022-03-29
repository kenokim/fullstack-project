<template>
<div class="container">
  <h4>Ask a public question</h4>
  <form @submit.prevent="postArticleForm">
    <div class="form-group">
      <label for="title">Title<br> <span class="require">*</span> <small>Be specific and imagine you’re asking a question to another person</small></label>
      <input type="text" class="form-control" name="title" v-model="dataForm.title"/>
    </div>
    <div class="form-group">
      <label for="content">Body<br> <span class="require">*</span><small>Include all the information someone would need to answer your question</small></label>
      <textarea rows="5" class="form-control" name="content" v-model="dataForm.content"></textarea>
    </div>
    <div class="form-group">
      <label for="tag">Tags<br> <span class="require">*</span> <small>Add up to 5 tags to describe what your question is about</small></label>
      <input placeholder="e.g. (sql-server, xml, javascript)" type="text" class="form-control" name="tags" v-model="dataForm.tags"/>
    </div>
    <div class="form-group">
      <button type="submit" class="btn btn-primary mt-2">
        Post your question
      </button>
    </div>	    
  </form>
</div>
</template>

<script>
import axios from "axios"
export default {
  name: 'ArticleForm',

  data() {
    return {
      dataForm: {
        title: "",
        content: "",
      }
    }
  },

  methods: {
    validation() {
      
    },
    postArticleForm() {
      console.log(`hi`);
      axios.post(`/article/add`, this.dataForm)
      .then(() => {
        this.$router.push({path:'/questions'});
      })
      .catch((res) => {
        console.log(res);
      })
    },
  },
}
</script>