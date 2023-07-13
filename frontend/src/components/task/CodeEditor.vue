<template>
  <div>
    <codemirror
        v-model="solution.code"
        :autofocus="true"
        :extensions="extensions"
        :indent-with-tab="true"
        :style="{ height: '400px', width: '100%' }"
        :tab-size="4"
        :disabled="$props.pending"
        placeholder="Ваше решение..."
    />
    <button :disabled="$props.pending" @click="sendSolution">
      <span v-if="!$props.pending"> Отправить </span>
      </button>
  </div>

</template>

<script>
import {defineComponent, reactive, shallowRef} from "vue";
import {Codemirror} from "vue-codemirror";
import {java} from "@codemirror/lang-java";
import {python} from "@codemirror/lang-python";
import {useRoute} from "vue-router";
import {oneDark} from "@codemirror/theme-one-dark";


export default defineComponent({
  components: {Codemirror},
  props: {
    pending: Boolean,
  },
  setup(props) {
    const extensions =  [ java()]

    if(window.matchMedia('(prefers-color-scheme: dark)').matches)
        extensions.push(oneDark)
    // Codemirror EditorView instance ref
    const view = shallowRef()
    const route = useRoute()
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

    return {
      sendSolution,
      solution,
      extensions,
      handleReady,
      log: console.log
    }
  }
})
</script>

<style lang="scss" scoped>
button {
  margin-top: 40px;
  background: var(--color-main);
  color: var(--color-text);
  padding: 10px 40px;
  border-radius: 10px;
  border: none;
  font-size: 16px;
  cursor: pointer;
  transition: all .2s linear;

  &:hover {
    transform: scale(102%);
  }
}
</style>