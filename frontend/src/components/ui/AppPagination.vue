<template>
  <div v-if="total > 1" class="pagination">
    <div :class="{disabled : current === 0}" class="pagination-item control" @click="changePage(current - 1)">--</div>
    <div v-for="n in total" :key="n" :class="{active : n-1 === current}" class="pagination-item "
         @click="changePage(n - 1)">
      {{ n - 1 }}
    </div>
    <div :class="{disabled :current === total - 1}" class="pagination-item" @click="changePage(current + 1)">++</div>
  </div>
</template>

<script>
import {defineComponent} from "vue";

export default defineComponent({
  props: {
    total: {
      type: Number,
      required: true,
    },
    current: {
      type: Number,
      required: true,
    }
  },

  setup(props, context) {
    const changePage = (page) => {
      if (page !== props.current && page !== -1 && page !== props.total)
        context.emit('changePage', page);
    }
    return {
      changePage
    }
  }
});


</script>

<style lang="scss" scoped>

.pagination {
  display: flex;
  justify-content: center;

  &-item {
    cursor: pointer;
    background-color: var(--color-background-soft);
    font-size: 22px;
    border-radius: 30px;
    width: 60px;
    height: 60px;
    display: flex;
    align-items: center;
    justify-content: center;
    margin: 0 6px;
    font-weight: 500;

    &:hover {
      background-color: var(--color-background-mute);
    }

    &.active {
      border: 2px solid var(--color-border);
      cursor: default;

    }

    &.disabled {
      opacity: 0.7;
      cursor: default;
      &:hover{
        background-color: var(--color-background-soft);
      }
    }
  }

}
@media (max-width: 600px) {
  .pagination-item{
    font-size: 16px;
    width: 50px;
    height: 50px;
  }
}
</style>