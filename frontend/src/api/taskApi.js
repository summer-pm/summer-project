import {axiosApi} from "@/api/api";

export default {
    getById(id) {
        return axiosApi.get(
            "/task/" + id
        )
    },
    getAll(offset, sort, title, status, level) {
        const queryParams = {};
        if (offset)
            queryParams.limit = offset;
        if (sort)
            queryParams.param2 = sort;
        if (title)
            queryParams.param2 = title;
        if (status)
            queryParams.param2 = status;
        if (level)
            queryParams.param2 = level;
        return new Promise((resolve) => {
            resolve({
                content: [
                    {
                        id: 1,
                        title: "Addition",
                        level: "EASY",
                        status: 0
                    },
                    {
                        id: 2,
                        title: "Addition 2",
                        level: "MEDIUM",
                        status: 1
                    },
                    {
                        id: 3,
                        title: "Addition 3",
                        level: "HARD",
                        status: 0
                    }
                ],
                totalPages: 4,
                number: offset,
                size: 10,
                sort: null
            });
        });

    }
}