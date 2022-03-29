import { createRouter, createWebHistory } from 'vue-router'
import MainArchitect from '@/views/Questions'
import AdminArchitect from '@/views/AdminPage'
import AllQuestions from '@/views/AllQuestions'
import OneQuestion from '@/views/OneQuestion'
import Users from '@/views/Users'
import UserDetailed from '@/views/UserDetailed'
import UserAdmin from '@/views/UserAdmin'
import Tags from '@/views/Tags'
import QuestionForm from '@/views/QuestionForm'
import About from '@/views/About'
import TagEdit from '@/views/TagEdit'

const routes = [
  {
    path: '/questions',
    name: 'questions',
    component: MainArchitect,
    children: [
      { path: '', component: AllQuestions },
      { path: 'tags', component: AllQuestions },
      { path: ':id', component: OneQuestion },
      { path: 'ask', component: QuestionForm },

    ]
  },

  {
    path: '/users',
    name: 'users',
    component: MainArchitect,
    children: [
      { path: '', component: Users },
      { path: 'admin', component: UserAdmin },
      { path: ':loginid', component: UserDetailed },
    ]
  },

  {
    path: '/tags',
    name: 'tags',
    component: MainArchitect,
    children: [
      { path: '', component: Tags },
      { path: 'edit/:tagName', component: TagEdit, props: true },

    ]
  },

  {
    path: '/home',
    name: 'home',
    component: MainArchitect,
    children: [
      { path: '', component: About },

    ]
  },

  {
    path: '/login',
    component: AdminArchitect,
    children: [
      { path: '', component: () => import('@/views/Login'), name: 'Login' },
      { path: '', component: () => import('@/views/Register'), name: 'Register' },

    ]
  }

// ---------------------- Login ------------------------
/*
{
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login'),
  },

  {
    path: '/login',
    name: 'Register',
    component: () => import('@/views/Register'),
  },

  {
    path: '/login',
    name: 'FindIdPw',
    component: () => import('@/views/FindIdPw'),
  },*/

// ---------------------- Login/end ------------------------


];

const router = createRouter({
    history: createWebHistory(),
    //mode: "history",
    routes,
});

export default router;

