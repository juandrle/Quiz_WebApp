import { createRouter, createWebHistory } from 'vue-router'
import QuizListeView from '@/views/QuizListeView.vue'
import QuizView from '@/views/QuizView.vue'


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', name: 'QuizListeView', component: QuizListeView },
    { path: '/quiz/:quizid', name: 'QuizView', component: QuizView, props: true }
  ]
})

export default router
