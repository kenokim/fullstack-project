<template>
  <component :is="'style'">
    .btn {
        font-size: 15px;
    }
  </component>
  <div>
    <div class="row">
      <h2 class="col-md-10">All Questions</h2>
      <button class="btn btn-primary col-md-2" v-on:click="this.$router.push({path:`/questions/ask`})">Ask Question</button>
    </div>
    <!-- Queried with tags -->
    <div v-if="this.$route.query.tag">
      <h4 v-if="this.$route.query.tag">Tag: {{this.$route.query.tag}}</h4>
      <span>Want to edit the tag?&nbsp;&nbsp;</span>
      <button class="btn btn-secondary" v-on:click="this.$router.push({path:`/tags/edit/${this.$route.query.tag}`})">Edit</button>
    </div>

    <br>
    <h4 class="col-md-4">{{this.totalNumArticles}} questions</h4>
    <div class="row">
      <div class="col-md-3"><input class="form-control" type="search" placeholder="Filter by question" aria-label="Search" v-model="query" v-on:keyup.enter="sendQuery"></div>
      <div class="col-md-1"></div>
      <button class="btn btn-outline-secondary col-md-1" v-on:click="[this.sort='new',sendQuery()]" :class="{active:sort==='new'}">New</button>
      <button class="btn btn-outline-secondary col-md-1" v-on:click="[this.sort='active',sendQuery()]" :class="{active:sort==='active'}">Active</button>
      <button class="btn btn-outline-secondary col-md-2" v-on:click="[this.sort='frequent',sendQuery()]" :class="{active:sort==='frequent'}">Frequent</button>
      <button class="btn btn-outline-secondary col-md-2" v-on:click="[this.sort='unanswered',sendQuery()]" :class="{active:sort==='unanswered'}">Unanswered</button>
      <button class="btn btn-outline-secondary col-md-1" v-on:click="[this.sort='votes',sendQuery()]" :class="{active:sort==='votes'}">Votes</button>
      <button class="btn btn-outline-secondary col-md-1" v-on:click="[this.sort='filter',sendQuery()]" :class="{active:sort==='filter'}">Filter</button>
    </div>
    <hr>
  </div>
  <div v-if="this.rendered">
  <div v-if="articles">
    <div v-for="article in articles" :key="article.id">    
      <div class="m-1"><articleList :article="article"></articleList></div>
    </div>
  </div>
  
  <button class="btn btn-outline-secondary" v-if="this.page > 0" v-on:click="goPrev">prev</button>
  <button class="btn btn-outline-secondary" v-if="this.page < totalNumArticles / numArticlesToGet - 1" v-on:click="goNext">next</button>
  
  </div>
</template>

<script>
import axios from "axios"
import articleList from "@/components/ArticleList"

export default {
  
  name: "AllQuestions",

  components: {
    articleList,

  },

  data() {
    return {
      articles: null,
      page: 0,
      numArticlesToGet: 10,
      totalNumArticles: 0,
      rendered: false,
      sort:'new',
      query:'',
    }
  },
  
  methods: {
    sendQuery() {
      console.log(this.query),
      this.page = 0,
      this.getAllArticles()
    },
    /*getAllArticles() {
      axios.get(`/article/articles?page=` + this.page + `&numPages=` + this.numArticlesToGet)
      .then((res) => {
        this.totalNumArticles = res.data.totalNumArticles;
        this.articles = res.data.articles;
        this.rendered = true;
      })
      .catch()
    },*/
    /*getAllArticles(async) {
      const resp = await axios.get(`/article/articles`);
    },*/
    getAllArticles() {
      const params = new URLSearchParams([['page', this.page], ['numItems', this.numArticlesToGet], ['sort', this.sort], ['query', this.query]]);
      //const params = new URLSearchParams([['tag', this.$route.query.tag]]);
      if (typeof this.$route.query.tag != 'undefined') {
        //params.append('tag', this.$route.query.tag);
        params.append('tag', this.$route.query.tag);
      }
      else {
        console.log(this.$route.query.tag);
      }
      axios.get(`/article/articles`, {params})
      .then((res) => {
        this.totalNumArticles = res.data.totalNumArticles;
        this.articles = res.data.articles;
        this.rendered = true;
        console.log("hello?");
        //this.$router.go();
      })
      .catch(() => {console.log("hello?1");})
      
    },

    goPrev() {
      this.page -= 1;
      console.log(this.page);
      this.getAllArticles();
    },
    goNext() {
      this.page += 1;
      console.log(this.page);
      this.getAllArticles();
    },
  },

  mounted() {
    this.getAllArticles();
  },

}
</script>