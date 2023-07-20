<template>
<auth-modal :show="showModal" @close="showModal = false"/>
  <button @click="showModal = true">Ff</button>
</template>

<script setup>
import {ref} from "vue";
import AuthModal from "@/components/auth/AuthModal.vue";
const showModal = ref(false)
</script>

<style scoped>

</style>