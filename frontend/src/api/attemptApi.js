import {axiosApi} from "@/api/api";

export default {

    sendAttempt(data) {
        return axiosApi.post(
            "/task/" + data.taskId + "/attempt",
            data
        )
    },
    getAttemptById(taskId, id) {
        return axiosApi.get(
            "/task/" + taskId + "/attempt/" + id,
        )
    }
}