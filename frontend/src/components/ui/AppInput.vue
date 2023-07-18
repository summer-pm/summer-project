<template>
  <div id="input">
    <label for="">{{label}}</label>
    <input @input="$emit('update:modelValue',$event.target.value)" :placeholder="placeholder" :type="type">
  </div>
</template>

<script setup>
defineProps({
  modelValue: String,
  label: {
    type: String,
    required: true
  },
  placeholder: {
    type: String,
    required: true
  },
  type: {
    type: String,
    required: true
  }
})

</script>

<style lang="scss" scoped>
#input {
  display: flex;
  flex-direction: column;
}

label {
  font-size: 12px;
}

input {
  border: none;
  outline: none;
  border-bottom: 2px solid var(--color-border);
  background: transparent;
  padding: 11px 0;
  font-size: 14px;
  transition: all .2s ease-in;

  &::placeholder {
    color: var(--color-text);
    opacity: .2;
  }

  &:focus {
    border-bottom: 2px solid var(--color-border-hover);
  }
}
</style>