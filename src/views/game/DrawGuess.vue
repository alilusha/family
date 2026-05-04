<template>
  <div class="draw-guess-page">
    <div class="header">
      <button class="back-btn" @click="goBack">‹</button>
      <h2 class="title">你画我猜</h2>
    </div>

    <div class="container">
      <div class="word-display">
        <div class="word-text">🐱 小猫</div>
        <div class="timer">{{ timeLeft }}s</div>
      </div>

      <div class="canvas-container">
        <canvas 
          ref="canvas" 
          class="draw-canvas"
          @mousedown="startDraw"
          @mousemove="draw"
          @mouseup="stopDraw"
          @mouseleave="stopDraw"
          @touchstart="startDraw"
          @touchmove="draw"
          @touchend="stopDraw"
        ></canvas>
      </div>

      <div class="toolbar">
        <div class="brush-sizes">
          <button class="size-btn" v-for="size in [8, 12, 16]" :key="size" :class="{ active: brushSize === size }" @click="brushSize = size">{{ size }}</button>
        </div>
        <div class="colors">
          <button class="color-btn" v-for="color in colors" :key="color" :style="{ background: color }" :class="{ active: currentColor === color }" @click="currentColor = color"></button>
        </div>
        <div class="tools">
          <button class="tool-btn" @click="clearCanvas">🗑️</button>
        </div>
      </div>

      <div class="guess-section">
        <div class="guess-list">
          <div class="guess-item" v-for="(guess, index) in guesses" :key="index">
            <span class="guess-player">{{ guess.player }}</span>: <span class="guess-text">{{ guess.text }}</span> <span v-if="guess.correct" class="correct-badge">✓正确!</span>
          </div>
        </div>
        <div class="guess-input-container">
          <input v-model="currentGuess" class="guess-input" placeholder="输入你的猜测..." @keyup.enter="submitGuess">
          <button class="btn btn-primary" @click="submitGuess">发送</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()
const canvas = ref(null)
let ctx = null
const isDrawing = ref(false)
const brushSize = ref(12)
const currentColor = ref('#2d3436')
const colors = ['#2d3436', '#e17055', '#ff6b35', '#ffd93d', '#00b894', '#74b9ff', '#6c5ce7', '#ff6b81']
const timeLeft = ref(60)
let timer = null
const currentGuess = ref('')
const guesses = ref([
  { player: '爸爸', text: '狗', correct: false },
  { player: '妈妈', text: '兔子', correct: false }
])

const initCanvas = () => {
  const el = canvas.value
  if (!el) return
  const rect = el.getBoundingClientRect()
  el.width = rect.width
  el.height = rect.width * 0.6
  ctx = el.getContext('2d')
  ctx.lineCap = 'round'
  ctx.lineJoin = 'round'
}

const startDraw = (e) => {
  isDrawing.value = true
  draw(e)
}

const draw = (e) => {
  if (!isDrawing.value || !ctx) return
  const rect = canvas.value.getBoundingClientRect()
  const x = (e.clientX || e.touches?.[0]?.clientX) - rect.left
  const y = (e.clientY || e.touches?.[0]?.clientY) - rect.top
  ctx.lineWidth = brushSize.value
  ctx.strokeStyle = currentColor.value
  ctx.lineTo(x, y)
  ctx.stroke()
  ctx.beginPath()
  ctx.moveTo(x, y)
}

const stopDraw = () => {
  isDrawing.value = false
  ctx.beginPath()
}

const clearCanvas = () => {
  if (!ctx) return
  ctx.clearRect(0, 0, canvas.value.width, canvas.value.height)
}

const submitGuess = () => {
  if (!currentGuess.value.trim()) return
  guesses.value.push({
    player: '小明',
    text: currentGuess.value,
    correct: currentGuess.value.toLowerCase().includes('猫')
  })
  if (currentGuess.value.toLowerCase().includes('猫')) {
    clearInterval(timer)
    setTimeout(() => alert('恭喜！猜中了！'), 300)
  }
  currentGuess.value = ''
}

const goBack = () => router.back()

onMounted(() => {
  initCanvas()
  timer = setInterval(() => {
    if (timeLeft.value > 0) {
      timeLeft.value--
    } else {
      clearInterval(timer)
    }
  }, 1000)
})

onUnmounted(() => {
  clearInterval(timer)
})
</script>

<style lang="scss" scoped>
.draw-guess-page {
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

.word-display {
  background: #fff;
  border-radius: 12px;
  padding: 16px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  margin-bottom: 16px;

  .word-text {
    font-size: 18px;
    font-weight: 600;
    color: #ff6b35;
  }

  .timer {
    font-size: 18px;
    font-weight: 700;
    color: #e17055;
  }
}

.canvas-container {
  background: #fff;
  border-radius: 12px;
  padding: 8px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  margin-bottom: 16px;
}

.draw-canvas {
  width: 100%;
  display: block;
  border-radius: 8px;
  border: 1px solid #e0e0e0;
}

.toolbar {
  background: #fff;
  border-radius: 12px;
  padding: 16px;
  display: flex;
  gap: 16px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  margin-bottom: 16px;
}

.brush-sizes, .colors, .tools {
  display: flex;
  gap: 8px;
  align-items: center;
}

.size-btn {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  border: 2px solid #e0e0e0;
  background: #fff;
  font-weight: 600;
  cursor: pointer;

  &.active {
    border-color: #ff6b35;
    background: #ffe8dd;
    color: #ff6b35;
  }
}

.color-btn {
  width: 32px;
  height: 32px;
  border-radius: 50%;
  border: 2px solid transparent;
  cursor: pointer;

  &.active {
    border-color: #2d3436;
  }
}

.tool-btn {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  border: none;
  background: #f8f9fa;
  font-size: 20px;
  cursor: pointer;
}

.guess-section {
  background: #fff;
  border-radius: 12px;
  padding: 16px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
}

.guess-list {
  max-height: 150px;
  overflow-y: auto;
  margin-bottom: 16px;
}

.guess-item {
  padding: 8px 0;
  font-size: 14px;
  border-bottom: 1px solid #f1f3f5;

  &:last-child {
    border-bottom: none;
  }

  .guess-player {
    color: #6c5ce7;
    font-weight: 500;
  }

  .guess-text {
    color: #2d3436;
  }

  .correct-badge {
    color: #00b894;
    font-weight: 600;
  }
}

.guess-input-container {
  display: flex;
  gap: 12px;
}

.guess-input {
  flex: 1;
}
</style>