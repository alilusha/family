<template>
  <div class="calendar-page">
    <div class="header">
      <h2 class="title">📅 家庭日历</h2>
      <button class="add-btn" @click="goToCreate">+</button>
    </div>

    <div class="container">
      <div class="calendar-header">
        <button class="nav-btn" @click="prevMonth">‹</button>
        <h3 class="month-title">{{ currentMonth }}</h3>
        <button class="nav-btn" @click="nextMonth">›</button>
      </div>

      <div class="weekdays">
        <div v-for="day in weekdays" :key="day" class="weekday">{{ day }}</div>
      </div>

      <div class="calendar-grid">
        <div v-for="(date, index) in calendarDays" :key="index" class="calendar-day" :class="{ today: date.isToday, selected: date.isSelected, hasEvent: date.hasEvent }" @click="selectDate(date)">
          {{ date.day }}
          <div v-if="date.hasEvent" class="event-dot" :style="{ background: date.eventColor }"></div>
        </div>
      </div>

      <div class="section mt-6">
        <h3 class="section-title">今日活动 · {{ selectedDateStr }}</h3>
        <div class="event-list">
          <div v-for="event in todayEvents" :key="event.id" class="event-card">
            <div class="event-time-bar" :style="{ background: event.color }"></div>
            <div class="event-content">
              <div class="event-time">{{ event.time }}</div>
              <h4 class="event-name">{{ event.name }}</h4>
              <div class="event-members">{{ event.members }}</div>
            </div>
            <div class="event-icon">{{ event.icon }}</div>
          </div>
        </div>
        <div v-if="todayEvents.length === 0" class="empty-state">
          暂无活动
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const currentDate = ref(new Date())
const selectedDate = ref(new Date())

const weekdays = ['日', '一', '二', '三', '四', '五', '六']

const currentMonth = computed(() => {
  return `${currentDate.value.getFullYear()}年${currentDate.value.getMonth() + 1}月`
})

const selectedDateStr = computed(() => {
  return `${selectedDate.value.getMonth() + 1}月${selectedDate.value.getDate()}日`
})

const calendarDays = computed(() => {
  const year = currentDate.value.getFullYear()
  const month = currentDate.value.getMonth()
  const firstDay = new Date(year, month, 1)
  const lastDay = new Date(year, month + 1, 0)
  const days = []

  for (let i = 0; i < firstDay.getDay(); i++) {
    days.push({ day: '', isToday: false, isSelected: false, hasEvent: false })
  }

  const today = new Date()
  for (let i = 1; i <= lastDay.getDate(); i++) {
    const date = new Date(year, month, i)
    const isToday = date.toDateString() === today.toDateString()
    const isSelected = date.toDateString() === selectedDate.value.toDateString()
    const hasEvent = [1, 5, 15, 20].includes(i)
    const eventColor = i === 1 ? '#ff6b35' : i === 5 ? '#ff6b81' : i === 15 ? '#6c5ce7' : '#00b894'
    days.push({ day: i, isToday, isSelected, hasEvent, eventColor })
  }

  return days
})

const todayEvents = ref([
  { id: 1, time: '14:00-16:00', name: '家庭游戏下午茶', members: '👨👩👦 全家参与', icon: '🎮', color: '#ff6b35' },
  { id: 2, time: '19:30-21:30', name: '电影之夜', members: '👨👩 3人参与', icon: '🎬', color: '#ff6b81' }
])

const prevMonth = () => {
  currentDate.value = new Date(currentDate.value.getFullYear(), currentDate.value.getMonth() - 1)
}

const nextMonth = () => {
  currentDate.value = new Date(currentDate.value.getFullYear(), currentDate.value.getMonth() + 1)
}

const selectDate = (date) => {
  if (!date.day) return
  selectedDate.value = new Date(currentDate.value.getFullYear(), currentDate.value.getMonth(), date.day)
}

const goToCreate = () => router.push('/create-event')
</script>

<style lang="scss" scoped>
.calendar-page {
  min-height: 100vh;
  background: #f8f9fa;
}

.header {
  background: #fff;
  padding: 16px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #f1f3f5;
}

.title {
  font-size: 20px;
  font-weight: 600;
  color: #2d3436;
}

.add-btn {
  width: 40px;
  height: 40px;
  border: none;
  background: #00b894;
  border-radius: 50%;
  font-size: 24px;
  color: #fff;
  cursor: pointer;
}

.container {
  padding: 16px;
}

.calendar-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 16px;

  .nav-btn {
    width: 40px;
    height: 40px;
    border: none;
    background: #fff;
    border-radius: 50%;
    font-size: 20px;
    cursor: pointer;
  }

  .month-title {
    font-size: 18px;
    font-weight: 600;
    color: #2d3436;
  }
}

.weekdays {
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  gap: 4px;
  margin-bottom: 8px;

  .weekday {
    text-align: center;
    font-size: 13px;
    color: #b2bec3;
    padding: 8px 0;
  }
}

.calendar-grid {
  display: grid;
  grid-template-columns: repeat(7, 1fr);
  gap: 4px;
  background: #fff;
  border-radius: 16px;
  padding: 16px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
}

.calendar-day {
  aspect-ratio: 1;
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  border-radius: 50%;
  font-size: 15px;
  color: #2d3436;
  cursor: pointer;
  position: relative;

  &.today {
    background: #ff6b35;
    color: #fff;
    font-weight: 600;
  }

  &.selected {
    background: #00b894;
    color: #fff;
    font-weight: 600;
  }

  .event-dot {
    width: 6px;
    height: 6px;
    border-radius: 50%;
    margin-top: 4px;
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

.event-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.event-card {
  background: #fff;
  border-radius: 12px;
  padding: 16px;
  display: flex;
  align-items: center;
  gap: 16px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);

  .event-time-bar {
    width: 4px;
    height: 60px;
    border-radius: 2px;
  }

  .event-content {
    flex: 1;

    .event-time {
      font-size: 13px;
      color: #636e72;
      margin-bottom: 4px;
    }

    .event-name {
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

  .event-icon {
    font-size: 32px;
  }
}

.empty-state {
  text-align: center;
  padding: 40px;
  color: #b2bec3;
}
</style>