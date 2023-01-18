<template>
    <div>
        <input type="text" placeholder="Write something" v-model="text" />
        <input id="filesUpload" type="file" name="files" @change="onFile" multiple/>
        <input type="button" value="Save" @click="save" />
        <img :src="imgSrc" v-if="imgSrc" />
    </div>
</template>

<script>
    import { mapActions } from 'vuex'


    export default {
        props: ['messages', 'messageAttr'],
        data() {
            return {
                imgSrc: '',
                text: '',
                id: ''
            }
        },
        watch: {
            messageAttr: function(newVal, oldVal) {
                this.text = newVal.text
                this.id = newVal.id
            }
        },
        methods: {
            ...mapActions(['addMessageAction', 'updateMessageAction']),
            save() {
                const message = {
                    id: this.id,
                    text: this.text,
                    images: this.images
                }

                if (this.id) {
                    this.updateMessageAction(message)
                } else {

                    const jsonStr = JSON.stringify(message);
                    const blob = new Blob([jsonStr], {
                    type: 'application/json'
                    });

                    const files = document.getElementById("filesUpload").files;
                    const files1 = [...files]

//                    let files1 = document.getElementById("filesUpload").files[1];
                    console.log(files1)



                    const formData = new FormData();
                    formData.append('properties', blob )
                    files1.forEach(item =>
                        formData.append('files', item )
                    )


//                    formData.append('files', files1 )

                    this.axios.post("/message", formData)

//                    this.addMessageAction(message)
                }

                this.text = ''
                this.id = ''
            },
            onFile(e) {
                  const files = e.target.files
                  console.log(files)
                  if (!files.length) return

                  const reader = new FileReader()
                  reader.readAsDataURL(files[0])
                  reader.onload = () => (this.imgSrc = reader.result)

                }
        }
    }
</script>

<style>

</style>