<template>
  <div class="video-player-page">
    <div class="header">
      <button class="back-btn" @click="goBack">‹</button>
      <h2 class="title">视频播放</h2>
    </div>

    <div class="video-container">
      <div class="video-placeholder">
        <span class="play-icon">▶</span>
        <span class="video-title">寻梦环游记</span>
      </div>
      <div class="video-controls">
        <div class="progress-bar">
          <div class="progress-fill" :style="{ width: `${progress}%` }"></div>
          <div class="progress-thumb" :style="{ left: `${progress}%` }"></div>
        </div>
        <div class="control-buttons">
          <button class="control-btn" @click="togglePlay">
            {{ isPlaying ? '⏸️' : '▶️' }}
          </button>
          <span class="time-display">{{ formatTime(currentTime) }} / {{ formatTime(duration) }}</span>
          <div class="right-controls">
            <button class="control-btn">🔊</button>
            <button class="control-btn">🔄</button>
            <button class="control-btn" @click="toggleFullscreen">⛶</button>
          </div>
        </div>
      </div>
    </div>

    <div class="container">
      <div class="video-info">
        <h3 class="video-title">寻梦环游记</h3>
        <div class="video-meta">
          <span class="rating">⭐ 9.2</span>
          <span class="year">2017</span>
          <span class="category">动画/家庭</span>
        </div>
        <p class="video-desc">
          讲述了一个关于梦想与家庭的温暖故事，男孩米格尔在亡灵节意外进入了另一个世界，展开了一段奇妙的冒险。
        </p>
      </div>

      <div class="family-viewers">
        <h4 class="section-title">👨‍👩‍👧‍👦 家人观看</h4>
        <div class="viewer-list">
          <div v-for="viewer in viewers" :key="viewer.id" class="viewer-item">
            <span class="viewer-avatar">{{ viewer.avatar }}</span>
            <span class="viewer-name">{{ viewer.name }}</span>
            <span class="viewer-status">{{ viewer.status }}</span>
          </div>
        </div>
      </div>

      <div class="add-playlist">
        <button class="btn btn-primary" @click="addToPlaylist">+ 添加到播放列表</button>
      </div>

      <div class="section mt-6">
        <h3 class="section-title">相关推荐</h3>
        <div class="content-grid">
          <div v-for="item in recommended" :key="item.id" class="content-card" @click="playVideo(item)">
            <div class="content-cover">{{ item.cover }}</div>
            <h4 class="content-title">{{ item.title }}</h4>
            <div class="content-meta">
              <span class="rating">{{ item.rating }}</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const isPlaying = ref(false)
const currentTime = ref(0)
const duration = ref(5400) // 90分钟
const progress = ref(0)
let timer = null

const viewers = ref([
  { id: 1, avatar: '👨', name: '爸爸', status: '正在看' },
  { id: 2, avatar: '👩', name: '妈妈', status: '正在看' },
  { id: 3, avatar: '👦', name: '小明', status: '正在看' }
])

const recommended = ref([
  { id: 1, title: '哪吒之魔童降世', cover: '🎬', rating: '⭐ 8.8' },
  { id: 2, title: '疯狂动物城', cover: '🎬', rating: '⭐ 9.0' },
  { id: 3, title: '冰雪奇缘', cover: '🎬', rating: '⭐ 8.6' }
])

const formatTime = (seconds) => {
  const h = Math.floor(seconds / 3600)
  const m = Math.floor((seconds % 3600) / 60)
  const s = seconds % 60
  return `${h.toString().padStart(2, '0')}:${m.toString().padStart(2, '0')}:${s.toString().padStart(2, '0')}`
}

const togglePlay = () => {
  isPlaying.value = !isPlaying.value
}

const toggleFullscreen = () => {
  if (!document.fullscreenElement) {
    document.documentElement.requestFullscreen()
  } else {
    document.exitFullscreen()
  }
}

const addToPlaylist = () => {
  alert('已添加到播放列表！')
}

const playVideo = (item) => {
  alert(`即将播放：${item.title}`)
}

const goBack = () => router.back()

onMounted(() => {
  timer = setInterval(() => {
    if (isPlaying.value && currentTime.value < duration.value) {
      currentTime.value++
      progress.value = (currentTime.value / duration.value) * 100
    }
  }, 1000)
})

onUnmounted(() => {
  if (timer) clearInterval(timer)
})
</script>

<style lang="scss" scoped>
.video-player-page {
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

.video-container {
  background: #000;
  position: relative;
}

.video-placeholder {
  width: 100%;
  height: 220px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  gap: 12px;
}

.play-icon {
  width: 64px;
  height: 64px;
  background: rgba(255, 255, 255, 0.9);
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 24px;
  cursor: pointer;
  padding-left: 8px;
}

.video-title {
  color: #fff;
  font-size: 18px;
  font-weight: 600;
}

.video-controls {
  background: linear-gradient(to top, rgba(0, 0, 0, 0.8), transparent);
  padding: 16px;
}

.progress-bar {
  position: relative;
  height: 4px;
  background: rgba(255, 255, 255, 0.3);
  border-radius: 2px;
  margin-bottom: 12px;
  cursor: pointer;
}

.progress-fill {
  height: 100%;
  background: #ff6b35;
  border-radius: 2px;
}

.progress-thumb {
  position: absolute;
  top: 50%;
  transform: translate(-50%, -50%);
  width: 12px;
  height: 12px;
  background: #ff6b35;
  border-radius: 50%;
}

.control-buttons {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.control-btn {
  border: none;
  background: none;
  color: #fff;
  font-size: 20px;
  cursor: pointer;
  padding: 4px;
}

.time-display {
  color: #fff;
  font-size: 13px;
}

.right-controls {
  display: flex;
  gap: 16px;
}

.container {
  padding: 16px;
}

.video-info {
  background: #fff;
  border-radius: 16px;
  padding: 20px;
  margin-bottom: 16px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
}

.video-title {
  font-size: 20px;
  font-weight: 600;
  color: #2d3436;
  margin-bottom: 8px;
}

.video-meta {
  display: flex;
  gap: 12px;
  margin-bottom: 12px;
}

.rating {
  color: #ffd93d;
  font-weight: 500;
}

.year, .category {
  color: #636e72;
}

.video-desc {
  color: #636e72;
  font-size: 14px;
  line-height: 1.6;
}

.family-viewers {
  background: #fff;
  border-radius: 16px;
  padding: 16px;
  margin-bottom: 16px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
}

.section-title {
  font-size: 16px;
  font-weight: 600;
  color: #2d3436;
  margin-bottom: 12px;
}

.viewer-list {
  display: flex;
  gap: 16px;
}

.viewer-item {
  text-align: center;
}

.viewer-avatar {
  display: block;
  font-size: 32px;
  margin-bottom: 4px;
}

.viewer-name {
  display: block;
  font-size: 13px;
  color: #2d3436;
  margin-bottom: 2px;
}

.viewer-status {
  display: block;
  font-size: 11px;
  color: #00b894;
}

.add-playlist {
  margin-bottom: 24px;
}

.content-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 12px;
}

.content-card {
  cursor: pointer;
}

.content-cover {
  aspect-ratio: 3/4;
  background: #fff;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 28px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  margin-bottom: 8px;
}

.content-title {
  font-size: 13px;
  color: #2d3436;
  margin-bottom: 4px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.content-meta {
  .rating {
    font-size: 11px;
  }
}
</style>
