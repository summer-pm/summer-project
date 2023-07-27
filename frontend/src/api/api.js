import axios from "axios";
import store from "@/store";


// eslint-disable-next-line no-undef
export let backendUrl = import.meta.env.VITE_API_URL;
export const axiosApi = axios.create({
     baseURL: backendUrl,
    headers: {'Content-Type': 'application/json'},

})

axiosApi.interceptors.request.use((conf) => {
    const isAuth = store.getters['user/isAuth'];
    if(isAuth){
        const token = store.getters['user/getToken']
        conf.headers['Authorization'] = `Bearer ${token}`;
    }
    return conf;
})

axiosApi.interceptors.response.use(function (response) {
    // Any status code that lie within the range of 2xx cause this function to trigger
    // Do something with response data
    return response;
  }, function (error) {
    if(error.response.status === 401){
        if(store.getters['user/isAuth']){
            store.dispatch('user/logout')
            store.dispatch('user/showLogin', {message: 'Ваша сессия устарела. Войдите'})
        }
    }
    return Promise.reject(error);
  });