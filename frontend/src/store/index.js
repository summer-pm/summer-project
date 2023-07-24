import {createStore} from "vuex";
import {userModule} from "@/store/modules/user";

export default createStore({
    modules: {
        user: userModule
    }
})