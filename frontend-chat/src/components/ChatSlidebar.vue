<template>
  <div class="chat-sidebar">
    <div class="chat-search-bar-container">
      <ChatSearchBar />
    </div>
    <div class="chat-list-container">
      <ChatList/>
    </div>
  </div>
</template>

<script>
import ChatList from '@/components/ChatList.vue';
import ChatSearchBar from '@/components/ChatSearchBar.vue';

export default {
  components: {
    ChatSearchBar,
    ChatList
  },
  data() {
    return {
      // Ваши данные, если таковые имеются
    }
  },
};
</script>

<style>
.chat-sidebar {
  background-color: #fff;
  box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
  height: 92vh; /* Занимаем всю высоту экрана */
  flex: 1;
  overflow-y: hidden; /* Скрываем вертикальную прокрутку у .chat-sidebar */
  display: flex;
  width: 30vw;
  flex-direction: column;
}

.chat-search-bar-container {
  position: sticky;
  top: 0; /* Чтобы прилипал к верхней границе родительского контейнера */
  background-color: #fff; /* Цвет фона для обеспечения видимости компонента */
  z-index: 1; /* Чтобы компонент ChatSearchBar был поверх компонентов ChatList */
}

.chat-list-container {
  flex: 1;
  overflow-y: auto; /* Добавляем вертикальную прокрутку для контейнера с ChatList */
}

/* Если у вас есть другие стили для .chat-sidebar, добавьте их здесь */

.chat-list {
  padding: 0;
}
</style>
