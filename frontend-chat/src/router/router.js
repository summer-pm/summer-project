import {createRouter, createWebHistory} from 'vue-router';
import ChatPage from '@/components/ChatPage.vue';
import Main from '@/components/temporary/Main.vue';
import About from '@/components/temporary/About.vue';
import ChatAreaMessages from '@/components/ChatAreaMessages.vue';

const routes = [
    {
        path: '/chat',
        component: ChatPage,
        
    },
    {
        path: '/chat/room',
        component: ChatAreaMessages,
    },

]

const router = createRouter({
    routes,
    history: createWebHistory(process.env.BASE_URL)
})

export default router;