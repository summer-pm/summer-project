import {getItem, setItem} from "@/store/localStorage";
import userApi from "@/api/userApi";

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
        }
    },
    mutations: {
        registerStart(state) {
            state.loading = true;
            state.errors.userExists = false
        },
        registerSuccess(state) {
            state.registered = true;
            state.loading = false;
        },
        registerFailure(state) {
            state.loading = false;
            state.errors.userExists = true
        },
        loginStart(state) {
            state.loading = true;
        },
        loginSuccess(state, token) {
            state.token = token;
            setItem('token', token)
            state.loading = false;
        },
        loginFailure(state) {
            state.loading = false;
            state.errors.login = true;
        },
        clearError(state) {
            state.loading = false;
            state.errors.login = false;
        }
    },
    actions:{
        register({commit}, {email,name,password}){
            commit('registerStart')
            setTimeout(() => {
                 userApi.register(name,email,password)
                .then(commit('registerSuccess'))
                .catch(commit('registerFailure'))
            },3000)

        }
    }
}