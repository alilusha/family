<template>
  <div class="profile-page">
    <div class="header">
      <h2 class="title">👤 个人中心</h2>
    </div>

    <div class="container">
      <div class="profile-card">
        <div class="avatar">{{ userInfo.avatar }}</div>
        <div class="info">
          <h3 class="name">{{ userInfo.name }}</h3>
          <p class="family">🏠 {{ familyName }}</p>
        </div>
      </div>

      <div class="stats-card">
        <div class="stat-item">
          <div class="stat-value">{{ stats.games }}</div>
          <div class="stat-label">游戏场</div>
        </div>
        <div class="stat-item">
          <div class="stat-value">{{ stats.score }}</div>
          <div class="stat-label">总积分</div>
        </div>
        <div class="stat-item">
          <div class="stat-value">{{ stats.events }}</div>
          <div class="stat-label">活动次</div>
        </div>
      </div>

      <div class="menu-list">
        <div class="menu-item" @click="goToFamily">
          <span class="menu-icon">🏠</span>
          <span class="menu-label">家庭空间</span>
          <span class="menu-arrow">›</span>
        </div>
        <div class="menu-item" @click="goToNotifications">
          <span class="menu-icon">🔔</span>
          <span class="menu-label">消息通知</span>
          <span class="menu-arrow">›</span>
        </div>
        <div class="menu-item">
          <span class="menu-icon">⚙️</span>
          <span class="menu-label">设置</span>
          <span class="menu-arrow">›</span>
        </div>
        <div class="menu-item">
          <span class="menu-icon">❓</span>
          <span class="menu-label">帮助与反馈</span>
          <span class="menu-arrow">›</span>
        </div>
      </div>

      <button class="btn btn-outline logout-btn" @click="logout">退出登录</button>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore, useFamilyStore } from '@/stores'

const router = useRouter()
const userStore = useUserStore()
const familyStore = useFamilyStore()

const userInfo = computed(() => userStore.userInfo)
const familyName = computed(() => familyStore.familyInfo?.name || '未加入家庭')

const stats = ref({
  games: 32,
  score: 1890,
  events: 15
})

const goToFamily = () => router.push('/family-space')
const goToNotifications = () => router.push('/notifications')

const logout = () => {
  userStore.logout()
  router.push('/login')
}
</script>

<style lang="scss" scoped>
.profile-page {
  min-height: 100vh;
  background: #f8f9fa;
}

.header {
  background: #fff;
  padding: 16px;
  border-bottom: 1px solid #f1f3f5;
}

.title {
  font-size: 20px;
  font-weight: 600;
  color: #2d3436;
}

.container {
  padding: 16px;
}

.profile-card {
  background: #fff;
  border-radius: 16px;
  padding: 24px;
  display: flex;
  align-items: center;
  gap: 16px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  margin-bottom: 16px;

  .avatar {
    width: 64px;
    height: 64px;
    border-radius: 50%;
    background: #ff6b35;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 32px;
  }

  .info {
    flex: 1;

    .name {
      font-size: 18px;
      font-weight: 600;
      color: #2d3436;
      margin-bottom: 4px;
    }

    .family {
      font-size: 13px;
      color: #636e72;
    }
  }
}

.stats-card {
  background: #fff;
  border-radius: 16px;
  padding: 20px;
  display: flex;
  justify-content: space-around;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  margin-bottom: 16px;

  .stat-item {
    text-align: center;

    .stat-value {
      font-size: 24px;
      font-weight: 700;
      color: #ff6b35;
      margin-bottom: 4px;
    }

    .stat-label {
      font-size: 13px;
      color: #636e72;
    }
  }
}

.menu-list {
  background: #fff;
  border-radius: 16px;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  margin-bottom: 24px;
}

.menu-item {
  display: flex;
  align-items: center;
  padding: 16px;
  gap: 12px;
  cursor: pointer;

  &:not(:last-child) {
    border-bottom: 1px solid #f1f3f5;
  }

  .menu-icon {
    font-size: 20px;
  }

  .menu-label {
    flex: 1;
    font-size: 15px;
    color: #2d3436;
  }

  .menu-arrow {
    font-size: 20px;
    color: #b2bec3;
  }
}

.logout-btn {
  width: 100%;
  border-color: #e17055;
  color: #e17055;
}
</style>