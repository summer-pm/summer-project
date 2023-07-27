<template>
    <div class="app-wrapper" tabindex="0" @keyup.esc="goBack">
      <chat-navbar/>
      <div class="app-container">
        <chat-slidebar/>
        <chat-area/>
      </div>
    </div>
</template>

<script>
import ChatNavbar from '@/components/ChatNavbar.vue';
import ChatSlidebar from '@/components/ChatSlidebar.vue';
import ChatArea from '@/components/ChatArea.vue';
import {mapActions, mapState} from 'vuex';
import {initializeApp} from "@/utils/utils";

export default  {
  components: {
    ChatNavbar, ChatSlidebar, ChatArea
  },
  computed: {
    ...mapState([
        'token',
        'authUser'
    ])
  },
  methods: {
    ...mapActions([
        'ESCAPE_FROM_CHAT',
        'CONNECT_TO_WEBSOCKET',
        'INIT_CHAT'
    ]),

    goBack(event) {
      this.$router.push({ name: 'MainChatView'});
      this.ESCAPE_FROM_CHAT();
    }
  },
  beforeCreate() {
    initializeApp();
  },
  mounted() {
    this.INIT_CHAT();
    this.CONNECT_TO_WEBSOCKET();
  },
  created() {

  }

}
</script>

<style>
.app-container {
  display: flex;
  border: none;
}
</style>
