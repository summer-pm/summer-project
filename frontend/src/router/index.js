import {createRouter, createWebHistory} from 'vue-router'
import SolutionView from "@/views/SolutionView.vue";
import TasksView from "@/views/TasksView.vue";
import MainView from "@/views/MainView.vue";

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
            component: MainView
        }
    ]
})

export default router
