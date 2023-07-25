import {axiosApi} from "@/api/api";

export default {
    getById(id) {
        return axiosApi.get(
            "/tasks/" + id
        )
    },
    getAll(offset, title, level) {
        const queryParams = {};
        if (offset)
            queryParams.page = offset;
        if (title)
            queryParams.title = title;
        if (level)
            queryParams.level = level;

        return axiosApi.get("/tasks", {
            params: queryParams
        })


    }
}