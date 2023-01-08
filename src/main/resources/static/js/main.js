
import 'app.js'
import '../css/app.css'
import VueAxios from 'vue-axios'
import axios from 'axios'
import router from 'router/router.js'
import '@babel/polyfill'
import { connect } from './util/ws.js'
import "bootstrap/dist/css/bootstrap.min.css"
import "bootstrap"
import App from 'pages/App.vue'
import store from 'store/store.js'
import { createApp } from 'vue'

if (frontendData.profile) {
    connect()
}


const app = createApp(App)
app.use(router)
app.use(VueAxios, axios)
app.use(store)
app.mount('#app')