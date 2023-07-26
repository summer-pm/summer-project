<template>
    <div class="chat-header">
        <div class="interlocutor-name">
            <span>{{ activeInterlocutorName }}</span>
            <span>Token: {{ token }}</span>
        </div>
    </div>
</template>

<script> 
import { mapState } from 'vuex';
    export default {
        methods: {
            
        },
        computed: {
            ...mapState([
                'activeInterlocutorName',
                'token'
            ]),
        }
        
    }
</script>

<style scoped>
.chat-header {
    padding: 16px;
    font-size: 18px;
    font-weight: bold;
    border-bottom: 1px solid #ccc;
}

.interlocutor-name {
    color: #333;
}
</style>