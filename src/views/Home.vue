<template>
  <div class="home-page">
    <div class="header">
      <div class="greeting">
        <h2 class="greet-text">早上好，{{ userName }}！👋</h2>
        <p class="sub-text">今天有 {{ todayEvents.length }} 个家庭活动</p>
      </div>
      <div class="header-actions">
        <button class="action-btn" @click="goToNotifications">🔔</button>
        <button class="action-btn" @click="goToProfile">👤</button>
      </div>
    </div>

    <div class="container">
      <div class="quick-start-card" @click="goToGameHall">
        <div class="card-content">
          <div class="icon-wrapper">
            <span class="icon">🎮</span>
          </div>
          <div class="text-wrapper">
            <h3 class="title">快速开始游戏</h3>
            <p class="desc">家庭问答 · 3人在线</p>
          </div>
          <button class="btn btn-capsule btn-outline start-btn">
            立即开始
          </button>
        </div>
      </div>

      <div class="section mt-6">
        <div class="section-header">
          <h3 class="section-title">📅 今日活动</h3>
          <button class="more-btn" @click="goToCalendar">查看全部</button>
        </div>
        <div class="event-list">
          <div v-for="event in todayEvents" :key="event.id" class="event-card">
            <div class="event-time">
              <span class="time">{{ event.time }}</span>
            </div>
            <div class="event-info">
              <h4 class="event-title">{{ event.title }}</h4>
              <p class="event-members">{{ event.members }}</p>
            </div>
            <div class="event-type" :style="{ background: event.color }">
              {{ event.typeIcon }}
            </div>
          </div>
        </div>
      </div>

      <div class="section mt-6">
        <div class="section-header">
          <h3 class="section-title">🎬 为你推荐</h3>
        </div>
        <div class="media-list">
          <div v-for="media in recommendMedia" :key="media.id" class="media-card">
            <div class="media-cover">{{ media.cover }}</div>
            <h4 class="media-title">{{ media.title }}</h4>
          </div>
        </div>
      </div>

      <div class="section mt-6">
        <div class="section-header">
          <h3 class="section-title">🏆 家庭排行榜</h3>
        </div>
        <div class="rank-list">
          <div v-for="(member, index) in rankList" :key="member.id" class="rank-item">
            <span class="rank-number" :class="`rank-${index + 1}`">
              {{ index + 1 === 1 ? '🥇' : index + 1 === 2 ? '🥈' : index + 1 === 3 ? '🥉' : index + 1 }}
            </span>
            <span class="avatar">{{ member.avatar }}</span>
            <span class="name">{{ member.name }}</span>
            <span class="score">{{ member.score }}分</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'
import { useFamilyStore } from '@/stores/family'

const router = useRouter()
const userStore = useUserStore()
const familyStore = useFamilyStore()

const userName = computed(() => userStore.userInfo.name || '用户')

const todayEvents = ref([
  { id: 1, time: '14:00', title: '家庭游戏下午茶', members: '👨👩👦 全家参与', color: '#ff6b35', typeIcon: '🎮' },
  { id: 2, time: '19:30', title: '电影之夜', members: '👨👩 3人参与', color: '#ff6b81', typeIcon: '🎬' }
])

const recommendMedia = ref([
  { id: 1, title: '寻梦环游记', cover: '🎬' },
  { id: 2, title: '哪吒之魔童降世', cover: '🎬' },
  { id: 3, title: '疯狂动物城', cover: '🎬' }
])

const rankList = ref([
  { id: 1, name: '爸爸', avatar: '👨', score: 2350 },
  { id: 2, name: '小明', avatar: '👦', score: 1890 },
  { id: 3, name: '妈妈', avatar: '👩', score: 1650 },
  { id: 4, name: '爷爷', avatar: '👴', score: 980 }
])

const goToNotifications = () => router.push('/notifications')
const goToProfile = () => router.push('/profile')
const goToGameHall = () => router.push('/game-hall')
const goToCalendar = () => router.push('/main/calendar')
</script>

