

import App from 'pages/App.vue'
import { connect } from './util/ws.js'
import 'startbootstrap-sb-admin-2/css/sb-admin-2.min.css'
import VueAxios from 'vue-axios'
import axios from 'axios'

import { createApp } from 'vue'

if (frontendData.profile) {
    connect()
}


const app = createApp(App)
app.use(VueAxios, axios)
app.mount('#app')