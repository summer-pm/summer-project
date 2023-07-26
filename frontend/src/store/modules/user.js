import {getItem, setItem} from "@/store/localStorage";
import userApi from "@/api/userApi";
import router from "@/router";

export const userModule = {
    namespaced: true,
    state: () => ({
        token: getItem('token') || null,
        errors: {
            userExists: false,
            login: false,
        },
        registered: false,
        loading: false,
        showLogin: false,
        loginMessage: '',
    }),
    getters: {
        isAuth: (state) => {
            return state.token !== null
        },
        isLoginError: (state) => {
            return state.errors.login
        },
        isSuccessRegister: (state) => {
            return state.registered
        },
        isRegisterError: (state) => {
            return state.errors.userExists
        },
        isLoading: (state) => {
            return state.loading;
        },
        getToken: (state) => {
            return state.token;
        },
        showLogin: (state) => {
            return state.showLogin
        },
        errorMessage: (state) => {
            return state.loginMessage
        }
    },
    mutations: {
        registerStart(state) {
            state.loading = true;
            state.errors.userExists = false
        },
        registerSuccess(state) {
            state.errors.userExists = false
            state.registered = true;
            state.loading = false;
        },
        registerFailure(state) {
            state.registered = false;
            state.loading = false;
            state.errors.userExists = true
        },
        loginStart(state) {
            state.loading = true;
            state.errors.login = false;
        },
        loginSuccess(state, token) {
            state.errors.login = false;
            state.loginMessage = ''
            state.token = token;
            setItem('token', token)
            state.loading = false;
        },
        loginFailure(state) {
            state.loading = false;
            state.errors.login = true;
        },
        showLogin(state, message) {
            state.token = null;
            state.showLogin = true;
            state.loginMessage = message;
        },
        hideLogin(state) {
            state.showLogin = false;
            state.loginMessage = '';
        },
        logout(state) {
            state.token = null;
            setItem('token', null);
        },
        clearAll(state) {
            state.loading = false;
            state.errors.login = false;
            state.registered = false;
            state.token = null;
            state.errors.userExists = false;
        }
    },
    actions: {

        hideLogin({commit}) {
            commit('clearAll')
            commit('hideLogin')
        },
        register({commit}, {email, name, password}) {
            commit('clearAll')
            commit('registerStart')

            userApi.register(name, email, password)
                .then(commit('registerSuccess'))
                .catch(err => {
                    console.log(err)
                    commit('registerFailure')
                })


        },
        login({commit}, {email, password}) {
            commit('clearAll')
            commit('loginStart')

            userApi.login(email, password)
                .then(r => {
                    commit('loginSuccess', r.data.token);
                    setTimeout(() => commit('hideLogin'), 1500)
                })
                .catch(err => {
                    console.log(err)
                    commit('loginFailure')
                })

        },
        logout({commit}) {
            commit('clearAll')
            commit('logout')
            router.push({name: 'main'})
        },
        showLogin({commit}, {message}) {
            commit('clearAll')
            commit('showLogin', message);
        }
    }
}