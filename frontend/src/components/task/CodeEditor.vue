<template>
  <div>
    <select v-model="solution.language">
      <option value="JAVA">Java</option>
      <option value="PYTHON">Python</option>
    </select>
    <codemirror
        v-if="!loading"
        v-model="solution.code"
        :autofocus="true"
        :disabled="$props.pending"
        :extensions="extensions"
        :indent-with-tab="true"
        :style="{ height: '100%', width: '100%' }"
        :tab-size="4"
        placeholder="Ваше решение..."
    />
    <button :disabled="$props.pending" @click="sendSolution">
      <span v-if="!$props.pending"> Отправить </span>
    </button>
  </div>

</template>

<script>
import {defineComponent, reactive, ref, shallowRef, watch} from "vue";
import {Codemirror} from "vue-codemirror";
import {java} from "@codemirror/lang-java";
import {useRoute} from "vue-router";
import {oneDark} from "@codemirror/theme-one-dark";
import {python} from "@codemirror/lang-python";


export default defineComponent({
  components: {Codemirror},
  props: {
    pending: Boolean,
  },

  setup(props) {
    const loading = shallowRef(false)
    const route = useRoute()

    const extensions = ref([java()])



    checkForDarkTheme();

    const view = shallowRef()
    const handleReady = (payload) => {
      view.value = payload.view
    }
    const solution = reactive({
      code: "class Solution{\n" +
          "    public int add(int a, int b){\n" +
          "        \n" +
          "    }\n" +
          "}",
      language: "JAVA",
      taskId: route.params.id
    })


    function sendSolution() {
      this.$emit('sendAttempt', solution)
    }
     function checkForDarkTheme() {
      if (window.matchMedia('(prefers-color-scheme: dark)').matches)
        extensions.value.push(oneDark)
    }

    watch(
        () => solution.language,
        (lang) => {
          loading.value = true;
          if (lang === 'JAVA') {
            extensions.value = [java()]
          } else {
            extensions.value = [python()]
          }
         checkForDarkTheme();
          setTimeout(() => loading.value = false, 200)

        }
    )

    return {
      loading,
      sendSolution,
      solution,
      extensions,
      handleReady,
      log: console.log
    }
  }
})
</script>

<style lang="scss" >
button {
  margin-top: 40px;
  background: var(--color-main);
  color: var(--color-text);
  padding: 10px 40px;
  border-radius: 100px;
  box-shadow: 0px 5px 15px 0px rgba(14, 26, 57, 0.20);
  border: none;
  font-size: 16px;
  cursor: pointer;
  transition: all .2s linear;

  &:hover {
    transform: scale(102%);
  }
}
select{

  margin-bottom: 35px;
  font-size: 16px;
  padding: 10px 50px;
   border-radius: 100px;
  border: none;
  color: var(--color-text);
  background-color: var(--color-background-soft);
}




/* скроет иконку стрелки в IE */


</style>