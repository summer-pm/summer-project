<template>
  <header :class="{transparent : isMainPage}">
    <div id="logo" @click="$router.push({name: 'main'})">
      <app-bear-logo/>
      <h1><span>Beer</span>code</h1>
    </div>
    <nav id="menu">
      <ul>
        <li @click="toFeed"><a>Лента</a></li>
        <li @click="$router.push({name: 'tasks'})"><a>Задачи</a></li>
        <li v-if="!isLoggedIn" id="auth" @click="showLogin"><a>Авторизация</a></li>
        <li v-else @click="logout"><a>Выйти</a></li>
      </ul>
    </nav>

    <div id="burger-button" class="opened" @click="isMenuOpened = !isMenuOpened">
     {{isMenuOpened ?  '✕' : '☰'}}
    </div>

  </header>
  <menu id="burger" :class="{active : isMenuOpened}">
    <ul>
      <li @click="toFeed"><a>Лента</a></li>
      <li @click="isMenuOpened = false;$router.push({name: 'tasks'})"><a>Задачи</a></li>
      <li v-if="!isLoggedIn" @click="showLogin"><a>Авторизация</a></li>
      <li v-else @click="logout"><a>Выйти</a></li>
    </ul>
  </menu>
</template>

<script setup>


import AppBearLogo from "@/components/ui/AppLogo.vue";
import {useStore} from "vuex";
import {useRoute, useRouter} from "vue-router";
import {computed, ref} from "vue";

const store = useStore();
const router = useRouter();
const route = useRoute();
const isMenuOpened = ref(false);
const showLogin = () => {
  isMenuOpened.value = false
  store.dispatch('user/showLogin', '');
}
const isLoggedIn = computed(() => {
  return store.getters['user/isAuth']
});
const isMainPage = computed(() => route.name === 'main');
const logout = () => {
  isMenuOpened.value = false
  store.dispatch('user/logout')
}
const toFeed = () => {
  if (isLoggedIn.value) {
    router.push({name: 'feed'})
  } else {
    store.dispatch('user/showLogin', {message: 'Войдите для просмотра ленты'})
  }
  isMenuOpened.value = false
}
</script>

<style lang="scss" scoped>



header {
  background-color: var(--color-background);
  position: sticky;
  top: 0;
  width: 100%;
  z-index: 200;
  border-bottom: 1px solid var(--color-border);
  box-shadow: 0px -1px 0px 0px var(--color-border) inset;
  height: 70px;
  padding: 15px 75px;
  display: flex;
  justify-content: space-between;
  align-items: center;

  &.transparent {
    background-color: transparent;
  }
}

ul {
  list-style-type: none;
  margin-left: 0;
  padding-left: 0;
}

#menu {
  & ul {
    display: flex;
    align-items: center;

    & li {
      margin-left: 30px;
      font-size: 18px;
      font-weight: 500;
      cursor: pointer;

      &:hover {
        opacity: .7;
        transform: scale(102%);
      }
    }
  }
}


#auth {
  border: 1px solid var(--color-text);
  border-radius: 100px;
  padding: 10px 20px;
}

#logo {
  cursor: pointer;
  display: flex;
  height: 100%;
  align-items: center;

  & h1 {
    margin-left: 20px;
  }

  & span {
    font-weight: 700;
    color: var(--color-bear)
  }
}

#burger-button {
  display: none;
}

@media (max-width: 800px) {
  header {
    padding: 15px 40px;
  }
}

@media (max-width: 580px) {
  #burger-button {
    display: block;
    font-size: 30px;
  }
  #menu {
    display: none;
  }
  header {
    padding: 15px 40px;
  }
}

menu {
  padding: 0;
}

#burger {
  background-color: var(--color-background);
  position: fixed;
  z-index: 150;
  top: 0;
  left: 0;
  width: 0;
  height: 100%;
  opacity: 0;
  display: none;
  justify-content: center;
  padding-top: 100px;
  transition: all 1s linear;

  &.active {
    transition: all 1s linear;
    opacity: 1;
    display: flex;
    width: 100%;
  }

  & ul {
    width: 100%;

    & li {
      text-align: center;
      border-bottom: 1px solid var(--color-border);
      width: 100%;
      background-color: var(--color-background-soft);
      font-size: 22px;
      padding: 20px;
    }
  }
}
</style>