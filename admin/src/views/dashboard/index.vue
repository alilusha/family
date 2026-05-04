<template>
  <div class="dashboard">
    <el-row :gutter="20">
      <el-col :span="6">
        <div class="stat-card user">
          <div class="stat-card-content">
            <div class="stat-icon">
              <el-icon><User /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-value">{{ stats.userCount }}</div>
              <div class="stat-label">用户总数</div>
            </div>
          </div>
          <div class="stat-footer">
            较昨日 <span class="up">+12%</span>
          </div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="stat-card family">
          <div class="stat-card-content">
            <div class="stat-icon">
              <el-icon><House /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-value">{{ stats.familyCount }}</div>
              <div class="stat-label">家庭总数</div>
            </div>
          </div>
          <div class="stat-footer">
            较昨日 <span class="up">+8%</span>
          </div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="stat-card game">
          <div class="stat-card-content">
            <div class="stat-icon">
              <el-icon><VideoPlay /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-value">{{ stats.gameCount }}</div>
              <div class="stat-label">游戏场次</div>
            </div>
          </div>
          <div class="stat-footer">
            较昨日 <span class="up">+15%</span>
          </div>
        </div>
      </el-col>
      <el-col :span="6">
        <div class="stat-card activity">
          <div class="stat-card-content">
            <div class="stat-icon">
              <el-icon><Calendar /></el-icon>
            </div>
            <div class="stat-info">
              <div class="stat-value">{{ stats.activityCount }}</div>
              <div class="stat-label">活动数量</div>
            </div>
          </div>
          <div class="stat-footer">
            较昨日 <span class="up">+6%</span>
          </div>
        </div>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px;">
      <el-col :span="12">
        <div class="chart-card">
          <div class="chart-header">
            <h3>用户增长趋势</h3>
          </div>
          <div ref="userChartRef" class="chart"></div>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="chart-card">
          <div class="chart-header">
            <h3>游戏场次统计</h3>
          </div>
          <div ref="gameChartRef" class="chart"></div>
        </div>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px;">
      <el-col :span="16">
        <div class="chart-card">
          <div class="chart-header">
            <h3>最近活动</h3>
            <el-button type="primary" link>查看全部</el-button>
          </div>
          <el-table :data="recentActivities" style="width: 100%">
            <el-table-column prop="title" label="活动名称" />
            <el-table-column prop="familyName" label="家庭" />
            <el-table-column prop="activityTime" label="活动时间" />
            <el-table-column prop="participantCount" label="参与人数" />
            <el-table-column label="状态">
              <template #default="{ row }">
                <el-tag v-if="row.status === 0" type="info">待开始</el-tag>
                <el-tag v-else-if="row.status === 1" type="success">进行中</el-tag>
                <el-tag v-else type="info">已结束</el-tag>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="chart-card">
          <div class="chart-header">
            <h3>热门家庭</h3>
          </div>
          <div class="hot-family-list">
            <div v-for="(item, index) in hotFamilies" :key="index" class="hot-family-item">
              <span class="rank" :class="'rank-' + (index + 1)">{{ index + 1 }}</span>
              <div class="family-info">
                <span class="family-name">{{ item.name }}</span>
                <span class="family-count">{{ item.gameCount }}次游戏</span>
              </div>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, onMounted, nextTick } from 'vue'
import * as echarts from 'echarts'

const stats = ref({
  userCount: 0,
  familyCount: 0,
  gameCount: 0,
  activityCount: 0
})

const userChartRef = ref(null)
const gameChartRef = ref(null)

const recentActivities = ref([
  { title: '家庭电影之夜', familyName: '快乐家庭1号', activityTime: '2024-01-15 20:00', participantCount: 4, status: 1 },
  { title: '周末游戏日', familyName: '幸福家庭3号', activityTime: '2024-01-14 14:00', participantCount: 3, status: 2 },
  { title: '亲子手工活动', familyName: '温馨家庭5号', activityTime: '2024-01-16 10:00', participantCount: 5, status: 0 },
  { title: '家庭知识竞赛', familyName: '开心家庭2号', activityTime: '2024-01-13 19:00', participantCount: 4, status: 2 },
  { title: '户外野餐活动', familyName: '美满家庭4号', activityTime: '2024-01-17 11:00', participantCount: 6, status: 0 }
])

