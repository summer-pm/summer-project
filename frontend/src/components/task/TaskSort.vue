<template>
  <div id="sort">
    <app-dropdown-select v-model="level" :items="levels"/>
    <app-search id="search" v-model="search"/>
  </div>

</template>

<script setup>
import AppDropdownSelect from "@/components/ui/AppDropdownSelect.vue";
import {ref, watch} from "vue";
import AppSearch from "@/components/ui/AppSearch.vue";

const levels = [
  {title: 'Сложность', value: ""},
  {title: 'Easy', value: "EASY"},
  {title: 'Medium', value: "MEDIUM"},
  {title: 'Hard', value: "HARD"}
]
const emit = defineEmits(['findLevel', 'search'])
const level = ref('')
const search = ref('');
watch(level, (newLevel, oldLevel) => {
      if (newLevel !== oldLevel)
        emit('findLevel', newLevel)
    }
)
watch(search, (newSearch, oldSearch) => {
      if (newSearch !== oldSearch)
        emit('search', newSearch)
    }
)
</script>

<style scoped>
#sort{
  display: flex;
  justify-content: space-between;
  flex-wrap: wrap;
}

@media (max-width: 600px) {
  #sort{
  flex-direction: column;
    align-items: center;
}
  #search{
    margin-top: 20px;
  }
}
</style>