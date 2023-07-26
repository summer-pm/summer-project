<template>
      <div class="chat-list">
        <chat-list-item
          v-for="chat in chats"
          :key="chat.roomId"
          :chat="chat"
          @updateActiveChat="updateActiveChat"/>
      </div>
</template>
  
<script>
import ChatListItem from '@/components/ChatListItem.vue';
import {mapActions, mapState} from 'vuex';


  export default {
    components: {ChatListItem},
    data() {
      return {}
    },
    computed: {
      ...mapState([
        'chats',
        'activeChatId',
          'token',
          'authUser'
      ])
    },
    methods: {
      ...mapActions([
      'FETCH_CHATS',
      'SET_ACTIVE_USER',
          'USER_IS_EXIST_IN_CHAT'
    ]),

    updateActiveChat(chat) {
      this.$router.push({
        path: 'chat',
        query: {'roomId': chat.roomId},
      })
    },
  },
  mounted() {
      // setTimeout(() => {
      //   this.FETCH_CHATS(this.token, this.authUser);
      // }, 100);
    }
}
  
  </script>
  
  <style>
  .chat-list {
    flex: 1;
    overflow-y: auto;
  }
  </style>
  