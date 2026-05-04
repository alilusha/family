<template>
  <div class="game-hall-page">
    <div class="header">
      <button class="back-btn" @click="goBack">‹</button>
      <h2 class="title">游戏大厅</h2>
    </div>

    <div class="container">
      <button class="btn btn-primary create-room-btn" @click="createRoom">
        + 创建新房间
      </button>

      <div class="section mt-6">
        <h3 class="section-title">可加入的房间</h3>
        <div class="room-list">
          <div v-for="room in rooms" :key="room.id" class="room-card" @click="joinRoom(room)">
            <div class="room-icon">{{ room.icon }}</div>
            <div class="room-info">
              <h4 class="room-name">{{ room.name }}</h4>
              <p class="room-players">{{ room.playerCount }}/{{ room.maxPlayers }}人</p>
            </div>
            <div class="room-status" :class="room.status">
              {{ room.statusText }}
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const rooms = ref([
  { id: 1, name: '家庭问答大挑战', icon: '🧠', playerCount: 3, maxPlayers: 6, status: 'waiting', statusText: '加入' },
  { id: 2, name: '你画我猜', icon: '🎨', playerCount: 2, maxPlayers: 8, status: 'playing', statusText: '围观' }
])

const goBack = () => router.back()
const createRoom = () => router.push('/game-room')
const joinRoom = (room) => router.push('/quiz-game')
</script>

<style lang="scss" scoped>
.game-hall-page {
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

.create-room-btn {
  width: 100%;
  height: 48px;
}

.section {
  .section-title {
    font-size: 16px;
    font-weight: 600;
    color: #2d3436;
    margin-bottom: 16px;
  }
}

.room-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.room-card {
  background: #fff;
  border-radius: 12px;
  padding: 16px;
  display: flex;
  align-items: center;
  gap: 16px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  cursor: pointer;

  .room-icon {
    font-size: 36px;
  }

  .room-info {
    flex: 1;

    .room-name {
      font-size: 15px;
      font-weight: 500;
      color: #2d3436;
      margin-bottom: 4px;
    }

    .room-players {
      font-size: 13px;
      color: #636e72;
    }
  }

  .room-status {
    padding: 6px 16px;
    border-radius: 999px;
    font-size: 13px;
    font-weight: 500;

    &.waiting {
      background: #e0f7f5;
      color: #00b894;
    }

    &.playing {
      background: #ffe8dd;
      color: #ff6b35;
    }
  }
}
</style>