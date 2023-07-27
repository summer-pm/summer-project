<template>
    <div class="about-area">
        <h1>get token mock</h1>
        <input
            type="text"
            v-model="inputTextValue"
            placeholder="Введите токен..."
            @keyup.enter="sendMessage"
            class="input-token"
        />
        <RouterLink to="/">Чат</RouterLink>
    </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';

    export default {
      data() {
            return {
                inputTextValue: '',
                counter: 0,
            }
        },
        computed: {
            ...mapState([
                'token'
            ])
        },
        methods: {
            ...mapActions([
                'SET_TOKEN'
            ]),
            sendMessage() {
                console.log(this.token);
                localStorage.setItem('token', this.inputTextValue);
                this.inputTextValue = '';
            }
        },
    }
</script>

<style scoped>
.about-area {
    margin: 0;
    position: absolute;
    top: 50%;
    left: 50%;
    -ms-transform: translate(-50%, -50%);
    transform: translate(-50%, -50%);
}
</style>