<style lang="scss" scoped>
.home-page {
  min-height: 100vh;
  background: #f8f9fa;
}

.header {
  background: #fff;
  padding: 16px 16px 24px;
  display: flex;
  justify-content: space-between;
  align-items: flex-start;

  .greeting {
    .greet-text {
      font-size: 20px;
      font-weight: 600;
      color: #2d3436;
      margin-bottom: 4px;
    }

    .sub-text {
      font-size: 13px;
      color: #636e72;
    }
  }

  .header-actions {
    display: flex;
    gap: 12px;

    .action-btn {
      width: 40px;
      height: 40px;
      border: none;
      background: #f8f9fa;
      border-radius: 50%;
      font-size: 20px;
      cursor: pointer;
    }
  }
}

.container {
  padding: 0 16px;
}

.quick-start-card {
  margin-top: 16px;
  background: linear-gradient(135deg, #ff6b35 0%, #ff8f65 100%);
  border-radius: 16px;
  padding: 20px;
  cursor: pointer;

  .card-content {
    display: flex;
    align-items: center;
    gap: 16px;

    .icon-wrapper {
      .icon {
        font-size: 40px;
      }
    }

    .text-wrapper {
      flex: 1;

      .title {
        font-size: 18px;
        font-weight: 600;
        color: #fff;
        margin-bottom: 4px;
      }

      .desc {
        font-size: 13px;
        color: rgba(255, 255, 255, 0.85);
      }
    }

    .start-btn {
      background: #fff;
      color: #ff6b35;
      font-size: 13px;
      padding: 6px 16px;
    }
  }
}

.section {
  .section-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 16px;

    .section-title {
      font-size: 18px;
      font-weight: 600;
      color: #2d3436;
    }

    .more-btn {
      font-size: 13px;
      color: #636e72;
      background: none;
      border: none;
      cursor: pointer;
    }
  }
}

.event-list {
  display: flex;
  flex-direction: column;
  gap: 12px;

  .event-card {
    background: #fff;
    border-radius: 12px;
    padding: 16px;
    display: flex;
    align-items: center;
    gap: 16px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);

    .event-time {
      .time {
        font-size: 15px;
        font-weight: 600;
        color: #2d3436;
      }
    }

    .event-info {
      flex: 1;

      .event-title {
        font-size: 15px;
        font-weight: 500;
        color: #2d3436;
        margin-bottom: 4px;
      }

      .event-members {
        font-size: 13px;
        color: #636e72;
      }
    }

    .event-type {
      width: 40px;
      height: 40px;
      border-radius: 50%;
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: 20px;
    }
  }
}

.media-list {
  display: flex;
  gap: 12px;
  overflow-x: auto;

  .media-card {
    flex-shrink: 0;
    width: 120px;

    .media-cover {
      width: 120px;
      height: 160px;
      background: #fff;
      border-radius: 12px;
      display: flex;
      align-items: center;
      justify-content: center;
      font-size: 48px;
      box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
      margin-bottom: 8px;
    }

    .media-title {
      font-size: 13px;
      color: #2d3436;
      overflow: hidden;
      text-overflow: ellipsis;
      white-space: nowrap;
    }
  }
}

.rank-list {
  background: #fff;
  border-radius: 12px;
  overflow: hidden;

  .rank-item {
    display: flex;
    align-items: center;
    padding: 16px;
    gap: 12px;

    &:not(:last-child) {
      border-bottom: 1px solid #f1f3f5;
    }

    .rank-number {
      width: 24px;
      text-align: center;
      font-size: 15px;
      font-weight: 600;
    }

    .avatar {
      font-size: 28px;
    }

    .name {
      flex: 1;
      font-size: 15px;
      color: #2d3436;
    }

    .score {
      font-size: 15px;
      font-weight: 600;
      color: #ff6b35;
    }
  }
}
</style>