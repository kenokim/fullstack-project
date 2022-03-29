<template>
<div>
    <div class="row">
      <h2 class="col-md-10">Tags</h2>
    </div> 
    <div class="row">
      <h4 class="small col-md-7">A tag is a keyword or label that categorizes your question with other, similar questions. Using the right tags makes it easier for others to find and answer your question.</h4>
    </div>
    <br>
    <h4 class="col-md-4">total {{this.totalNumTags}} tags</h4>
    <div class="row">
      <div class="col-md-3"><input class="form-control" type="search" placeholder="Filter by tag name" aria-label="Search" v-model="query" v-on:keyup.enter="sendQuery"></div>
      <div class="col-md-5"></div>
      <button class="btn btn-outline-secondary col-md-1" v-on:click="[this.sort='popular',sendQuery()]" :class="{active:sort==='popular'}">Popular</button>
      <button class="btn btn-outline-secondary col-md-1" v-on:click="[this.sort='name',sendQuery()]" :class="{active:sort==='name'}">Name</button>
      <button class="btn btn-outline-secondary col-md-1" v-on:click="[this.sort='new',sendQuery()]" :class="{active:sort==='new'}">New</button>
    </div>
    <hr>

  </div>
<div v-if="this.rendered">
  <div v-if="tags" class="row">
    <div class="col-md-3" v-for="tag in tags" :key="tag.id">    
      <div class="bg-light border"><tagList :tag="tag"></tagList></div>
    </div>
  </div>
</div>
<button class="btn btn-outline-secondary" v-if="this.page > 0" v-on:click="goPrev">prev</button>
<button class="btn btn-outline-secondary" v-if="this.page < totalNumTags / numTagsToGet - 1" v-on:click="goNext">next</button>
<span>.. {{this.page}} page</span>
</template>

<script>
import axios from "axios"
import tagList from "@/components/TagList"

export default {
  
  name: "AllTags",

  components: {
    tagList,

  },

  data() {
    return {
      tags: null,
      page: 0,
      numTagsToGet: 20,
      totalNumTags: 0,
      rendered: false,
      sort: 'new',
      query: '',
    }
  },

  methods: {
    sendQuery() {
      console.log(this.query),
      this.page = 0,
      this.getAllTags()
    },
    getAllTags() {
      const params = new URLSearchParams([['page', this.page], ['numItems', this.numTagsToGet], ['sort', this.sort], ['query', this.query]]);
      axios.get(`/tags`, {params})
      .then((res) => {
        this.totalNumTags = res.data.totalNumTags;
        this.tags = res.data.tags;
        this.rendered = true;
      })
      .catch()
    },
    goPrev() {
      this.page -= 1;
      console.log(this.page);
      this.getAllTags();
    },
    goNext() {
      this.page += 1;
      console.log(this.page);
      this.getAllTags();
    },
  },

  mounted() {
    this.getAllTags();
  },

}
</script>