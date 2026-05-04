<template>
  <div class="notifications-page">
    <div class="header">
      <button class="back-btn" @click="goBack">‹</button>
      <h2 class="title">🔔 消息通知</h2>
    </div>

    <div class="container">
      <div class="notification-list">
        <div v-for="notification in notifications" :key="notification.id" class="notification-card">
          <div class="notification-icon">{{ notification.icon }}</div>
          <div class="notification-content">
            <h4 class="notification-title">{{ notification.title }}</h4>
            <p class="notification-desc">{{ notification.desc }}</p>
            <p class="notification-time">{{ notification.time }}</p>
          </div>
        </div>
      </div>
      <div v-if="notifications.length === 0" class="empty-state">
        暂无消息
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const notifications = ref([
  { id: 1, icon: '🎮', title: '爸爸发起了问答大挑战', desc: '房间号：123456，快来加入！', time: '5分钟前' },
  { id: 2, icon: '📅', title: '明日有家庭活动', desc: '家庭游戏下午茶，下午14:00开始', time: '1小时前' },
  { id: 3, icon: '👤', title: '爷爷加入了家庭', desc: '新成员加入，欢迎欢迎！', time: '昨天' }
])

const goBack = () => router.back()
</script>

<style lang="scss" scoped>
.notifications-page {
  min-height: 100vh;
  background: #f8f9fa;
}

.header {
  background: #fff;
  padding: 16px;
  display: flex;
  align-items: center;
  gap: 12px;
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
  font-size: 20px;
  font-weight: 600;
  color: #2d3436;
}

.container {
  padding: 16px;
}

.notification-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.notification-card {
  background: #fff;
  border-radius: 12px;
  padding: 16px;
  display: flex;
  gap: 16px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);

  .notification-icon {
    font-size: 32px;
  }

  .notification-content {
    flex: 1;

    .notification-title {
      font-size: 15px;
      font-weight: 500;
      color: #2d3436;
      margin-bottom: 4px;
    }

    .notification-desc {
      font-size: 13px;
      color: #636e72;
      margin-bottom: 4px;
    }

    .notification-time {
      font-size: 12px;
      color: #b2bec3;
    }
  }
}

.empty-state {
  text-align: center;
  padding: 40px;
  color: #b2bec3;
}
</style>