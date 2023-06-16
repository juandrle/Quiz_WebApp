import { createRouter, createWebHistory } from 'vue-router'
import QuizListeView from '@/views/QuizListeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { path: '/', name: 'QuizListeView', component: QuizListeView }
  ]
})

export default router
