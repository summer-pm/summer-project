import {axiosApi} from "@/api/api";

export default {

    sendAttempt(data) {
        return axiosApi.post(
            "/tasks/" + data.taskId + "/attempt",
            data
        )
    },
    getAttemptById(taskId, id) {
        return axiosApi.get(
            "/attempts/" + id,
        )
    }
}