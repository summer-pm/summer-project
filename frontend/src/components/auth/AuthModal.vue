<template>

  <app-modal :error-message="errorMessage" v-if="show" @close="$emit('close')">
    <template #header>
      {{ isLogin ? 'Вход' : isRegisterSuccess ? 'Пользователь успешно зарегистрирован!' : 'Регистрация'}}
    </template>
    <template #body>
     <login-component v-if="isLogin" @register="isLogin = false"/>
      <register-component v-else-if="!isLogin && !isRegisterSuccess" @login="isLogin = true"/>
      <register-success-component v-else-if="isRegisterSuccess" @login="isLogin = true"/>
    </template>
  </app-modal>
</template>

<script>

import {computed, defineComponent, ref} from "vue";
import AppModal from "@/components/ui/AppModal.vue";
import LoginComponent from "@/components/auth/LoginComponent.vue";
import RegisterComponent from "@/components/auth/RegisterComponent.vue";
import {useStore} from "vuex";
import RegisterSuccessComponent from "@/components/auth/RegisterSuccessComponent.vue";

export default defineComponent({
  components: {RegisterSuccessComponent, RegisterComponent, LoginComponent, AppModal},
  props: {
    show: {
      type: Boolean,
      required: false
    }
  },

  setup() {
    const isLogin = ref(true);
    const store = useStore();
    const isRegisterSuccess = computed(() => store.getters['user/isSuccessRegister'])
    const errorMessage = computed(() => store.getters['user/errorMessage'])
    return{
      isLogin,
      isRegisterSuccess,
      errorMessage
    }
  }
})
</script>

<style scoped>
.form-item{
  margin-top: 20px;
}
</style>