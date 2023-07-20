import axios from "axios";
import store from "@/store";

export const axiosApi = axios.create({
     baseURL: "http://localhost:8082/api/v1",
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