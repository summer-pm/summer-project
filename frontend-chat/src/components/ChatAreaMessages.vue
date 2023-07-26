<template>    
    <div class="message-area">
        <div class="message-container">
            <ChatMessage
            v-for="message in chatMessages[activeChatId]"
            :key="message.messageId"
            :message="message"/>
        </div>
    </div>
</template>
<script>
import ChatMessage from '@/components/ChatMessage.vue';
import {mapState} from 'vuex';

    export default {
        components: {ChatMessage},
        computed: {
            ...mapState([
                'messages',
                'activeChatId',
                'chatMessages',
                'activeChatId'
        ])
        }, 
        data() {
            return {
                stompClient: null,
                roomId: '64b8e1ce631df963c898ae4f',
            }
        },
    }
</script>

<style scoped>

.message-area {
    flex: 1;
    padding: 16px;
    
}
  
.message-container {
    flex: 1;
    margin-bottom: 10px;
    overflow-y: auto;
    display: flex;
    flex-direction: column-reverse;
}
  
.message-content {
    background-color: #f2f2f2;
    padding: 8px;
    border-radius: 4px;
}
  
.message-time {
    color: #888;
    font-size: 12px;
    margin-top: 4px;
}
</style>