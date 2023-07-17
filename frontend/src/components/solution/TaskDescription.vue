<template>
  <div id="task-descr">

    <div v-for="(example,n) in task.taskTestCases" :key="n" class="task-example">
      <h5>Пример {{ n + 1 }}</h5>
      <div class="task-example-wrapper">
        <code>Входные параметры:</code> <code :key="i" v-for="(value,i) in example.inputValues ">{{ value }} &nbsp;</code>
        <br>
        <code>Выходные параметры:</code>  <code>{{ example.outputValues }}</code>
      </div>
    </div>
    <div class="task-limits"><h5>Ограничения</h5>
      <ul>
        <li><h5>время выполнения {{ task.timeLimitMs }} ms</h5></li>
        <li><h5>объем памяти {{ task.volumeLimitMb }} MB</h5></li>
      </ul>
    </div>
  </div>
</template>

<script setup>
defineProps(['task'])
</script>

<style lang="scss" scoped>
#task-descr {

}

.task-example {
  margin-top: 30px;

  &-wrapper {
    background-color: var(--color-background-soft);
    padding: 10px 30px;
    border-radius: 10px;
  }
}


code {
  font-size: 16px;
}
</style>