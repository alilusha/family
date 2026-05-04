import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useUserStore = defineStore('user', () => {
  const token = ref('admin-token')
  const userInfo = ref({
    username: 'admin',
    nickname: '超级管理员',
    avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png'
  })

  const login = async (formData) => {
    return new Promise((resolve) => {
      setTimeout(() => {
        token.value = 'admin-token'
        resolve()
      }, 500)
    })
  }

  const logout = () => {
    token.value = ''
    userInfo.value = {}
  }

  return {
    token,
    userInfo,
    login,
    logout
  }
})
