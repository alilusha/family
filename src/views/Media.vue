<template>
  <div class="media-page">
    <div class="header">
      <h2 class="title">🎬 影音</h2>
      <button class="search-btn">🔍</button>
    </div>

    <div class="container">
      <div class="category-tabs">
        <div v-for="cat in categories" :key="cat.id" class="tab-item" :class="{ active: activeCategory === cat.id }" @click="activeCategory = cat.id">
          {{ cat.name }}
        </div>
      </div>

      <div class="section mt-6">
        <h3 class="section-title">🌟 今日推荐</h3>
        <div class="featured-card" @click="goToPlayer">
          <div class="featured-cover">🎬</div>
          <div class="featured-info">
            <h4 class="featured-title">寻梦环游记</h4>
            <div class="featured-meta">
              <span class="rating">⭐ 9.2</span>
              <span class="tag">动画</span>
              <span class="tag">家庭</span>
            </div>
          </div>
        </div>
      </div>

      <div class="section mt-6">
        <h3 class="section-title">🔥 热门内容</h3>
        <div class="content-grid">
          <div v-for="item in mediaList" :key="item.id" class="content-card" @click="goToPlayer">
            <div class="content-cover">{{ item.cover }}</div>
            <h4 class="content-title">{{ item.title }}</h4>
            <div class="content-meta">
              <span class="rating">{{ item.rating }}</span>
              <span class="tag">{{ item.tag }}</span>
            </div>
          </div>
        </div>
      </div>

      <div class="section mt-6">
        <h3 class="section-title">📋 共享播放列表</h3>
        <div class="playlist-list">
          <div v-for="list in playlists" :key="list.id" class="playlist-card">
            <div class="playlist-icon">{{ list.icon }}</div>
            <div class="playlist-info">
              <h4 class="playlist-name">{{ list.name }}</h4>
              <p class="playlist-count">{{ list.count }}首</p>
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

const activeCategory = ref(1)

const categories = ref([
  { id: 1, name: '全部' },
  { id: 2, name: '动画' },
  { id: 3, name: '电影' },
  { id: 4, name: '纪录片' },
  { id: 5, name: '音乐' }
])

const mediaList = ref([
  { id: 1, title: '哪吒之魔童降世', cover: '🎬', rating: '⭐ 8.8', tag: '动画' },
  { id: 2, title: '疯狂动物城', cover: '🎬', rating: '⭐ 9.0', tag: '动画' },
  { id: 3, title: '冰雪奇缘', cover: '🎬', rating: '⭐ 8.6', tag: '动画' },
  { id: 4, title: '寻梦环游记', cover: '🎬', rating: '⭐ 9.2', tag: '动画' },
  { id: 5, title: '超人总动员', cover: '🎬', rating: '⭐ 8.5', tag: '动画' },
  { id: 6, title: '飞屋环游记', cover: '🎬', rating: '⭐ 8.9', tag: '动画' }
])

const playlists = ref([
  { id: 1, name: '周末电影之夜', icon: '🎵', count: 12 },
  { id: 2, name: '宝宝最爱动画', icon: '🎵', count: 8 }
])

const goToPlayer = () => router.push('/video-player')
</script>

<style lang="scss" scoped>
.media-page {
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

.search-btn {
  width: 40px;
  height: 40px;
  border: none;
  background: #f8f9fa;
  border-radius: 50%;
  font-size: 20px;
  cursor: pointer;
}

.container {
  padding: 16px;
}

.category-tabs {
  display: flex;
  gap: 12px;
  overflow-x: auto;
  padding-bottom: 4px;

  .tab-item {
    flex-shrink: 0;
    padding: 8px 16px;
    background: #fff;
    border-radius: 999px;
    font-size: 14px;
    color: #636e72;
    cursor: pointer;
    transition: all 0.2s;

    &.active {
      background: #ff6b81;
      color: #fff;
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

.featured-card {
  background: #fff;
  border-radius: 16px;
  overflow: hidden;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
  cursor: pointer;

  .featured-cover {
    height: 200px;
    background: #f8f9fa;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 64px;
  }

  .featured-info {
    padding: 16px;

    .featured-title {
      font-size: 18px;
      font-weight: 600;
      color: #2d3436;
      margin-bottom: 8px;
    }

    .featured-meta {
      display: flex;
      gap: 8px;
      align-items: center;

      .rating {
        font-size: 14px;
        color: #ffd93d;
        font-weight: 500;
      }

      .tag {
        font-size: 12px;
        padding: 2px 8px;
        background: #f8f9fa;
        border-radius: 4px;
        color: #636e72;
      }
    }
  }
}

.content-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 12px;
}

.content-card {
  cursor: pointer;

  .content-cover {
    aspect-ratio: 3/4;
    background: #fff;
    border-radius: 12px;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 32px;
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
    display: flex;
    gap: 8px;
    align-items: center;

    .rating {
      font-size: 11px;
      color: #ffd93d;
    }

    .tag {
      font-size: 11px;
      color: #b2bec3;
    }
  }
}

.playlist-list {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.playlist-card {
  background: #fff;
  border-radius: 12px;
  padding: 16px;
  display: flex;
  align-items: center;
  gap: 16px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);

  .playlist-icon {
    font-size: 32px;
  }

  .playlist-info {
    flex: 1;

    .playlist-name {
      font-size: 15px;
      font-weight: 500;
      color: #2d3436;
      margin-bottom: 4px;
    }

    .playlist-count {
      font-size: 13px;
      color: #636e72;
    }
  }
}
</style>