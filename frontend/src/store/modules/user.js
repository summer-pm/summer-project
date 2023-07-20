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
    actions: {
        register({commit}, {email, name, password}) {
            commit('registerStart')
            setTimeout(() => {
                userApi.register(name, email, password)
                    .then(commit('registerSuccess'))
                    .catch(err => {
                        console.log(err)
                        commit('registerFailure')
                    })
            }, 300)

        },
        login({commit}, {email, password}) {
            commit('loginStart')
            setTimeout(() => {
                userApi.login(email, password)
                    .then(r => {
                        commit('loginSuccess', r.data.token);
                        //TODO: Возможно сделать переменную в vuex, чтобы показывать где угодно
                        router.push({name : 'tasks'})
                    })
                    .catch(err => {
                        console.log(err)
                        commit('loginFailure')
                    })
            }, 300)
        }
    }
}