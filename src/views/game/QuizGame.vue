<template>
  <div class="quiz-game-page">
    <div class="game-header">
      <div class="timer">{{ timeLeft }}s</div>
      <div class="progress">第 {{ currentQuestion + 1 }}/{{ questions.length }}题</div>
    </div>

    <div class="progress-bar">
      <div class="progress-fill" :style="{ width: `${((currentQuestion + 1) / questions.length) * 100}%` }"></div>
    </div>

    <div class="container">
      <div class="question-card">
        <h3 class="question-text">{{ questions[currentQuestion]?.text }}</h3>
      </div>

      <div class="options-list">
        <div v-for="(option, index) in questions[currentQuestion]?.options" :key="index" class="option-item" :class="{ selected: selectedOption === index, correct: showAnswer && index === questions[currentQuestion].correct, wrong: showAnswer && selectedOption === index && index !== questions[currentQuestion].correct }" @click="selectOption(index)">
          <span class="option-letter">{{ String.fromCharCode(65 + index) }}</span>
          <span class="option-text">{{ option }}</span>
        </div>
      </div>

      <div class="players-status">
        <div v-for="player in players" :key="player.id" class="player-item">
          <span class="player-avatar">{{ player.avatar }}</span>
          <span class="player-name">{{ player.name }}</span>
          <span class="player-score">{{ player.score }}分</span>
        </div>
      </div>
    </div>

    <div v-if="showResult" class="result-overlay">
      <div class="result-card">
        <h2 class="result-title">游戏结束！</h2>
        <div class="rank-list">
          <div v-for="(player, index) in sortedPlayers" :key="player.id" class="rank-item">
            <span class="rank-number">{{ index === 0 ? '🥇' : index === 1 ? '🥈' : index === 2 ? '🥉' : index + 1 }}</span>
            <span class="rank-avatar">{{ player.avatar }}</span>
            <span class="rank-name">{{ player.name }}</span>
            <span class="rank-score">{{ player.score }}分</span>
          </div>
        </div>
        <div class="result-actions">
          <button class="btn btn-outline" @click="goBack">返回大厅</button>
          <button class="btn btn-primary" @click="restartGame">再来一局</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed, onMounted, onUnmounted } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const currentQuestion = ref(0)
const selectedOption = ref(null)
const showAnswer = ref(false)
const showResult = ref(false)
const timeLeft = ref(15)
let timer = null

const questions = ref([
  { text: '中国的首都是哪里？', options: ['上海', '北京', '广州', '深圳'], correct: 1 },
  { text: '一年有多少个月份？', options: ['10个', '11个', '12个', '13个'], correct: 2 },
  { text: '水的沸点是多少度？', options: ['90°C', '95°C', '100°C', '105°C'], correct: 2 },
  { text: '地球是什么形状？', options: ['圆形', '方形', '三角形', '椭圆形'], correct: 3 },
  { text: '彩虹有几种颜色？', options: ['5种', '6种', '7种', '8种'], correct: 2 }
])

const players = ref([
  { id: 1, name: '爸爸', avatar: '👨', score: 2350, answered: false },
  { id: 2, name: '妈妈', avatar: '👩', score: 2100, answered: false },
  { id: 3, name: '小明', avatar: '👦', score: 1890, answered: false }
])

const sortedPlayers = computed(() => [...players.value].sort((a, b) => b.score - a.score))

const selectOption = (index) => {
  if (showAnswer) return
  selectedOption.value = index
  showAnswer.value = true
  if (index === questions.value[currentQuestion.value].correct) {
    players.value[2].score += 100
  }
  clearInterval(timer)
  setTimeout(nextQuestion, 2000)
}

const nextQuestion = () => {
  if (currentQuestion.value < questions.value.length - 1) {
    currentQuestion.value++
    selectedOption.value = null
    showAnswer.value = false
    timeLeft.value = 15
    startTimer()
  } else {
    showResult.value = true
  }
}

