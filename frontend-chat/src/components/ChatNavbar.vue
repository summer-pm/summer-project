<template>
    <div class="chat-header">
      <div class="logo">
        <router-link to="/about" class="nav-item">BeerChat</router-link>
      </div>
      <div class="header-right">
        <div class="navigation">
            <router-link to="/task" class="nav-item">Задачи</router-link>
            <router-link to="/messages" class="nav-item">Сообщения</router-link>
        </div>
        <div class="profile">
          <img :src="avatarSrc" :alt="userName" class="user-avatar" />
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import { getRandomIndex } from '@/utils/utils';
  
  export default {
    data() {
      return {
        avatarIndex: getRandomIndex(),
      }
    },
    props: {
      userName: {
        type: String,
        required: false,
      },
      userAvatar: {
        type: String,
        required: false,
      },
    },
    computed: {
      avatarSrc() {
        return require(`@/assets/images/avatar_storage/avatar${this.avatarIndex}.jpg`);
      },
    }
  };
  </script>
  
  <style>
  * {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    }
  .chat-header {
    height: 8vh;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 16px;
    /* background-color: #f2f2f2; */
    font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
    border-bottom: 2px solid #828282; 
  }
  
  .logo {
    font-size: 18px;
    font-weight: bold;
    margin-left: 16px;
  }
  
  .navigation {
    display: flex;
  }
  
  .nav-item {
    margin-right: 20px;
    text-decoration: none;
    color: #555;
  }
  
  .nav-item:hover {
    color: #000;
  }
  
  .profile {
    display: flex;
    align-items: center;
    margin-left: 20px;
  }
  
  .user-avatar {
    width: 40px;
    height: 40px;
    border-radius: 50%;
  }

.header-right {
  display: flex;
  align-items: center;
  margin-right: 10px;
}
  </style>
  