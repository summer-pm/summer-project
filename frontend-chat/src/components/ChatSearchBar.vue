<template>
  <div class="chat-search-bar">
    <div class="chat-title-and-actions">
      <div class="chat-title">Чаты</div>
      <div class="chat-actions">
        <button class="notification-button" @click="handleNotificationsClick">
          <i class="fas fa-bell" v-if="notificationsEnabled"></i>
          <i class="fas fa-bell-slash" v-else></i>
        </button>
        <div class="add-chat" @click="handleAddChatClick">
          <i class="fas fa-plus">P</i>
        </div>
      </div>
    </div>
    <div class="chat-search">
      <input
        type="text"
        v-model="searchQuery"
        placeholder="Чаты, сообщения и другое"
        class="search-input"
        @input="performSearch"
        @keyup.enter="performSearch"
      />
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      searchQuery: "",
      notificationsEnabled: true,
    };
  },
  methods: {
    performSearch() {
      // Метод для выполнения поиска чата по введенному запросу (searchQuery)
      // Можете реализовать поиск чата или использовать его для фильтрации уже существующих чатов
      this.search();
    },
    handleNotificationsClick() {
      this.toggleNotifications();
    },
    handleAddChatClick() {
      // Обработчик клика на иконку добавления чата (например, открыть модальное окно для создания нового чата)
    },
    search() {
      // Метод для выполнения поиска чата по введенному запросу (searchQuery)
      // Можете реализовать поиск чата или использовать его для фильтрации уже существующих чатов
    },
    toggleNotifications() {
      this.notificationsEnabled = !this.notificationsEnabled;
    },
  },
};
</script>

<style>
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
  color: #ff0000; /* Измените цвет при наведении на ваше усмотрение */
}

.notification-button .fa-bell {
  color: #ff0000; /* Измените цвет включенного состояния уведомлений */
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
