import { createWebHistory, createRouter } from "vue-router";
import MessagesList from 'components/messages/MessagesList.vue'
import Login from 'pages/Login.vue'
import SignUp from 'pages/SignUp.vue'

const routes = [
  { path: "/", component: MessagesList },
  { path: "/login", component: Login },
  { path: "/signup", component: SignUp }
]


const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;