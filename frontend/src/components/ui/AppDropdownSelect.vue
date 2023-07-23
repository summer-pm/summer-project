<template>
<div id="dropdown">
  <p @click="isOpened = !isOpened" class="wrapper" :class="{'active' : isOpened}">{{selected.title}} <span class="arrow"></span></p>
  <div v-if="isOpened" class="items">
    <ul>
      <li :key="item.value" @click="select(item)" v-for="item in items">{{item.title}}</li>

    </ul>
  </div>
</div>


</template>

<script setup>
const props = defineProps({
  items: Array,
})
import {onBeforeMount, onMounted, reactive, ref} from "vue";
const selected = reactive({title : props.items[0].title, value: props.items[0].value})
const isOpened = ref(false);
const emit = defineEmits(['update:modelValue'])
const select = (item) => {
  console.log(item)
  Object.assign(selected, item)
  isOpened.value = false
  update(item)
}
const update = (item) => {
  emit("update:modelValue", item.value)
}
onBeforeMount(() => {
  emit("update:modelValue", selected.value)
})
</script>

<style lang="sass" scoped>
#dropdown
  display : inline-block
  cursor:  pointer
.items
  z-index: 400
  border: 1px solid var(--color-border)
  width: 220px
  position: absolute
  border-radius: 20px
  margin-top: 15px
  color: var(--color-text)
  background-color: var(--color-background-soft)
  overflow: hidden
  & ul
    margin: 0
    padding: 0
    list-style: none
    overflow: hidden
    & li
      padding: 5px 40px
      &:hover
        overflow: hidden
        font-weight: 600
        background-color: var(--color-background-mute)

.wrapper
  display: inline-flex
  align-items: center
  border-radius: 100px
  color: var(--color-text)
  background-color: var(--color-background-soft)
  padding: 0 40px
  justify-content: space-between
  width: 220px
  height: 45px
  & span
    margin-left: 35px
  &.active
    .arrow
      transform: rotate(45deg) translate(-5px, -5px)

      &:before
        transform: translate(10px, 0)

      &:after
        transform: rotate(90deg) translate(10px, 0)


.arrow
  width: 13px
  height: 13px
  display: inline-block
  position: relative
  bottom: -3px
  left: -10px
  transition: 0.4s ease
  margin-top: 2px
  text-align: left
  transform: rotate(45deg)
  float: right

  &:before, &:after
    position: absolute
    content: ''
    display: inline-block
    width: 12px
    height: 3px
    background-color: var(--color-text)
    transition: 0.4s ease

  &:after
    position: absolute
    transform: rotate(90deg)
    top: -5px
    left: 5px




</style>