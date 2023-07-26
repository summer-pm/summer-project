<template>
  <div id="task-info">
    <div class="task-level">
       <task-level :value="task.level"/>
    </div>
    <p class="task-descr">
      {{task.description}}
    </p>

    <div v-for="(example,n) in task.examples" :key="n" class="task-example">
      <h5>Пример {{ n + 1 }}</h5>
      <div class="task-example-wrapper">
        <span>Входные параметры:</span> <code >{{ example.input }}&nbsp;</code>
        <br>
        <span>Выходные параметры:</span>  <code>{{ example.output }}</code>
        <br>
        <span>Описание:</span>  <code>{{ example.explanation }}</code>
      </div>
    </div>
    <div class="task-limits"><h5>Ограничения</h5>
      <ul>
        <li><h5>время выполнения {{ task.timeLimit }} ms</h5></li>
        <li><h5>объем памяти {{ task.volumeLimit }} MB</h5></li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import TaskLevel from "@/components/task/TaskLevel.vue";

defineProps(['task'])
</script>

<style lang="scss" scoped>
#task-info {

}
.task-level{
  margin: 20px 0;
}
.task-limits{
  margin-top: 20px;
}
.task-example {
  margin-top: 30px;

  &-wrapper {
    background-color: var(--color-background-soft);
    padding: 10px 30px;
    border-radius: 10px;
    font-size: 14px;
    & span{
      font-weight: 600;
    }
  }
}


code {
  font-size: 16px;
}

@media (max-width: 650px) {
code {
  font-size: 14px;
}
  h5 {
    font-size: 14px;
  }
}
</style>