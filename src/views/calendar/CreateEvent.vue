<template>
  <div class="create-event-page">
    <div class="header">
      <button class="back-btn" @click="goBack">‹</button>
      <h2 class="title">创建活动</h2>
      <button class="save-btn" @click="saveEvent">保存</button>
    </div>

    <div class="container">
      <div class="form-item">
        <label class="form-label">活动名称</label>
        <input v-model="event.name" type="text" class="input" placeholder="请输入活动名称" />
      </div>

      <div class="form-item">
        <label class="form-label">活动类型</label>
        <div class="type-selector">
          <div v-for="type in eventTypes" :key="type.id" class="type-item" :class="{ active: event.type === type.id }" @click="event.type = type.id">
            <span class="type-icon">{{ type.icon }}</span>
            <span class="type-label">{{ type.label }}</span>
          </div>
        </div>
      </div>

      <div class="form-item">
        <label class="form-label">开始时间</label>
        <input v-model="event.startTime" type="text" class="input" placeholder="请选择时间" />
      </div>

      <div class="form-item">
        <label class="form-label">结束时间</label>
        <input v-model="event.endTime" type="text" class="input" placeholder="请选择时间" />
      </div>

      <div class="form-item">
        <label class="form-label">参与成员</label>
        <div class="member-selector">
          <div v-for="member in familyMembers" :key="member.id" class="member-item" :class="{ active: selectedMembers.includes(member.id) }" @click="toggleMember(member.id)">
            <span class="member-avatar">{{ member.avatar }}</span>
            <span class="member-name">{{ member.name }}</span>
          </div>
        </div>
      </div>

      <div class="form-item">
        <label class="form-label">备注</label>
        <textarea v-model="event.remark" class="input textarea" placeholder="添加备注"></textarea>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useFamilyStore } from '@/stores'

const router = useRouter()
const familyStore = useFamilyStore()
const familyMembers = ref(familyStore.familyMembers)

const event = ref({
  name: '',
  type: 1,
  startTime: '',
  endTime: '',
  remark: ''
})

const selectedMembers = ref([1, 2, 3])

const eventTypes = ref([
  { id: 1, icon: '🎮', label: '娱乐活动' },
  { id: 2, icon: '📚', label: '学习活动' },
  { id: 3, icon: '🏃', label: '户外活动' },
  { id: 4, icon: '🏠', label: '家务任务' },
  { id: 5, icon: '✏️', label: '自定义' }
])

const goBack = () => router.back()

const saveEvent = () => {
  alert('保存成功！')
  router.back()
}

const toggleMember = (id) => {
  const index = selectedMembers.value.indexOf(id)
  if (index > -1) {
    selectedMembers.value.splice(index, 1)
  } else {
    selectedMembers.value.push(id)
  }
}
</script>

<style lang="scss" scoped>
.create-event-page {
  min-height: 100vh;
  background: #f8f9fa;
}

.header {
  background: #fff;
  padding: 16px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  border-bottom: 1px solid #f1f3f5;
}

.back-btn {
  width: 40px;
  height: 40px;
  border: none;
  background: #f8f9fa;
  border-radius: 50%;
  font-size: 24px;
  cursor: pointer;
}

.title {
  font-size: 18px;
  font-weight: 600;
  color: #2d3436;
}

.save-btn {
  border: none;
  background: none;
  font-size: 15px;
  font-weight: 500;
  color: #ff6b35;
  cursor: pointer;
}

.container {
  padding: 16px;
}

.form-item {
  margin-bottom: 20px;

  .form-label {
    display: block;
    font-size: 15px;
    font-weight: 500;
    color: #2d3436;
    margin-bottom: 12px;
  }
}

.type-selector {
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  gap: 12px;
}

.type-item {
  background: #fff;
  border-radius: 12px;
  padding: 16px 8px;
  text-align: center;
  cursor: pointer;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  border: 2px solid transparent;

  &.active {
    border-color: #ff6b35;
  }

  .type-icon {
    display: block;
    font-size: 28px;
    margin-bottom: 8px;
  }

  .type-label {
    display: block;
    font-size: 12px;
    color: #636e72;
  }
}

.member-selector {
  display: flex;
  gap: 12px;
  flex-wrap: wrap;
}

.member-item {
  background: #fff;
  border-radius: 12px;
  padding: 12px;
  text-align: center;
  cursor: pointer;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  border: 2px solid transparent;

  &.active {
    border-color: #ff6b35;
  }

  .member-avatar {
    display: block;
    font-size: 28px;
    margin-bottom: 4px;
  }

  .member-name {
    display: block;
    font-size: 12px;
    color: #636e72;
  }
}

.textarea {
  height: 100px;
  resize: none;
  padding: 12px;
}
</style>