const hotFamilies = ref([
  { name: '快乐家庭1号', gameCount: 328 },
  { name: '幸福家庭3号', gameCount: 295 },
  { name: '温馨家庭5号', gameCount: 256 },
  { name: '开心家庭2号', gameCount: 234 },
  { name: '美满家庭4号', gameCount: 201 },
  { name: '欢乐家庭6号', gameCount: 185 }
])

const loadData = async () => {
  const mockRes = {
    userCount: 12580,
    familyCount: 3200,
    gameCount: 56800,
    activityCount: 12300
  }
  stats.value = mockRes
}

const initUserChart = () => {
  const chart = echarts.init(userChartRef.value)
  chart.setOption({
    tooltip: {
      trigger: 'axis'
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        name: '新增用户',
        type: 'line',
        smooth: true,
        data: [120, 200, 150, 80, 70, 230, 180],
        itemStyle: { color: '#409eff' }
      }
    ]
  })
}

const initGameChart = () => {
  const chart = echarts.init(gameChartRef.value)
  chart.setOption({
    tooltip: {
      trigger: 'axis'
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        name: '问答大挑战',
        type: 'bar',
        data: [120, 200, 150, 80, 70, 400, 380],
        itemStyle: { color: '#67c23a' }
      },
      {
        name: '你画我猜',
        type: 'bar',
        data: [100, 180, 130, 70, 60, 350, 320],
        itemStyle: { color: '#e6a23c' }
      }
    ]
  })
}

onMounted(async () => {
  await loadData()
  await nextTick()
  initUserChart()
  initGameChart()
})
</script>

<style lang="scss" scoped>
.dashboard {
  width: 100%;
}

.stat-card {
  background: #fff;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);

  &.user {
    border-left: 4px solid #409eff;
  }

  &.family {
    border-left: 4px solid #67c23a;
  }

  &.game {
    border-left: 4px solid #e6a23c;
  }

  &.activity {
    border-left: 4px solid #f56c6c;
  }
}

.stat-card-content {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 15px;
}

.stat-icon {
  font-size: 48px;
  opacity: 0.2;
}

.stat-info {
  text-align: right;
}

.stat-value {
  font-size: 32px;
  font-weight: bold;
  color: #303133;
}

.stat-label {
  font-size: 14px;
  color: #909399;
}

.stat-footer {
  color: #909399;
  font-size: 13px;
  padding-top: 10px;
  border-top: 1px solid #f5f7fa;
}

.up {
  color: #67c23a;
}

.down {
  color: #f56c6c;
}

.chart-card {
  background: #fff;
  border-radius: 8px;
  padding: 20px;
  box-shadow: 0 2px 12px rgba(0, 0, 0, 0.05);
}

.chart-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;

  h3 {
    margin: 0;
    font-size: 16px;
    color: #303133;
  }
}

.chart {
  width: 100%;
  height: 300px;
}

.hot-family-list {
  .hot-family-item {
    display: flex;
    align-items: center;
    padding: 12px 0;
    border-bottom: 1px solid #f5f7fa;

    &:last-child {
      border-bottom: none;
    }
  }
}

.rank {
  width: 24px;
  height: 24px;
  border-radius: 4px;
  background: #c0c4cc;
  color: #fff;
  text-align: center;
  line-height: 24px;
  font-size: 14px;
  margin-right: 12px;
  font-weight: bold;

  &.rank-1 {
    background: #f56c6c;
  }

  &.rank-2 {
    background: #e6a23c;
  }

  &.rank-3 {
    background: #67c23a;
  }
}

.family-info {
  flex: 1;
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.family-name {
  color: #303133;
  font-size: 14px;
}

.family-count {
  color: #909399;
  font-size: 12px;
}
</style>
