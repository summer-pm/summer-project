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

export default  {
  components: {
    ChatNavbar, ChatSlidebar, ChatArea
  },

  methods: {
    ...mapActions([
      'ESCAPE_FROM_CHAT',
      'CONNECT_TO_WEBSOCKET'
    ]),

    goBack(event) {
      this.$router.push('chat');
      this.ESCAPE_FROM_CHAT();
    }
  },
  mounted() {
    this.CONNECT_TO_WEBSOCKET();
    console.log(this.$route.meta.loggedInUser);
  }

}
</script>

<style>
.app-container {
  display: flex;
  border: none;
}
</style>
