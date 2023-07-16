<template>
  <div >
    <bear-logo/>
  </div>


</template>

<script>
import BearLogo from "@/components/ui/BearLogo.vue";

export default {
  name: "Loader",
  components: {BearLogo}
}
</script>

<style >
#bear{
  animation-duration: .6s;
   animation-name: fade-in;
animation-iteration-count: infinite;
}
#bear1{
  animation-duration: .6s;
   animation-name: fade-in;
animation-iteration-count: infinite;
}
@keyframes fade-in {
  from {
    opacity: 0;
  }

  to {
    opacity: 1;
  }
}
</style>