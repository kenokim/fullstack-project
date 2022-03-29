<template>
<div class="m-1" style="display: inline;">
  <button class="btn btn-outline-primary btn-sm" 
  v-on:click="this.queryQuestionByTag"
  @mouseover="doMouseOver"
  @mouseleave="doMouseLeave"
  id="tagButton"
  data-toggle="tooltip" data-placement="top" data-html="true" v-bind:title='tagData'
  >{{tagName}}</button>
</div>
  <span v-show="showTooltip">Hello Tooltip!</span>
</template>



<script>
import axios from "axios"
export default {
  props: ['tagName'],
  data() {
    return {
      showTooltip: false,
      tagData: null,
    }
  },
  methods: {
    queryQuestionByTag() {
      this.$router.push({path:'/questions', query: {tag: this.tagName}})

    },
    doMouseOver() {
      if (this.tagData !== null) return
      console.log("ok, somebody's entered the button");
      setTimeout(() => {
          this.showTooltip = false;
          axios.get(`/tags/${this.tagName}/tooltip`)
          .then(res => {
            this.tagData = res.data;
          })
        }, 1000);
    },
    doMouseLeave() {
      setTimeout(() => {this.showTooltip = false;}, 2000);
    }
  }
}
</script>