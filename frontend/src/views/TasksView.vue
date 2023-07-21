<template>
  <div id="table">
    <div v-for="(task,n) in data.content" :key="n">
      <task-item @click="$router.push({ name: 'solution', params: { id: task.id } })" :is-odd="n % 2 === 0" :task="task"/>
    </div>
  </div>
  <div id="pagination">
    <app-pagination @changePage="changePage" :current="data.currentPage" :total="data.totalPages"/>

  </div>
</template>

<script setup>
import {onMounted, reactive} from "vue";
import taskApi from "@/api/taskApi";
import TaskItem from "@/components/task/TaskItem.vue";
import AppPagination from "@/components/ui/AppPagination.vue";

const searchParams = reactive({
  title: '',
  status: '',
  level: ''
});
const data = reactive({
  content: [],
  totalPages: 3,
  currentPage: 0,
  pageSize: 10,
  sort: null
});
const loadData = () => {
  taskApi.getAll(data.currentPage, data.sort,
      searchParams.title, searchParams.status,
      searchParams.level).then((r) => {
    Object.assign(data, r)
  });
}
const changePage = (page) => {
  data.currentPage = page;
}
onMounted(loadData)
</script>
<style scoped>
#pagination{
  margin-top: 30px;
}
#table{
  margin-top: 50px;
}
</style>