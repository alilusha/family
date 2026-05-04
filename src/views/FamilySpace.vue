<template>
  <div class="family-space-page">
    <div class="header">
      <button class="back-btn" @click="goBack">‹</button>
      <h2 class="title">👨‍👩‍👧‍👦 家庭空间</h2>
    </div>

    <div class="container">
      <div class="family-info-card">
      <div class="family-icon">{{ familyInfo?.avatar || '🏠' }}</div>
      <div class="family-details">
        <h3 class="family-name">{{ familyInfo?.name || '快乐一家人' }}</h3>
        <p class="invite-code">邀请码：{{ inviteCode }}</p>
      </div>
      <button class="btn btn-outline btn-small share-btn">分享</button>
    </div>

    <div class="section mt-6">
      <h3 class="section-title">家庭成员</h3>
      <div class="member-list">
        <div v-for="member in familyMembers" :key="member.id" class="member-card">
          <div class="member-avatar">{{ member.avatar }}</div>
          <div class="member-info">
            <h4 class="member-name">{{ member.name }}</h4>
            <p class="member-role">{{ member.role }}</p>
          </div>
          <button v-if="isAdmin" class="btn btn-outline btn-small remove-btn">移除</button>
        </div>
      </div>
    </div>

    <div class="section mt-6">
      <h3 class="section-title">家庭公告</h3>
      <div class="notice-card">
        <p class="notice-content">本周六下午3点，家庭游戏比赛！欢迎大家积极参与！</p>
        <p class="notice-time">—— 爸爸 · 2小时前</p>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { useFamilyStore } from '@/stores'

const router = useRouter()
const familyStore = useFamilyStore()

const familyInfo = computed(() => familyStore.familyInfo)
const familyMembers = computed(() => familyStore.familyMembers)
const isAdmin = ref(true)
const inviteCode = ref('ABC123')

const goBack = () => router.back()
</script>

<style lang="scss" scoped>
.family-space-page {
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

.family-info-card {
  background: #fff;
  border-radius: 16px;
  padding: 24px;
  display: flex;
  align-items: center;
  gap: 16px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);

  .family-icon {
    width: 64px;
    height: 64px;
    border-radius: 16px;
    background: #ff6b35;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 32px;
  }

  .family-details {
    flex: 1;

    .family-name {
      font-size: 18px;
      font-weight: 600;
      color: #2d3436;
      margin-bottom: 4px;
    }

    .invite-code {
      font-size: 13px;
      color: #636e72;
    }
  }
}

.section {
  .section-title {
    font-size: 16px;
    font-weight: 600;
    color: #2d3436;
    margin-bottom: 16px;
  }
}

.member-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.member-card {
  background: #fff;
  border-radius: 12px;
  padding: 16px;
  display: flex;
  align-items: center;
  gap: 16px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);

  .member-avatar {
    width: 48px;
    height: 48px;
    border-radius: 50%;
    background: #f8f9fa;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 24px;
  }

  .member-info {
    flex: 1;

    .member-name {
      font-size: 15px;
      font-weight: 500;
      color: #2d3436;
      margin-bottom: 4px;
    }

    .member-role {
      font-size: 13px;
      color: #636e72;
    }
  }
}

.notice-card {
  background: #fff;
  border-radius: 12px;
  padding: 16px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);

  .notice-content {
    font-size: 15px;
    color: #2d3436;
    margin-bottom: 8px;
  }

  .notice-time {
    font-size: 13px;
    color: #636e72;
    text-align: right;
  }
}
</style>