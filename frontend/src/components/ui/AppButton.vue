<template>
  <button :disabled="disabled" :class="{'full-width' : fullWidth}">
    <slot></slot>
  </button>
</template>

<script setup>
defineProps({
  fullWidth: {
    type: Boolean,
    default: false
  },
  disabled: {
    type: Boolean,
    default: false
  }
})
</script>

<style scoped>
button {
  padding: 15px 58px;
  border-radius: 100px;
  border: none;
  background-color: var(--color-main);
  color: var(--color-text);
  cursor: pointer;
  font-size: 18px;
  font-weight: 500;
}
button[disabled]{
  background-color: var(--color-border);
  color: var(--color-border-hover);
  cursor: default;
}
button:hover{
  opacity: .85;
}
.full-width {
  width: 100%;
}
</style>