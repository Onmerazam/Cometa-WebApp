import { createStore } from 'vuex'
import messagesApi from 'api/messages.js'

const store = createStore({
  state: {
    messages: [],
    profile: frontendData.profile
  },
  getters:{
    sortedMessages: state => state.messages.sort((a,b) => -(a.id - b.id))

  },
  mutations:{
    addMessageMutation(state, message) {
        state.messages = [
            ...state.messages,
            message
        ]
    },
    updateMessageMutation(state, message) {
        const updateIndex = state.messages.findIndex(item => item.id === message.id)
        state.messages = [
            ...state.messages.slice(0, updateIndex),
            message,
            ...state.messages.slice(updateIndex + 1)
        ]

    },
    removeMessageMutation(state, message) {
        const removeIndex = state.messages.findIndex(item => item.id === message.id)
        if (removeIndex > -1){
            state.messages = [
                ...state.messages.slice(0, removeIndex),
                ...state.messages.slice(removeIndex + 1)
            ]
        }
    },

    setMessages(state, messages){
        state.messages = messages
    }
  },
  actions:{
    async addMessageAction({commit, state}, message){
        const result = await messagesApi.add(message)
        const data = await result.data
        const index = state.messages.findIndex(item => item.id === data.id)
        if (index > -1) {
            commit('updateMessageMutation', data)

        } else {

            commit('addMessageMutation', data)
        }
    },
    async updateMessageAction({commit}, message){
        const result = await messagesApi.update(message)
        const data = await result.data
        commit('updateMessageMutation', data)

    },
    async removeMessageAction({commit}, message){
        const result = await messagesApi.remove(message.id)
        const data = await result.data
        if (result.status == 200) {
            commit('removeMessageMutation', data)
        }
    },
    async getAllMessages( {commit} ){
        const result = await messagesApi.getMessages()
        const data = await result.data
        if (result.status == 200) {
            commit('setMessages', data)
        }

    }
  }

})
export default store