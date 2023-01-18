<template>
    <div style="position: relative; width: 300px;">
        <message-form :messageAttr="message" />
        <message-row v-for="message in sortedMessages"
                     :key="message.id"
                     :message="message"
                     :editMessage="editMessage" />
    </div>
</template>

<script>
    import { mapGetters, mapActions } from 'vuex'
    import MessageRow from 'components/messages/MessageRow.vue'
    import MessageForm from 'components/messages/MessageForm.vue'
    import mApi from 'api/messages'

    export default {
        components: {
            MessageRow,
            MessageForm
        },
        data() {
            return {
                message: null
            }
        },
        computed: mapGetters(['sortedMessages']),
        methods: {
            ...mapActions(['getAllMessages']),
            editMessage(message) {
                this.message = message
            }
        },
        created () {
            this.getAllMessages()
        }
    }
</script>

<style>

</style>