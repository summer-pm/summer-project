<template>
  <app-modal v-if="show" @close="$emit('close')">
    <template #header>
      {{ isLogin ? 'Вход' : 'Регистрация'}}
    </template>
    <template #body>
     <login-component v-if="isLogin" @register="isLogin = false"/>
      <register-component v-else @login="isLogin = true"/>
    </template>
  </app-modal>
</template>

<script>

import {defineComponent, ref} from "vue";
import AppModal from "@/components/ui/AppModal.vue";
import LoginComponent from "@/components/auth/LoginComponent.vue";
import RegisterComponent from "@/components/auth/RegisterComponent.vue";

export default defineComponent({
  components: {RegisterComponent, LoginComponent, AppModal},
  props: {
    show: {
      type: Boolean,
      required: false
    }
  },

  setup() {
    const isLogin = ref(true);
    return{
      isLogin
    }
  }
})
</script>

<style scoped>
.form-item{
  margin-top: 20px;
}
</style>