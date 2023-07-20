import axios from "axios";

export const axiosApi = axios.create({
     baseURL: "http://localhost:8082/api/v1",
    headers: {'Content-Type': 'application/json'}
})