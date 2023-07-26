<template>
  <div >
    <app-bear-logo :is-loader="true"/>
  </div>


</template>

<script>


import AppBearLogo from "@/components/ui/AppLogo.vue";

export default {
  name: "AppLoader",
  components: {AppBearLogo}
}
</script>

<style scoped>

</style>