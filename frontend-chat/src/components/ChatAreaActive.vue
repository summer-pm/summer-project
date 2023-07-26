<template>
    <div class="active-chat-area">
      <section class="chat-area-header">
        <chat-area-header />
      </section>

      <section class="chat-area-messages">
        <chat-area-messages />
      </section>
  
      <section class="chat-area-input">
        <chat-area-input />
      </section>
    </div>
  </template>
  
  <script>
  import ChatMessage from '@/components/ChatMessage.vue';
  import ChatAreaHeader from '@/components/ChatAreaHeader.vue';
  import ChatAreaMessages from '@/components/ChatAreaMessages.vue';
  import ChatAreaInput from '@/components/ChatAreaInput.vue';
  
  export default {
    components: {
      ChatAreaHeader,
      ChatAreaMessages,
      ChatAreaInput,
    },
    methods: {},
  };
  </script>
  
  <style scoped>
/* Стили для общего контейнера .active-chat-area */
.active-chat-area {
  display: flex;
  flex-direction: column;
  height: 100%;
}

/* Стили для каждой секции (компонента) внутри .active-chat-area */
.chat-area-header,
.chat-area-messages,
.chat-area-input {
  flex: 1; /* Распределение места внутри .active-chat-area, flex-grow: 1 */
}

/* Стили для chat-area-header */
.chat-area-header {
  flex-grow: 1; /* Занимает 10% места в верхней части */
}

/* Стили для chat-area-messages */
.chat-area-messages {
  flex-grow: 8; /* Занимает 80% места в центре */
}

/* Стили для chat-area-input */
.chat-area-input {
  flex-grow: 1; /* Занимает 10% места в нижней части */
}
</style>
  