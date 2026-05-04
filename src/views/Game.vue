<template>
  <div class="game-page">
    <div class="header">
      <h2 class="title">🎮 游戏大厅</h2>
    </div>

    <div class="container">
      <button class="btn btn-primary create-room-btn" @click="goToGameHall">
        + 创建新房间
      </button>

      <div class="section mt-6">
        <h3 class="section-title">正在进行的房间</h3>
        <div class="room-list">
          <div v-for="room in activeRooms" :key="room.id" class="room-card" @click="goToRoom(room)">
            <div class="room-icon">{{ room.icon }}</div>
            <div class="room-info">
              <h4 class="room-name">{{ room.name }}</h4>
              <p class="room-players">👤 {{ room.players.join(' ') }} · {{ room.playerCount }}/{{ room.maxPlayers }}人</p>
            </div>
            <div class="room-status" :class="room.status">
              {{ room.statusText }}
            </div>
          </div>
        </div>
      </div>

      <div class="section mt-6">
        <h3 class="section-title">选择游戏开始</h3>
        <div class="game-grid">
          <div v-for="game in games" :key="game.id" class="game-card" @click="startGame(game)">
            <div class="game-icon">{{ game.icon }}</div>
            <h4 class="game-name">{{ game.name }}</h4>
            <p class="game-desc">{{ game.desc }}</p>
            <button class="btn btn-small start-game-btn" :style="{ background: game.color }">
              开始
            </button>
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

const activeRooms = ref([
  { id: 1, name: '家庭问答大挑战', icon: '🧠', players: ['👨', '👩', '👦'], playerCount: 3, maxPlayers: 6, status: 'playing', statusText: '围观' },
  { id: 2, name: '你画我猜', icon: '🎨', players: ['👨', '👩'], playerCount: 2, maxPlayers: 8, status: 'waiting', statusText: '加入' }
])

const games = ref([
  { id: 1, name: '问答大挑战', icon: '🧠', desc: '2-8人', color: '#6c5ce7', route: '/quiz-game' },
  { id: 2, name: '你画我猜', icon: '🎨', desc: '2-8人', color: '#ff6b35', route: '/draw-guess' },
  { id: 3, name: '棋类对战', icon: '♟️', desc: '2人', color: '#00b894', route: '/game-hall' },
  { id: 4, name: '更多游戏', icon: '🎲', desc: '即将推出', color: '#dfe6e9', route: '/game-hall' }
])

const goToGameHall = () => router.push('/game-hall')
const goToRoom = (room) => router.push('/game-room')
const startGame = (game) => router.push(game.route)
</script>

<style lang="scss" scoped>
.game-page {
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
      font-weight: 600;
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

    &.playing {
      background: #ffe8dd;
      color: #ff6b35;
    }

    &.waiting {
      background: #e0f7f5;
      color: #00b894;
    }
  }
}

.game-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 12px;
}

.game-card {
  background: #fff;
  border-radius: 16px;
  padding: 20px;
  text-align: center;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  cursor: pointer;

  .game-icon {
    font-size: 48px;
    margin-bottom: 12px;
  }

  .game-name {
    font-size: 16px;
    font-weight: 600;
    color: #2d3436;
    margin-bottom: 4px;
  }

  .game-desc {
    font-size: 13px;
    color: #636e72;
    margin-bottom: 16px;
  }

  .start-game-btn {
    width: 100%;
    border: none;
    color: #fff;
  }
}
</style>