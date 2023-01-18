<template xmlns:v-bind="http://www.w3.org/1999/xhtml">
   <div>
       <i>({{ message.id }})</i> {{ message.text }}
       <div v-for="img in message.images">
        <div ><img style="height:100px; width:100px" :src="getImgUrl(img)"   /></div>
       </div>
       <span style="position: absolute; right: 0">
           <input type="button" value="Edit" @click="edit" />
           <input type="button" value="X" @click="del" />
        </span>
    </div>
</template>

<script>
    import { mapActions } from 'vuex'
    export default {
        props: ['message', 'editMessage'],
        methods: {
            ...mapActions(['removeMessageAction']),
            edit() {
                this.editMessage(this.message)
            },
            del() {
                this.removeMessageAction(this.message)
            },
            getImgUrl(img){
                return "/image/" + img
            }
        }
    }
</script>

<style>

</style>