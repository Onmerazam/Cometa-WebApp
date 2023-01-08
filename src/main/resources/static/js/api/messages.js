import axios from 'axios'

const messages = axios.create({
  baseURL: '/message/',
  headers: {
    Accept: 'application/json',
  },
})

export default {
    add: message =>  messages.post(messages.baseURL, message),
    update: message =>  messages.put(`${message.id}`, message),
    remove: id =>  messages.delete(`${id}`),
}