<template>
  <app-loader v-show="isLoading"/>
  <div v-show="!isLoading">
    <div class="form-item">
      <app-input v-model="v$.email.$model" :errors="v$.email.$errors" :label="'Email'" :placeholder="'Введите почту'"
                 :type="'email'"/>
    </div>
    <div class="form-item">
      <app-input v-model="v$.password.$model" :errors="v$.password.$errors" :label="'Пароль'"
                 :placeholder="'Введите пароль'" :type="'password'"/>
    </div>
    <div class="form-item">
      <app-input v-model="v$.name.$model" :errors="v$.name.$errors" :label="'Имя'" :placeholder="'Введите имя'"
                 :type="'text'"/>
    </div>
    <p v-if="isUserExists" style="text-align: center; color: var(--color--text-error)">Такой пользователь уже есть</p>
    <div class="form-item">
      <app-button :disabled="v$.$errors.length !== 0" :full-width="true" @click="send">Зарегистрироваться</app-button>
    </div>
    <h5>или <span style="color: var(--color-main)" @click="$emit('login')">Войти</span></h5>
  </div>

</template>

<script setup>
import AppInput from "@/components/ui/AppInput.vue";
import AppButton from "@/components/ui/AppButton.vue";
import {computed, reactive} from "vue";
import {email, helpers, maxLength, minLength, required} from "@vuelidate/validators";
import useVuelidate from "@vuelidate/core";
import AppLoader from "@/components/ui/AppLoader.vue";
import {useStore} from "vuex";

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
const store = useStore();

const isLoading = computed(() => store.getters['user/isLoading'])
const isUserExists = computed(() => store.getters['user/isRegisterError'])
const send = async () => {
  const result = await v$.value.$validate()
  if (result) {
    await store.dispatch('user/register', {
      email: form.email,
      name: form.name,
      password: form.password
    })
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