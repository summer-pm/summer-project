import {createRouter, createWebHistory} from 'vue-router';
import ChatPage from '@/components/ChatPage.vue';
import Main from '@/components/temporary/Main.vue';
import About from '@/components/temporary/About.vue';
import ChatAreaMessages from '@/components/ChatAreaMessages.vue';

const routes = [
    {
        path: '/',
        component: ChatPage,
        name: 'MainChatView'
        
    },
    {
        path: '/room',
        component: ChatAreaMessages,
        name: 'ActiveChatRoom'
    },
    // Mock routs
    {
        path: '/main',
        component: Main,
    },
    {
        path: '/mock-login',
        component: About,
        name: 'About',
    },
    {
        path: '/feed',
        component: About
    },
    {
        path: '/tasks',
        component: About
    },
    {
        path: '/messages',
        component: About
    },
]

const router = createRouter({
    routes,
    history: createWebHistory(process.env.BASE_URL)
})

export default router;