import {createRouter, createWebHistory} from 'vue-router'
import SolutionView from "@/views/SolutionView.vue";
import Loader from "@/components/ui/Loader.vue";
import TasksView from "@/views/TasksView.vue";

const router = createRouter({
    history: createWebHistory(import.meta.env.BASE_URL),
    routes: [
        {
            path: '/task/:id',
            name: 'solution',
            component: SolutionView
        },
        {
            path: '/task',
            name: 'tasks',
            component: TasksView
        },
        {
            path: '/',
            name: 'main',
            component: Loader
        }
    ]
})

export default router
