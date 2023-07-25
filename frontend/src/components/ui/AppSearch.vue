<template>
  <div id="search">
    <svg @click="$emit('update:modelValue', input)" fill="none"  viewBox="0 0 43 43"  xmlns="http://www.w3.org/2000/svg">
      <path
          d="M16.875 30.5241C24.538 30.5241 30.75 24.3626 30.75 16.762C30.75 9.16147 24.538 3 16.875 3C9.21205 3 3 9.16147 3 16.762C3 24.3626 9.21205 30.5241 16.875 30.5241Z"
          stroke="#979797" stroke-linecap="round" stroke-width="6"/>
      <path d="M27.666 27.4651L40.0001 39.6987" stroke="#979797" stroke-linecap="round" stroke-width="6"/>
    </svg>
    <input v-model="input" placeholder="Поиск" type="text">
  </div>
</template>

<script setup>
import {ref} from "vue";
const props = defineProps({
  startValue : {
    type : String,
    required : false,
    default : ''
  }
})
const input = ref(props.startValue);
</script>

<style lang="scss" scoped>
#search{
  display: flex;
  border-radius: 100px;

  height: 50px;
  padding: 10px 20px;
  background-color: var(--color-background-mute);
  & svg {
    max-height: 100%;
    cursor: pointer;
    margin-right: 10px;
    &:hover {
      transform: scale(104%);
    }
  }
  & input {
    outline: none;
    width: 200px;
    background-color: transparent;
    border: none;
    color: var(--color-text);
    &::placeholder{
      color: var(--color-text);
      opacity: .4;
    }
  }
}
</style>