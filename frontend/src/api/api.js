import axios from "axios";

export const axiosApi = axios.create({
     baseURL: "http://localhost:60000",
    headers: {'Content-Type': 'application/json'}
})