<template>
  <div class="page-container">
    <el-card>
      <div class="search-form">
        <el-form :inline="true" :model="searchForm">
          <el-form-item label="活动标题">
            <el-input v-model="searchForm.title" placeholder="请输入" />
          </el-form-item>
          <el-form-item label="状态">
            <el-select v-model="searchForm.status" placeholder="请选择">
              <el-option label="待开始" :value="0" />
              <el-option label="进行中" :value="1" />
              <el-option label="已结束" :value="2" />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="loadData">查询</el-button>
            <el-button @click="handleReset">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="title" label="活动标题" />
        <el-table-column prop="familyName" label="家庭" />
        <el-table-column prop="activityTime" label="活动时间" width="180" />
        <el-table-column prop="location" label="地点" />
        <el-table-column prop="participantCount" label="参与人数" width="100" />
        <el-table-column label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="row.status === 0 ? 'info' : row.status === 1 ? 'success' : ''">
              {{ row.status === 0 ? '待开始' : row.status === 1 ? '进行中' : '已结束' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column label="操作" width="150">
          <template #default="{ row }">
            <el-button type="primary" link size="small">查看详情</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination">
        <el-pagination v-model:current-page="pageNum" v-model:page-size="pageSize" :page-sizes="[10,20,50,100]" :total="total" layout="total, sizes, prev, pager, next, jumper" />
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const searchForm = ref({ title: '', status: null })
const tableData = ref([
  { id: 1, title: '家庭电影之夜', familyName: '快乐家庭1号', activityTime: '2024-01-15 20:00', location: '家里', participantCount: 4, status: 1, createTime: '2024-01-10 12:00:00' },
  { id: 2, title: '周末游戏日', familyName: '幸福家庭2号', activityTime: '2024-01-13 14:00', location: '户外', participantCount: 3, status: 2, createTime: '2024-01-08 12:00:00' },
  { id: 3, title: '亲子手工活动', familyName: '温馨家庭3号', activityTime: '2024-01-16 10:00', location: '家里', participantCount: 5, status: 0, createTime: '2024-01-12 12:00:00' }
])
const pageNum = ref(1)
const pageSize = ref(10)
const total = ref(50)

const handleReset = () => { searchForm.value = { title: '', status: null }; loadData() }
const loadData = () => {}
onMounted(loadData)
</script>

<style lang="scss" scoped>
.page-container { .search-form { margin-bottom: 20px; } .pagination { margin-top: 20px; display: flex; justify-content: flex-end; } }
</style>
