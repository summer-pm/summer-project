<template>
  <app-modal v-if="show" @close="$emit('close')">
    <template #header>
      Вход
    </template>
    <template #body>
      <div class="form-item">
        <app-input v-model="email" :label="'Email'" :placeholder="'Введите почту'" :type="'email'"/>
      </div>
      <div class="form-item">
        <app-input v-model="email" :label="'Пароль'" :placeholder="'Введите пароль'" :type="'password'"/>
      </div>
      <div class="form-item">
        <app-button :full-width="true">Войти</app-button>
      </div>
    </template>
  </app-modal>
</template>

<script>

import {defineComponent, ref} from "vue";
import AppModal from "@/components/ui/AppModal.vue";
import AppInput from "@/components/ui/AppInput.vue";
import AppButton from "@/components/ui/AppButton.vue";

export default defineComponent({
  components: {AppButton, AppInput, AppModal},
  props: {
    show: {
      type: Boolean,
      required: false
    }
  },

  setup() {
    const email = ref('');
    return {email}
  }
})
</script>

<style scoped>
.form-item{
  margin-top: 20px;
}
</style>