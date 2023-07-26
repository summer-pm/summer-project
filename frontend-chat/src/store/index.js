import axios from 'axios';
import { createStore } from 'vuex';
import SockJs from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import * as API from '@/api/apiPaths';
import {API_BASE_URL, HOST_NAME, ROOMS_ENDPOINT, WS_PATH} from "@/api/apiPaths";

// Функции для получения данных из LocalStorage
const getSavedDataFromLocalStorage = (key) => {
  return JSON.parse(localStorage.getItem(key)) || {};
};

const setSavedDataToLocalStorage = (key, data) => {
  localStorage.setItem(key, JSON.stringify(data));
};

const getAuthTokenFromLocalStorage = key => {
  const token = JSON.parse(localStorage.getItem(key));
  if (token) {
    // state.isAuth = true;
    return token;
  }
  else {
    // state.isAuth = false;
    return null;
  }

}

const stompSubscriptionPlugin = (store) => {
    store.subscribe((mutation, state) => {
      if (mutation.type === 'SET_CHATS_TO_STORE') {
        // Подписываемся на новые топики
        state.chats.forEach(chat => {
          const subscription = state.stompClient.subscribe(`/topic/room/${chat.roomId}`, message => {
            let json = JSON.parse(message.body);
            const roomId = json.body.chatRoomId;
            if (!state.chatMessages[roomId]) {
              state.chatMessages[roomId] = []; // Создаем пустой массив, если комнаты еще нет в хранилище
            }
            state.chatMessages[roomId].push(json.body);
            console.log(`Received message from ${json.body.chatRoomId}: ${json.body.content}`);
          });
          console.log(subscription);
          state.currentSubscriptions.set(chat.roomId, subscription);
          // Сохраняем подписку в массив для дальнейшей отписки при необходимости
        });
      }
      if (mutation.type === 'DELETE_CHAT') {
        /// TO-DO удалять чаты и подписки;
      }
    });
  };


// Определения геттеров, мутаций и действий
const state = {
    isAuth: false,
    token: 'localStorage.getItem("token") || null,',
    chats: [],
    chatMessages: {},
    currentSubscriptions: new Map(),
    currentUserId: '64b8e14a631df963c898ae4a',
    activeChatId: getSavedDataFromLocalStorage('activeChatId'),
    activeInterlocutorName: getSavedDataFromLocalStorage('activeInterlocutorName'),
    stompClient: {},
    socket: {},
    stompConnected: false
};

const getters = {};

const mutations = {
    SET_TOKEN(state, token) {
      state.token = token;
    },
    ADD_MESSAGE(state, message) {
        const roomId = message.chatRoomId;
        if (!state.chatMessages[roomId]) {
            state.chatMessages[roomId] = []; // Создаем пустой массив, если комнаты еще нет в хранилище
        }
        state.chatMessages[roomId].push(message);
    },
    SET_CHATS_TO_STORE(state, chats) {
    state.chats = chats;
  },
  CLEAR_INTERLOCUTOR_FROM_HEAD(state) {
    state.activeInterlocutorName = {};
    state.activeChatId = {};
  },
  SET_ACTIVE_CHAT(state, chat) {
    if (chat) {
      state.activeInterlocutorName = chat.interlocutor;
      state.activeChatId = chat.roomId;
    } else {
      state.activeInterlocutorName = '';
      state.activeChatId = null
    }
  },
  CONNECT_STOMP_SOCK(state) {
    try {
      state.socket = new SockJs(HOST_NAME + WS_PATH);
      state.stompClient = Stomp.over(state.socket);
  
      console.log('WEBSOCKET CONNECTION: ', state.socket, state.stompClient);
      state.stompClient.connect({}, frame => {
        console.log(frame);
        state.stompConnected = true; // Устанавливаем флаг успешного подключения
      });
    } catch (e) {
      console.error('WS CONNECTION ERROR >>', e);
      state.stompConnected = false; // Устанавливаем флаг неудачного подключения
    }
  }
};

const actions = {
  async FETCH_USER_DATA({ commit }) {
    try {
      const user = await axios.get(API.GATEWAY_PATH
                                  +API.API_VERSION
                                  +API.USERS_CRUD
                                  +'/profile');
      console.log('GET response >>', user.data);
      setTimeout(() => {
        commit('SET_USER_TO_STORE')
      })
    }
    catch(error) {
      console.error('GET request error >> ', error);
    }
  },
  SET_TOKEN({ commit }, t) {
    commit('SET_TOKEN', t);
  },
  async FETCH_CHATS({ commit }) {
    try {
        const chats = await axios
            .get(API_BASE_URL +
                ROOMS_ENDPOINT + '/test?userId=64b8e14a631df963c898ae4a');
        console.log('GET response >> ', chats)
        setTimeout(() => {
            commit('SET_CHATS_TO_STORE', chats.data);
        }, 350);
    } catch (error) {
      console.error('GET request error >> ', error);
    }
  },
  ESCAPE_FROM_CHAT({ commit }) {
    commit('CLEAR_INTERLOCUTOR_FROM_HEAD');
  },
  UPDATE_ACTIVE_CHAT({ commit }, chat) {
    commit('SET_ACTIVE_CHAT', chat);
  },
  async SAVE_MESSAGE_TO_DB({commit}, message) {
    try {
      const response = await axios.post(API_BASE_URL + MESSAGES_ENDPOINT, message);
      console.log('POST response >> ', response.data);
      return response.data; // Возвращаем данные ответа
    } 
    catch (error) {
      console.error('POST request error >> ', error);
      throw error; // Прокидываем ошибку дальше для обработки в компонентах, если необходимо
    }
  },
  CONNECT_TO_WEBSOCKET({ commit }) {
    commit('CONNECT_STOMP_SOCK');
  },
  STORE_NEW_MESSAGE({ commit }, message) {
    commit('ADD_MESSAGE', message);
}
};

// Создание Vuex Store с использованием определений выше
const store = createStore({
  state,
  getters,
  mutations,
  actions,
  plugins: [stompSubscriptionPlugin],
  modules: {},
});

// Подписка на изменение состояния и сохранение данных в LocalStorage
store.subscribe((mutation, state) => {
  setSavedDataToLocalStorage('activeChatId', state.activeChatId);
  setSavedDataToLocalStorage('activeInterlocutorName', state.activeInterlocutorName);
});

export default store;
