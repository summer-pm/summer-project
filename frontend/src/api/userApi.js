import {axiosApi} from "@/api/api";

export default {
    register(username, email,password) {
        return axiosApi.post("/auth/register",{
            email: email,
            username: username,
            password: password
        })
    },
    login(email,password) {
        return axiosApi.post("/auth/login",{
            email: email,
            password: password
        })
    }
}