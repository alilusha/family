<template>
  <div class="main-container">
    <div class="main-content">
      <router-view></router-view>
    </div>

    <div class="tab-bar">
      <div
        v-for="tab in tabs"
        :key="tab.path"
        class="tab-item"
        :class="{ active: activeTab === tab.path }"
        @click="switchTab(tab.path)"
      >
        <span class="tab-icon">{{ tab.icon }}</span>
        <span class="tab-label">{{ tab.label }}</span>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'

const route = useRoute()
const router = useRouter()

const tabs = [
  { path: '/main/home', icon: '🏠', label: '首页' },
  { path: '/main/game', icon: '🎮', label: '游戏' },
  { path: '/main/media', icon: '🎬', label: '影音' },
  { path: '/main/calendar', icon: '📅', label: '规划' }
]

const activeTab = computed(() => route.path)

const switchTab = (path) => {
  router.push(path)
}
</script>

<style lang="scss" scoped>
.main-container {
  min-height: 100vh;
  background: #f8f9fa;
  padding-bottom: 60px;
}

.main-content {
  min-height: calc(100vh - 60px);
}

.tab-bar {
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  height: 60px;
  background: #fff;
  display: flex;
  border-top: 1px solid #dfe6e9;
  z-index: 100;

  .tab-item {
    flex: 1;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    cursor: pointer;
    transition: all 0.2s;

    .tab-icon {
      font-size: 24px;
      margin-bottom: 2px;
      transition: all 0.2s;
    }

    .tab-label {
      font-size: 12px;
      color: #b2bec3;
      transition: all 0.2s;
    }

    &.active {
      .tab-icon {
        transform: scale(1.1);
      }

      .tab-label {
        color: #ff6b35;
        font-weight: 500;
      }
    }
  }
}
</style>