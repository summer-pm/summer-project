<template>
 <div class="form-item">
    <app-input v-model="form.email" :label="'Email'" :placeholder="'Введите почту'" :type="'email'"/>
  </div>
  <div class="form-item">
    <app-input v-model="form.password" :label="'Пароль'" :placeholder="'Введите пароль'" :type="'password'"/>
  </div>
  <div class="form-item">
    <app-input v-model="form.name" :label="'Имя'" :placeholder="'Введите имя'" :type="'text'"/>
  </div>
  <div class="form-item">
    <app-button :full-width="true">Зарегистрироваться</app-button>
  </div>
    <h5>или <span @click="$emit('login')" style="color: var(--color-main)">Войти</span></h5>
</template>

<script setup>
import AppInput from "@/components/ui/AppInput.vue";
import AppButton from "@/components/ui/AppButton.vue";
import {reactive} from "vue";

const form = reactive(
    {
      email: '',
      password: '',
      name: ''
    }
)
</script>

<style scoped>
.form-item {
  margin-top: 20px;
}

h5 {
  margin-top: 10px;
  font-size: 14px;
  text-align: center;
}

span {
  cursor: pointer;
}

span:hover {
  opacity: .7;
}
</style>