<template>
  <header>
    <div id="logo" @click="$router.push({name: 'main'})">
      <app-bear-logo/>
      <h1><span>Beer</span>code</h1>
    </div>
    <nav class="menu">
      <ul>
        <li @click="toFeed"><a>Лента</a></li>
        <li @click="$router.push({name: 'tasks'})"><a>Задачи</a></li>
        <li v-if="!isLoggedIn" @click="showLogin" id="auth"><a>Авторизация</a></li>
        <li v-else @click="logout" ><a>Выйти</a></li>
      </ul>
    </nav>
  </header>
</template>

<script setup>


import AppBearLogo from "@/components/ui/AppLogo.vue";
import {useStore} from "vuex";
import {useRouter} from "vue-router";
import {computed} from "vue";

const store = useStore();
const router = useRouter();
const showLogin = () => store.dispatch('user/showLogin', '');
const isLoggedIn = computed( () => {return store.getters['user/isAuth']});
const logout = () => store.dispatch('user/logout')
const toFeed = () => {
  if(isLoggedIn.value){
    router.push({name : 'feed'})
  } else {
    store.dispatch('user/showLogin', {message:'Войдите для просмотра ленты'})
  }
}
</script>

<style lang="scss" scoped>
header {
  position: sticky;
  z-index: 9999;
  border-bottom: 1px solid var(--color-border);
  box-shadow: 0px -1px 0px 0px var(--color-border) inset;
  height: 70px;
  padding: 15px 75px;
  display: flex;
  justify-content: space-between;
}
 ul {
    display: flex;
   align-items: center;
    list-style-type: none;
    margin-left: 0;
    padding-left: 0;
   & li{
     margin-left: 30px;
     font-size: 18px;
     font-weight: 500;
     cursor: pointer;
     &:hover{
       opacity: .7;
       transform: scale(102%);
     }
   }
  }
#auth{
  border: 1px solid var(--color-text);
  border-radius: 100px;
  padding: 10px 20px;
}

#logo {
  cursor: pointer;
  display: flex;
  height: 100%;

  & h1 {
    margin-left: 20px;
  }

  & span {
    font-weight: 700;
    color: var(--color-bear)
  }
}
</style>