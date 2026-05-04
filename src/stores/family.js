import { defineStore } from 'pinia'
import { ref } from 'vue'

export const useFamilyStore = defineStore('family', () => {
  const familyInfo = ref(JSON.parse(localStorage.getItem('familyInfo') || '{}'))
  const familyMembers = ref([
    { id: 1, name: '爸爸', avatar: '👨', role: '管理员' },
    { id: 2, name: '妈妈', avatar: '👩', role: '管理员' },
    { id: 3, name: '小明', avatar: '👦', role: '儿童' }
  ])
  const hasFamily = ref(!!Object.keys(familyInfo.value).length)

  const setFamilyInfo = (info) => {
    familyInfo.value = info
    hasFamily.value = !!Object.keys(info).length
    localStorage.setItem('familyInfo', JSON.stringify(info))
  }

  const createFamily = (name, avatar = '🏠') => {
    const info = {
      id: Date.now(),
      name,
      avatar,
      inviteCode: Math.random().toString(36).substring(2, 8).toUpperCase(),
      createTime: new Date().toISOString()
    }
    setFamilyInfo(info)
    return info
  }

  const joinFamily = (inviteCode) => {
    const info = {
      id: Date.now(),
      name: '快乐一家人',
      avatar: '🏠',
      inviteCode,
      joinTime: new Date().toISOString()
    }
    setFamilyInfo(info)
    return info
  }

  const addMember = (member) => {
    familyMembers.value.push({
      ...member,
      id: Date.now()
    })
  }

  const removeMember = (id) => {
    const index = familyMembers.value.findIndex(m => m.id === id)
    if (index > -1) {
      familyMembers.value.splice(index, 1)
    }
  }

  return {
    familyInfo,
    familyMembers,
    hasFamily,
    setFamilyInfo,
    createFamily,
    joinFamily,
    addMember,
    removeMember
  }
})