<template>
  <div :class="{odd : isOdd}" class="card">
    <div class="card-item title">{{ task.id }}. {{ task.title }}</div>
    <div class="card-item level">
      <task-level :value="task.level"/>
    </div>
  </div>
</template>

<script setup>
import TaskLevel from "@/components/task/TaskLevel.vue";

defineProps({
  task: {
    type: Object,
    required: true
  },
  isOdd: {
    type: Boolean,
    required: true
  }
})

</script>

<style lang="scss" scoped>
.level{
  display: flex;
  justify-content: end;
  align-items: center;
}
.card {
  font-size: 18px;
  font-weight: 500;
  background-color: var(--color-background-soft);
  padding: 15px 70px 15px 50px;
  width: 100%;
  display: flex;
  align-items: center;

  cursor: pointer;
  opacity: .7;
  border: 1px solid var(--color-border);
  border-left: none;
  border-right: none;
  &:hover{
    opacity: 1;
    border: 1px solid var(--color-border-hover);
  }
  &.odd {
    background-color: var(--color-background-mute);
  }

  &-item {
    width: 50%;
    text-align: center;
  }
}
.title{
  text-align: left;
}

@media (max-width: 600px) {
.card{
   font-size: 14px;
   padding: 15px 20px 15px 20px;
}
}

</style>