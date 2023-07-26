<template>
  <div id="level" :class="resultClass">
    {{ value }}
  </div>
</template>

<script setup>
import {computed} from "vue";

const props = defineProps({
  value: {
    type: String,
    required: true
  }
});

const resultClass = computed(() => {
  let className = '';

  switch (props.value) {
    case 'EASY':
      className = 'easy';
      break;
    case 'MEDIUM':
      className = 'medium';
      break;
    case 'HARD':
      className = 'hard';
      break;
    default:
      break;
  }
  return className;
});
</script>

<style scoped>
#level {
  width: 130px;
  border: 1px solid;
  padding: 10px 25px;
  font-size: 16px;
  font-weight: 500;
  border-radius: 100px;
  text-align: center;
}
.easy{
  color: var(--color-easy);
  border-color: var(--color-easy);
}
.medium{
  color: var(--color-medium);
  border-color: var(--color-medium);
}
.hard{
  color: var(--color-hard);
  border-color: var(--color-hard);
}

@media (max-width: 600px) {
  #level {
    width: 100px;
    padding: 5px 20px;
    font-size: 14px;
  }
}
</style>