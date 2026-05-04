<template>
  <div class="game-room-page">
    <div class="header">
      <button class="back-btn" @click="goBack">‹</button>
      <h2 class="title">你画我猜</h2>
    </div>

    <div class="container">
      <div class="room-info">
        <div class="room-code">房间号: 385726</div>
        <div class="room-actions">
          <button class="btn btn-outline btn-small">复制</button>
          <button class="btn btn-outline btn-small">分享</button>
        </div>
      </div>

      <div class="players-grid">
        <div v-for="player in players" :key="player.id" class="player-card">
          <div class="player-avatar">{{ player.avatar }}</div>
          <div class="player-name">{{ player.name }}</div>
          <div class="player-status" v-if="player.isReady">已准备</div>
        </div>
        <div class="player-card add-player">
          <button class="btn btn-outline btn-small">+邀请</button>
        </div>
      </div>

      <div class="game-settings">
        <div class="setting-item">
          <span class="setting-label">局数:</span>
          <div class="setting-options">
            <button class="btn btn-outline btn-small active">8</button>
            <button class="btn btn-outline btn-small">10</button>
            <button class="btn btn-outline btn-small">12</button>
          </div>
        </div>
        <div class="setting-item">
          <span class="setting-label">难度:</span>
          <div class="setting-options">
            <button class="btn btn-outline btn-small">简单</button>
            <button class="btn btn-outline btn-small active">普通</button>
            <button class="btn btn-outline btn-small">困难</button>
          </div>
        </div>
      </div>

      <button class="btn btn-primary start-btn" @click="startGame">开始游戏</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const players = ref([
  { id: 1, name: '爸爸', avatar: '👨', isReady: true },
  { id: 2, name: '妈妈', avatar: '👩', isReady: true },
  { id: 3, name: '小明', avatar: '👦', isReady: false }
])

const goBack = () => router.back()
const startGame = () => router.push('/draw-guess')
</script>

<style lang="scss" scoped>
.game-room-page {
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

.room-info {
  background: #fff;
  border-radius: 12px;
  padding: 16px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  margin-bottom: 24px;

  .room-code {
    font-size: 15px;
    color: #2d3436;
  }
}

.players-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 12px;
  margin-bottom: 24px;
}

.player-card {
  background: #fff;
  border-radius: 12px;
  padding: 16px 8px;
  text-align: center;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);

  .player-avatar {
    display: block;
    font-size: 32px;
    margin-bottom: 8px;
  }

  .player-name {
    display: block;
    font-size: 13px;
    color: #2d3436;
    margin-bottom: 8px;
  }

  .player-status {
    display: inline-block;
    font-size: 11px;
    padding: 2px 8px;
    background: #e0f7f5;
    color: #00b894;
    border-radius: 999px;
  }

  &.add-player {
    display: flex;
    align-items: center;
    justify-content: center;
  }
}

.game-settings {
  background: #fff;
  border-radius: 12px;
  padding: 16px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  margin-bottom: 24px;
}

.setting-item {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 12px;

  &:last-child {
    margin-bottom: 0;
  }

  .setting-label {
    font-size: 15px;
    color: #2d3436;
  }
}

.setting-options {
  display: flex;
  gap: 8px;

  .btn.active {
    background: #ff6b35;
    color: #fff;
  }
}

.start-btn {
  width: 100%;
  height: 48px;
}
</style>