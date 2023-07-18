<template>
<login-modal :show="showModal" @close="showModal = false"/>
  <button @click="showModal = true">Ff</button>
</template>

<script setup>
import {ref} from "vue";
import LoginModal from "@/components/auth/LoginModal.vue";
const showModal = ref(false)
</script>

<style scoped>

</style>