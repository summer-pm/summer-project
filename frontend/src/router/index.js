import { createRouter, createWebHistory } from 'vue-router'
import SolutionView from "@/views/SolutionView.vue";
import Loader from "@/components/ui/Loader.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/task/:id',
      name: 'home',
      component: SolutionView
    },
    {
      path: '/',
      name: 'main',
      component: Loader
    }
  ]
})

export default router