const startTimer = () => {
  timer = setInterval(() => {
    if (timeLeft.value > 0) {
      timeLeft.value--
    } else {
      showAnswer.value = true
      clearInterval(timer)
      setTimeout(nextQuestion, 2000)
    }
  }, 1000)
}

const restartGame = () => {
  currentQuestion.value = 0
  selectedOption.value = null
  showAnswer.value = false
  showResult.value = false
  timeLeft.value = 15
  startTimer()
}

const goBack = () => router.push('/main/game')

onMounted(() => {
  startTimer()
})

onUnmounted(() => {
  clearInterval(timer)
})
</script>

<style lang="scss" scoped>
.quiz-game-page {
  min-height: 100vh;
  background: linear-gradient(180deg, #f0edff 0%, #e8e4ff 100%);
  padding-bottom: 30px;
}

.game-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px;
  background: rgba(255, 255, 255, 0.9);
}

.timer {
  width: 48px;
  height: 48px;
  border-radius: 50%;
  background: #6c5ce7;
  color: #fff;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 20px;
  font-weight: 600;
}

.progress {
  font-size: 15px;
  color: #636e72;
}

.progress-bar {
  width: 100%;
  height: 4px;
  background: #e0e0e0;
}

.progress-fill {
  height: 100%;
  background: #6c5ce7;
  transition: width 0.3s;
}

.container {
  padding: 16px;
}

.question-card {
  background: #fff;
  border-radius: 16px;
  padding: 24px;
  text-align: center;
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.12);
  margin-bottom: 24px;

  .question-text {
    font-size: 18px;
    font-weight: 600;
    color: #2d3436;
  }
}

.options-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
  margin-bottom: 24px;
}

.option-item {
  background: #fff;
  border-radius: 12px;
  padding: 16px;
  display: flex;
  align-items: center;
  gap: 16px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  cursor: pointer;
  border: 2px solid transparent;

  &.selected {
    border-color: #6c5ce7;
    background: #f0edff;
  }

  &.correct {
    border-color: #00b894;
    background: #e0f7f5;
  }

  &.wrong {
    border-color: #e17055;
    background: #fff5f5;
  }

  .option-letter {
    width: 36px;
    height: 36px;
    border-radius: 50%;
    background: #f8f9fa;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 15px;
    font-weight: 600;
    color: #636e72;
  }

  .option-text {
    flex: 1;
    font-size: 15px;
    color: #2d3436;
  }
}

.players-status {
  background: #fff;
  border-radius: 12px;
  padding: 16px;
  display: flex;
  justify-content: space-around;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
}

.player-item {
  text-align: center;

  .player-avatar {
    display: block;
    font-size: 32px;
    margin-bottom: 4px;
  }

  .player-name {
    display: block;
    font-size: 12px;
    color: #636e72;
    margin-bottom: 4px;
  }

  .player-score {
    display: block;
    font-size: 14px;
    font-weight: 600;
    color: #6c5ce7;
  }
}

.result-overlay {
  position: fixed;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  background: rgba(0, 0, 0, 0.7);
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 16px;
  z-index: 1000;
}

.result-card {
  background: #fff;
  border-radius: 24px;
  padding: 32px;
  width: 100%;
  max-width: 400px;
}

.result-title {
  font-size: 24px;
  font-weight: 700;
  color: #2d3436;
  text-align: center;
  margin-bottom: 24px;
}

.rank-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
  margin-bottom: 24px;
}

.rank-item {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 12px;
  background: #f8f9fa;
  border-radius: 12px;

  .rank-number {
    font-size: 20px;
    width: 32px;
    text-align: center;
  }

  .rank-avatar {
    font-size: 28px;
  }

  .rank-name {
    flex: 1;
    font-size: 15px;
    color: #2d3436;
  }

  .rank-score {
    font-size: 16px;
    font-weight: 600;
    color: #6c5ce7;
  }
}

.result-actions {
  display: flex;
  gap: 12px;
}
</style>