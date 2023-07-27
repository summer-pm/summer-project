<template>
  <div class="chat-search-bar">
    <div class="chat-title-and-actions">
      <div class="chat-title">Чаты</div>
      <div class="chat-actions">
        <button class="notification-button" >
          <i class="fas fa-bell"></i>
          <i class="fas fa-bell-slash"></i>
        </button>
        <div class="add-chat" @click="addNewChat">
<!--          <button class="add-chat-btn" @click="addNewChat">Add</button>-->
        </div>
      </div>
    </div>
    <div class="chat-search">
      <input
        type="text"
        v-model="searchQuery"
        placeholder="Чаты, сообщения и другое"
        class="search-input"
        @keyup.enter="addNewChat"
      />
    </div>
  </div>
</template>

<script>
import {mapActions} from "vuex";

export default {

  data() {
    return {
      searchQuery: "",
    };
  },
  methods: {
    ...mapActions([
        'ADD_NEW_CHAT'
    ]),
    addNewChat() {
      this.ADD_NEW_CHAT(this.searchQuery);
      this.searchQuery = '';
    },
  },
};
</script>

<style>
.add-chat {
  position: absolute;
  width: 35px;
  height: 35px;
  background: #fff;
  cursor: pointer;

  /* Mittig */
  /*top: 50%;*/
  /*left: 50%;*/
}

.add-chat:after {
  content: '';
  position: absolute;
  transform: translate(-50%, -50%);
  height: 4px;
  width: 50%;
  background: #fcdd2d;
  top: 50%;
  left: 50%;
}

.add-chat:before {
  content: '';
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background: transparent;
  height: 50%;
  width: 4px;
}

.add-chat:hover:before,
.add-chat:hover:after {
  background: #fcdd2d;
  transition: 0.2s;
}

.add-chat:hover {
  background-color: #095776;
  transition: 0.2s;
}
/* Общие стили для компонента chat-search-bar */
.chat-search-bar {
  padding: 16px;
  display: flex;
  flex-direction: column;
}

.chat-title-and-actions {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.chat-title {
  font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
  font-size: 18px;
  font-weight: bold;
}

.chat-actions {
  display: flex;
  align-items: center;
}

/* Стили для кнопки уведомлений */
.notification-button {
  background-color: transparent;
  border: none;
  font-size: 24px;
  cursor: pointer;
  color: #999; /* Измените цвет на ваше усмотрение */
  outline: none;
  padding: 0;
  transition: color 0.3s ease;
}

.notification-button:hover {
  color: #999; /* Измените цвет при наведении на ваше усмотрение */
}

.notification-button .fa-bell {
  color: #999; /* Измените цвет включенного состояния уведомлений */
}

.notification-button .fa-bell-slash {
  display: none;
}

.notification-button[disabled] {
  cursor: not-allowed;
}

.notification-button[disabled] .fa-bell {
  display: none;
}

.notification-button[disabled] .fa-bell-slash {
  display: inline;
}

/* Стили для поля поиска */
.chat-search {
  margin-top: 10px;
}

.search-input {
  padding: 10px;
  padding-left: 14px;
  background-color: #C9CCD13D;
  border: 0;
  border-radius: 16px;
  width: 100%;
}

/* Дополнительные стили */
</style>
