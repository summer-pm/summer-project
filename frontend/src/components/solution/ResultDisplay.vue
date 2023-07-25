<template>
  <div v-if="attempt.status !== 'NONE'">
    <h5>Результат:</h5>
    <div class="result-wrapper" :class="{pending : isPending, error: isError, failure: isFailure, success: isSuccess}">
      <div id="loader" v-if="isPending">
        <app-loader style="height: 80px"/>
      </div>
      <div v-else-if="isError">
        <p>Ошибка</p>
        <br>
        <span style="white-space: pre-line">{{ attempt.errorMessage }}</span>
      </div>
      <div v-else-if="isFailure">
        <p>Неверный результат</p>
        <br>
        <p>Входные данные:</p>
        <span  :key="i" v-for="(value,i) in attempt.testCase.inputValues ">{{value}} &nbsp;</span>
        <p>Ожидаемый результат</p>
        <span style="white-space: pre-line">{{ attempt.testCase.outputValues }}</span>
        <p>Ваш результат:</p>
        <span style="white-space: pre-line">{{ attempt.actualResult }}</span>

      </div>
      <div v-else-if="isSuccess">
        <p>Решение верно</p>
        <br>
        <p>Время выполнения:</p>
        <span>{{attempt.executionTimeNs/1e6 }}ms</span>
        <p>Объем памяти:</p>
        <span>{{attempt.memoryUsageMb}} MB</span>
      </div>
    </div>
       

  </div>

</template>

<script>
import {computed, defineComponent} from "vue";
import AppLoader from "@/components/ui/AppLoader.vue";


export default defineComponent({
  components: {AppLoader},
  props: {
    attempt: {
      required: true,
      type: Object,
    }
  },
  setup(props) {
    const isPending = computed(() => {
      return props.attempt.status === 'PENDING'
    })
    const isError = computed(() => {
      return props.attempt.status === 'ERROR'
    })
    const isFailure = computed(() => {
      return props.attempt.status === 'FAILURE'
    })
     const isSuccess = computed(() => {
      return props.attempt.status === 'SUCCESS'
    })
    return {
      isPending,
      isError,
      isFailure,
      isSuccess
    }
  }
})
</script>

<style lang="scss" scoped>
#loader{
  height: 80px;
  padding: 10px 0;
  display: flex;
  align-items: center;
  justify-content: center;
}
.result-wrapper {
  width: 35%;
  padding: 10px;
  border-radius: 10px;
  border: 2px solid var(--color-border);
  min-height: 50px;
  transition: all .4s linear;
  margin-top: 20px;
  &.pending {
    background-color: var(--color-background-mute);
  }

  &.error {
    color: var(--color--text-error);
    background-color: var(--color-background-error);
    border-color: var(--color--text-error);
  }
  &.failure{
    background-color: var(--color-background-mute);
  }
  &.success{
     color: var(--color-success);
    background-color: var(--color-success-background);
    border-color: var(--color-success);
  }
}

@media (max-width: 650px) {
.result-wrapper{
  width: 100%;
}
}
</style>
