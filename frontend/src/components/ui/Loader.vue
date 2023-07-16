<template>
  <div >
    <bear-logo :is-loader="true"/>
  </div>


</template>

<script>
import BearLogo from "@/components/ui/BearLogo.vue";

export default {
  name: "Loader",
  components: {BearLogo}
}
</script>

<style scoped>

</style>