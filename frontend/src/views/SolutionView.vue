<template>
  <div v-if="!task" style="display: flex; width: 100%; justify-content: center; align-items: center; margin-top: 50px;">
    <app-loader></app-loader>

  </div>
  <div v-else>
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
import TaskDescription from "@/components/solution/TaskDescription.vue";
import CodeEditor from "@/components/solution/CodeEditor.vue";
import {useRoute} from "vue-router";
import ResultDisplay from "@/components/solution/ResultDisplay.vue";
import attemptApi from "@/api/attemptApi";
import AppLoader from "@/components/ui/AppLoader.vue";
import {useStore} from "vuex";

export default defineComponent({
  components: {AppLoader, ResultDisplay, CodeEditor, TaskDescription},
  setup() {
    const store = useStore();
    const route = useRoute()
    const task = ref();
    let attempt = reactive({
      status: 'NONE'
    });
    onBeforeMount(() => {
      taskApi.getById(route.params.id).then(r => {
        setTimeout(() => {
          task.value = r.data
          console.log(task.value)
        }, 1000)

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
      if (store.getters['user/isAuth']) {
        attemptApi.sendAttempt(solution).then(r => {
          Object.assign(attempt, r.data);
          checkForPending();
        }).catch(err => {
          console.log(err)
          if (err.response.status === 400) {
            attempt.status = 'ERROR'
            attempt.errorMessage = err.response.data.message
          }
        })
      } else {
        store.dispatch('user/showLogin', {message:'Войдите для отправки решения'})
      }


    }

    function checkAttemptStatus() {
      attemptApi.getAttemptById(task.value.id, attempt.id).then(r => {
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