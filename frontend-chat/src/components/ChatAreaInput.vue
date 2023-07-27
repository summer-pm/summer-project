<template>
    <div class="input-area">
      <div class="input-wrapper">
        <input
          type="text"
          v-model="inputTextValue"
          placeholder="Введите сообщение..."
          @keyup.enter="sendMessage"
          class="input-box"
        />
        <button
          @click="sendMessage"
          :class="{ 'send-btn': true, 'blue-background': inputTextValue, 'white-plane': !inputTextValue }"
        >
          <i class="fas fa-paper-plane"></i>
        </button>
      </div>
    </div>
  </template>
  
<script>
import { mapActions, mapState } from 'vuex';

export default {
    data() {
      return {
        inputTextValue: "",
      };
    },
    computed: {
      ...mapState([
        'activeChatId',
        'currentUserId',
        'stompClient'
      ]),
        
    },
    methods: {
        ...mapActions([
        ]),
        sendMessage() {
            let trimedInput = this.inputTextValue.trim();
            if (trimedInput !== '') {
                const message = {
                chatRoomId: this.activeChatId,
                senderId: this.currentUserId,
                content: this.inputTextValue
                };
                const path = '/app/changeMessage/' + this.activeChatId;
                this.stompClient.send(path, {}, JSON.stringify(message));
                this.inputTextValue = '';
            }
            
        },
    },
}

  </script>
  
  <style scoped>
  .input-area {
    display: flex;
    align-items: center;
    padding: 16px;
    border: 0;
    margin-top: auto;
  }
  
  .input-wrapper {
    position: relative;
    flex: 1;
  }
  
  .input-box {
    width: 100%;
    height: 40px; /* Увеличиваем высоту input */
    padding: 8px 40px 8px 8px;
    border: 1px solid #ccc;
    border-radius: 4px;
    outline: none; /* Убираем выделение при наведении курсора */
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Добавляем тень от контура input */
  }
  
  .send-btn {
    position: absolute;
    top: 50%;
    right: 10px;
    transform: translateY(-50%);
    padding: 8px;
    margin: 4px; /* Добавляем промежутки до границ кнопки */
    color: #fff;
    border: none;
    border-radius: 4px;
    cursor: pointer;
  }
  
  .send-btn.blue-background {
    background-color: #007bff;
  }
  
  .send-btn.white-plane {
    background-color: transparent;
    color: #007bff; /* Синий цвет самолетика, если нет текста в input */
  }
  
  .send-btn:hover {
    background-color: #0056b3;
    color: white; /* Белый цвет самолетика при наведении, если есть текст в input */
  }
  
  .send-btn.white-plane:hover {
    background-color: #007bff;
    color: white; /* Белый цвет самолетика при наведении, если нет текста в input */
  }
  
  /* Стили для иконки самолетика (Font Awesome) */
  .send-btn i {
    font-size: 16px;
    transform: rotate(45deg); /* Поворот иконки на -45 градусов (сделает из нее самолетик) */
  }
  </style>
  