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
        'activeChatId'
      ])
    },
    methods: {
      ...mapActions([
      'FETCH_CHATS',
      'SET_ACTIVE_USER'
    ]),

    updateActiveChat(chat) {
      this.$router.push({
        path: 'chat',
        query: {'roomId': chat.roomId},
      })
    },
  },
  mounted() {
      this.FETCH_CHATS();
    }
}
  
  </script>
  
  <style>
  .chat-list {
    flex: 1;
    overflow-y: auto;
  }
  </style>
  