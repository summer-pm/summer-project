<template>
  <div class="form-item">
    <app-input :errors="v$.email.$errors" v-model="v$.email.$model" :label="'Email'" :placeholder="'Введите почту'" :type="'email'"/>
  </div>
  <div class="form-item">
    <app-input :errors="v$.password.$errors" v-model="v$.password.$model" :label="'Пароль'" :placeholder="'Введите пароль'" :type="'password'"/>
  </div>
  <div class="form-item">
    <app-input :errors="v$.name.$errors" v-model="v$.name.$model" :label="'Имя'" :placeholder="'Введите имя'" :type="'text'"/>
  </div>
  <div class="form-item">
    <app-button @click="send" :disabled="v$.$errors.length !== 0" :full-width="true">Зарегистрироваться</app-button>
  </div>
  <h5>или <span  style="color: var(--color-main)" @click="$emit('login')">Войти</span></h5>
</template>

<script setup>
import AppInput from "@/components/ui/AppInput.vue";
import AppButton from "@/components/ui/AppButton.vue";
import {reactive} from "vue";
import {email, helpers, maxLength, minLength, required} from "@vuelidate/validators";
import useVuelidate from "@vuelidate/core";

const form = reactive(
    {
      email: '',
      password: '',
      name: ''
    });
const rules = {

  email: {
    required: helpers.withMessage("Введите Email", required),
    email: helpers.withMessage("Неправильный формат email", email)
  },
  password: {
    minLength: helpers.withMessage("Пароль минимум 8 символов", minLength(8)),
    maxLength: helpers.withMessage("Пароль максимум 30 символов", maxLength(30))
  },
  name: {
    minLength: helpers.withMessage("Имя минимум 2 символа", minLength(2)),
    maxLength: helpers.withMessage("Имя максимум 50 символов", maxLength(50))
  }
}
const v$ = useVuelidate(rules, form);

const send = async () => {
  const result = await v$.value.$validate()
  if (result) {
    console.log("can login");
  }
}
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