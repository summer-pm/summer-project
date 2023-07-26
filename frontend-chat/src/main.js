import App from './App'
import {createApp} from 'vue'
import store from '@/store';
import router from '@/router/router';
import '@fortawesome/fontawesome-free/css/all.min.css';

const app = createApp(App);

app
    .use(store)
    .use(router)
    .mount('#app')
