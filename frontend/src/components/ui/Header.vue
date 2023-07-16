<template>
<header>
  <bear-logo/>
</header>
</template>

<script>
import BearLogo from "@/components/ui/BearLogo.vue";

export default {
  name: "Header",
  components: {BearLogo}
}
</script>

<style scoped>
header{
  height: 60px;
  padding: 10px 35px;
}
</style>