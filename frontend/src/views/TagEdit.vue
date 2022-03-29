<template>
<span class="fs-5">Editing tag info for </span>
<tagButton v-if="this.tag != null" :key="tag.id" :tagName="tag.tagName"/>




<p>Privileges: you need at least 0 reputations to edit a tag.</p>
<div class="alert alert-primary" :class="{'alert-danger': this.notEnough }">You have: {{info}}</div>
<p>Info: how to get reputations? Write articles, and get likes from other users. One like counts one reputations.</p>
<br>
<div class="form-group">
  <label for="content">Usage guidance (excerpt)<br> <span class="require">*</span><small :class="{'text-danger':this.validate==false}">(plain text only, no Markdown formatting, must be at least 10 characters)
    </small></label>
  <textarea v-if="tag != null" rows="5" class="form-control" :class="{'is-invalid':this.validate==false}" name="content" v-model="this.dataForm.content" :placeholder="[[tag.explanation]]"></textarea>
</div>

<button @click.prevent="editTag" class="btn btn-primary m-1">Save Edits</button>
<button @click.prevent="this.$router.go(-1)" class="btn btn-outline-primary m-1">Cancel</button>

</template>
<script>
import axios from "axios"
export default {
  props: ['tagName'],

  data() {
    return {
      tag: null,
      dataForm: {content:"",},
      info: "로그인이 필요합니다.",
      rendered: false,
      notEnough: false,
      validate: true,
    }
  },

  methods: {
    validation() {
      if (this.dataForm.content.length < 10) {
        this.validate = false
        
      }
      else {
          this.validate = true
      }
    },
    editTag() {
      this.validation()
      if (this.validate)(
      axios.post(`/tags/${this.tagName}/edit`, this.dataForm)
      .then(()=> {
        this.$router.push(`/tags`)
      })
      .catch(() => {
        this.notEnough = true
      })
      )
    },
  },

  mounted() {
    axios.get(`/tags/${this.tagName}`)
    .then(res => {
      this.tag = res.data;
    })
    .catch(e => {
      console.log(e);
    })
    axios.get(`/login/info`)
    .then(res => {
        if (res.data != null)
        this.info = "total reputation " + res.data.reputation,
        this.rendered = true
    })
    .catch(e => {
      console.log(e);
    })
  },

}
</script>