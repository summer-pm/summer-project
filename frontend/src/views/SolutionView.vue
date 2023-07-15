<template>
  <div v-if="task">
    <h3>{{ task.id }}. {{ task.title }}</h3>
    <div id="solution">
      <task-description v-if="task" :task="task" class="item"/>
      <code-editor :pending=" attempt.status === 'PENDING'" class="item" @sendAttempt="sendAttempt"/>
    </div>
    <div v-if="attempt">
      <result-display :attempt="attempt"/>
    </div>
  </div>

</template>

<script>

import {defineComponent, onBeforeMount, reactive, ref} from 'vue'
import taskApi from "@/api/taskApi";
import TaskDescription from "@/components/task/TaskDescription.vue";
import CodeEditor from "@/components/task/CodeEditor.vue";
import {useRoute} from "vue-router";
import ResultDisplay from "@/components/task/ResultDisplay.vue";

export default defineComponent({
  components: {ResultDisplay, CodeEditor, TaskDescription},
  setup() {
    const route = useRoute()
    const task = ref();
    let attempt = reactive({
      status: 'NONE'
    });
    onBeforeMount(() => {
      taskApi.getById(route.params.id).then(r => {
        task.value = r.data
        console.log(task.value)
      }).catch(err => {
        console.log(err)
      })
    })

    function checkForPending() {
      if (attempt.status === 'PENDING') {
        setTimeout(() => {
          checkAttemptStatus()
        }, 500)
      }
    }

    function sendAttempt(solution) {
      taskApi.sendAttempt(solution).then(r => {
        console.log(r.data);
        Object.assign(attempt, r.data);
        checkForPending();
      }).catch(err => {
        console.log(err)
        if (err.response.status === 400) {
          attempt.status = 'ERROR'
          attempt.errorMessage = err.response.data.message
        }
      })
    }

    function checkAttemptStatus() {
      taskApi.getAttemptById(task.value.id, attempt.id).then(r => {
        Object.assign(attempt, r.data);
        checkForPending();
      }).catch(
          err => console.log(err)
      )
    }

    return {
      attempt,
      sendAttempt,
      task
    }
  }
})
</script>

<style lang="scss" scoped>
#solution {
  display: flex;
  justify-content: space-between;
}

.item {
  width: 60%;

  &:first-child {
    margin-right: 20px;
    width: 40%;
  }
}

</style>