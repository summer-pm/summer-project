<template>
  <div
    class="chat-item"
    @click="selectChat(chat)"
    :class="{ active: isActiveChat }"
  >
    <img :src="avatarSrc" :alt="chat.name" class="chat-avatar" />
    <div class="chat-info">
      <div class="left-info-item">
        <div class="chat-name">{{ chat.interlocutor }}</div>
        <div class="last-message">{{ chat.lastMessage ? formatContentView(chat.lastMessage.content) : '' }}</div>
      </div>
      <div class="right-info-item">
        <div class="time">{{ chat.lastMessage ? formattedTime : '' }}</div>
        <div class="unreaded-message-count">
          <span class="circle-counter">{{ unreadMessages }}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex';
import { getRandomIndex } from '@/utils/utils';

export default {
  props: {
    chat: {
      type: Object,
      required: true,
    },
  },
  computed: {
    ...mapState(['activeChatId']),
    isActiveChat() {
      return this.activeChatId === this.chat.roomId;
    },
    avatarSrc() {
      return require(`@/assets/images/avatar_storage/avatar${this.avatarIndex}.jpg`);
    },
    formattedTime() {
      return this.chat.lastMessage.timestamp.substring(11, 16);
    },
    unreadMessages() {
      return 7; // Здесь можно подставить логику подсчета непрочитанных сообщений
    },
  },
  data() {
    return {
      avatarIndex: getRandomIndex(),
    };
  },
  methods: {
    ...mapActions(['UPDATE_ACTIVE_CHAT', 
                   'SET_ACTIVE_USER']),
    selectChat(chat) {
      this.UPDATE_ACTIVE_CHAT(chat);
      this.$emit('updateActiveChat', chat);
    },
    formatContentView(content) {
      return content ? (content.length > 35 ? content.substring(0, 35) : content) : '';
    },
  },
};
</script>

<style scoped>
.chat-item {
  display: flex;
  align-items: center;
  padding: 10px;
  padding-left: 20px;
  cursor: pointer;
  transition: background-color 0.2s ease;
}

.chat-item:hover {
  background-color: #e0ded4;
}

.active,
.active:hover {
  background-color: #fcdd2d;
}

.chat-avatar {
  width: 50px;
  height: 50px;
  border-radius: 50%;
  margin-right: 10px;
}

.chat-info {
  flex: 1;
  display: inline-flex;
}

.chat-name {
  font-weight: bold;
  font-size: 18px;
  margin-bottom: 4px;
  color: #333;
}

.last-message {
  font-size: 14px;
  color: #666;
  margin-bottom: 8px;
}
.unreaded-message-count {
  font-size: 12px;
  text-align: center;
}

.circle-counter {
  height: 18px;
  width: 18px;
  background-color: #bbb;
  border-radius: 50%;
  display: inline-block;
  text-align: center;
  margin-top: 4px;
  padding-top: 1px;
}
.time {
  font-size: 14px;
  color: #888;
}

.right-info-item {
  margin-left: auto;
}
</style>
