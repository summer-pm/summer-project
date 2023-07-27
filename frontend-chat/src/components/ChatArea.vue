<template>
    <div class="chat-area">
      <div 
        class="empty-area-wrapper" 
        v-if="isEmpty(activeInterlocutorName)">
        <chat-empty-area/>
      </div>
      <div class="chat-area-wrapper" v-else>
        <chat-area-active/>
      </div>
    </div>
  </template>
  
<script>
import ChatEmptyArea from '@/components/ChatEmptyArea.vue';
import ChatAreaActive from '@/components/ChatAreaActive.vue';
  
import { mapState } from 'vuex';

export default {
    components: {
      ChatEmptyArea,
      ChatAreaActive
    },
    data() {
      return {
        
      }
    },
    methods: {
      isEmpty(activeInterlocutorName) {
        return Object.keys(activeInterlocutorName).length === 0 && activeInterlocutorName.constructor === Object;
      }
    },
    computed: {
      ...mapState([
          'activeInterlocutorName',
      ]),
    },
  
  };
  </script>
  
<style>
.chat-area-wrapper  {
  width: 100%;
  height: 100%;
}

.chat-area {
    position: relative;
    display: flex;
    flex-direction: column;
    height: 92vh;
    background-color: #fff;
    border-left: 1px solid #ccc;
    overflow: hidden;
    width: 70vw;
    margin-left: auto;
}

.empty-area-wrapper {
  width: 100%;
  height: 100%;
}
</style>
  