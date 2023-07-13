import { createRouter, createWebHistory } from 'vue-router'
import SolutionView from "@/views/SolutionView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/task/:id',
      name: 'home',
      component: SolutionView
    }
  ]
})

export default router
