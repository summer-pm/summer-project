<template>
 <h5><span style="color: var(--color-main)" @click="$emit('login')">Войти</span></h5>
</template>

<script>
export default {
  name: "RegisterSuccessComponent"
}
</script>

<style scoped>
h5{
  cursor: pointer;
}
</style>