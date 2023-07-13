import {axiosApi} from "@/api/api";

export default {
    getById(id) {
        return axiosApi.get(
            "/task/" + id
        )
    },
    sendAttempt(data) {
        return axiosApi.post(
            "/task/" + data.taskId + "/attempt",
            data
        )
    },
    getAttemptById(taskId,id){
          return axiosApi.get(
            "/task/" + taskId + "/attempt/" + id,
        )
    }
}