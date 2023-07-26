import axios from 'axios';
import { createStore } from 'vuex';
import SockJs from 'sockjs-client';
import { Stomp } from '@stomp/stompjs';
import * as API from '@/api/apiPaths';

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
    authUser: null,
    userPgId: -1,
    token: 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXJhdEB5YW5kZXgucnUiLCJpYXQiOjE2OTAzNzczMzEsImV4cCI6MTY5MjEwNTMzMX0.xcm5bXbs71180Bubkax5PSyU0nje2SgZwVVerKUBVPw',
    chats: [],
    chatMessages: {},
    currentSubscriptions: new Map(),
    currentUserId: '64b8e14a631df963c898ae4a',
    activeChatId: getSavedDataFromLocalStorage('activeChatId'),
    activeInterlocutorName: getSavedDataFromLocalStorage('activeInterlocutorName'),
    stompClient: {},
    socket: {},
    stompConnected: false,
    headerConfig: {
        headers: {
            "Authorization": `Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJtYXJhdEB5YW5kZXgucnUiLCJpYXQiOjE2OTAzNzczMzEsImV4cCI6MTY5MjEwNTMzMX0.xcm5bXbs71180Bubkax5PSyU0nje2SgZwVVerKUBVPw`
        }
    }
};

const getters = {};

const mutations = {
    SET_USER_TO_STORE(state, user) {
        state.authUser = user;
        state.userPgId = user.userId;
    },
    SET_MONGO_ID_TO_STORE(state, data) {
        state.currentUserId = data.id;
    },
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
      state.socket = new SockJs(API.HOST_NAME + API.WS_PATH);
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
  },
    async START_INIT_CHAT(state) {
        try {
            const profileResponse = await axios.get(API.GATEWAY_PATH + API.API_VERSION + API.USERS_CRUD + '/profile', state.headerConfig);
            console.log('Profile: ', profileResponse.data);
            this.commit('SET_USER_TO_STORE', profileResponse.data);

            const isExistsResponse = await axios.get(API.GATEWAY_PATH + API.API_VERSION + API.CHAT_USERS_CRUD + `/${state.userPgId}`, state.headerConfig);
            console.log('Existing: ', isExistsResponse.data);
            this.commit('SET_MONGO_ID_TO_STORE', isExistsResponse.data);

            const chatsResponse = await axios.get(API.GATEWAY_PATH + API.API_VERSION + API.ROOMS_ENDPOINT + `/list?userId=${isExistsResponse.data.id}`, state.headerConfig);
            console.log('Chats: ', chatsResponse.data);
            this.commit('SET_CHATS_TO_STORE', chatsResponse.data);
        } catch (error) {
            // Обработка ошибок, если необходимо
            console.error('Ошибка запроса:', error);
        }
    },
    async ADD_NEW_CHAT_ROOM(state, userEmail) {
        let contain = false;
        state.chats.forEach(chat => {
            if (chat.interlocutorProfile.email === userEmail) {
                contain = true;
            }
        })
        if (contain) {
            alert(`Chat with ${userEmail} already exists`);
        }
        else {
            try {
                console.log(userEmail);
                const interlocutor = await axios.get(API.GATEWAY_PATH + API.API_VERSION + API.USERS_CRUD + `/info?userEmail=${userEmail}`, state.headerConfig);
                console.log('Requesting chat with: ', interlocutor.data);
                const interlocutorProfile = await axios.get(API.GATEWAY_PATH + API.API_VERSION + API.USERS_CRUD + `/${interlocutor.data.userPgId}`, state.headerConfig);
                console.log('Requesting chat with(PG): ', interlocutorProfile.data)
                const body = {
                    users: [state.currentUserId, interlocutor.data.id]
                }
                const newRoom = await axios.post(API.GATEWAY_PATH + API.API_VERSION + API.ROOMS_ENDPOINT, body, state.headerConfig);
                console.log('Room created: ', newRoom.data);
                const room = {
                    roomId: newRoom.data.id,
                    interlocutor: interlocutorProfile.data.username,
                    interlocutorProfile: interlocutorProfile.data,
                    lastMessage: null
                }
                this.commit('ADD_NEW_CHAT_ROOM_TO_STATE', room);
            }
            catch (error) {
                console.error('Ошибка запроса: ', error);
            }
        }
    },
    ADD_NEW_CHAT_ROOM_TO_STATE(state, newRoom) {
        state.chats.push(newRoom);
    }
};


const actions = {
    ADD_NEW_CHAT({ commit }, userEmail) {
        commit('ADD_NEW_CHAT_ROOM', userEmail);
    },
    INIT_CHAT({ commit }) {
        commit('START_INIT_CHAT');
    },


  SET_TOKEN({ commit }, t) {
    commit('SET_TOKEN', t);
  },

  ESCAPE_FROM_CHAT({ commit }) {
    commit('CLEAR_INTERLOCUTOR_FROM_HEAD');
  },
  UPDATE_ACTIVE_CHAT({ commit }, chat) {
    commit('SET_ACTIVE_CHAT', chat);
  },
  async SAVE_MESSAGE_TO_DB({commit}, message) {
    try {
      const response = await axios.post(API.API_BASE_URL + API.MESSAGES_ENDPOINT, message);
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
