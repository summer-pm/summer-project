<template>

  <div style="margin-top: 40px;">
    <task-sort @search="search" @find-level="findLevel"/>
    <div v-if="loading" style="margin-top: 100px; display: flex; justify-content: center; width: 100%;">
      <app-loader/>

    </div>
    <div v-else-if="data.content.length !== 0" id="table">
      <div v-for="(task,n) in data.content" :key="n">
        <task-item :is-odd="n % 2 === 0" :task="task"
                   @click="$router.push({ name: 'solution', params: { id: task.id } })"/>
      </div>
    </div>
    <h3 v-else style="text-align: center; margin-top: 40px;">К сожалению, ничего не найдено(</h3>
    <div id="pagination">
      <app-pagination :current="data.number" :total="data.totalPages" @changePage="changePage"/>
    </div>
  </div>

</template>

<script setup>
import {onMounted, reactive, ref} from "vue";
import taskApi from "@/api/taskApi";
import TaskItem from "@/components/task/TaskItem.vue";
import AppPagination from "@/components/ui/AppPagination.vue";
import AppLoader from "@/components/ui/AppLoader.vue";
import {useRoute} from "vue-router";
import TaskSort from "@/components/task/TaskSort.vue";

const route = useRoute();
const loading = ref(false);
const searchParams = reactive({
  title: route.query.title,
  status: '',
  level: route.query.level
});
const data = reactive({
  content: [],
  totalPages: 0,
  number: 0,
  pageSize: 10,
  sort: null
});
const loadData = () => {
  loading.value = true
  setTimeout(() => {

    taskApi.getAll(data.number,
        searchParams.title,
        searchParams.level)
        .then((r) => {
          Object.assign(data, r.data)
        }).finally(() => {
      loading.value = false
    });
  }, 1000)

}
const search = (search) => {
  searchParams.title = search
  loadData()
}
const changePage = (page) => {
  data.number = page;
  loadData()
}
const findLevel = (level) => {
  searchParams.level = level
  loadData()
}
onMounted(loadData)
</script>
<style scoped>
#pagination {
  margin-top: 30px;
}

#table {
  margin-top: 20px;
}
</style>