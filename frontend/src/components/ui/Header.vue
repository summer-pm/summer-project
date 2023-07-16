<template>
<header>
  <div id="logo">
  <bear-logo/>
    <h1><span>Beer</span>code</h1>
  </div>
</header>
</template>

<script>
import BearLogo from "@/components/ui/BearLogo.vue";

export default {
  name: "Header",
  components: {BearLogo}
}
</script>

<style lang="scss" scoped>
header{
  border-bottom: 1px solid var(--color-border);
  box-shadow: 0px -1px 0px 0px var(--color-border) inset;
  height: 60px;
  padding: 10px 75px;
  display: flex;
}
#logo{
  display: flex;
  height: 100%;
  & h1{
    margin-left: 20px;
  }
  & span{
    font-weight: 700;
    color: var(--color-bear)
  }
}
</style>