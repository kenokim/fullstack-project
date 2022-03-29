<template>
<div class="row g-0 pb-2">
  <div>
    <div class="row">
      <tagButton class="col-md-2" :key="tag.id" :tagName="tag.tagName"/>
    </div>
    <p class="small mt-2">{{tag.explanation}}</p>
    <p class="small text-muted" v-if="tag.numArticles != null">{{tag.numArticles}} questions</p>
    <p class="small text-muted" v-else>0 questions</p>
    <!--<button class="btn btn-outline-secondary small" v-on:click="this.$router.push({path:`/tags/edit/${tag.tagName}`, props:{tagName: tag.tagName}})">태그 수정</button>-->
  </div>
</div>
</template>

<script>
export default {

  name: "TagList",

  props: ['tag'],

  methods: {
  
  },
}
</script>