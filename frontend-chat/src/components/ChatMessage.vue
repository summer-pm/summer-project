<template>
    <div class="message" :class="!isMyMessage ? 'my' : 'interlocutor'">
      <div class="text-area">
        <span>{{ message.content }}</span>
      </div>
      <div class="info-area">
        <div class="time-area">
          <span>{{ editTimestamp(message.timestamp) }}</span>
        </div>
        <div class="checkboxes-area">
          <span>✓✓</span>
        </div>
      </div>
    </div>
  </template>

  <script>
  import {extractTime} from '@/utils/utils';
  export default {
    data() {
      return {
        isMyMessage: {
          type: Boolean,
          default: true
        }
      };
    },
    methods: {
      editTimestamp(timestamp) {
        return extractTime(timestamp);
      }
    },
    props: {
      message: {
        type: Object,
        required: true,
      },
    },
  };
  </script>
  
  <style >
 .message {
  background-color: #fff;
  color: #000;
  border: 1px solid #ccc;
  padding: 10px;
  border-radius: 10px;
  display: flex;
  align-items: center; /* Изменение display на inline-block */
  position: relative;
  max-width: 70%; /* Максимальная ширина блока, чтобы не занимал всю ширину экрана */
}
 .message.my {
   background-color: teal;
 }
 .message.interlocutor {
   background-color: coral;
 }

.info-area {
  margin-left: auto;
}
  
.info-area {
    margin-left: auto;
}
  
  .text-area {
  margin-right: auto;
  margin: 0;
  position: absolute;
  top: 50%;
  left: 50%;
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
  white-space: nowrap; /* Запрещаем перенос текста на новую строку */
  overflow: hidden; /* Скрываем лишний текст, если он не помещается */
  text-overflow: ellipsis; /* Добавляем многоточие для обозначения обрезанного текста */
}
  
  /* Стили для времени */
  .time-area {
    color: #888; /* Серый цвет для времени */
    font-size: 12px; /* Размер шрифта времени */
  }
  
  /* Стили для чекбоксов (галочек) */
  .checkboxes-area {
    margin-right: auto;
    color: #ffcc00; /* Желтый цвет для галочек */
    font-size: 14px; /* Размер шрифта галочек */
    padding-left: 8px; /* Отступ слева для галочек */
  }
  </style>
  