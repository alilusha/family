<template>
  <div class="page-container">
    <el-card>
      <div class="search-form">
        <el-form :inline="true" :model="searchForm">
          <el-form-item label="游戏类型">
            <el-select v-model="searchForm.gameType" placeholder="请选择">
              <el-option label="问答大挑战" value="问答大挑战" />
              <el-option label="你画我猜" value="你画我猜" />
            </el-select>
          </el-form-item>
          <el-form-item label="家庭名称">
            <el-input v-model="searchForm.familyName" placeholder="请输入" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="loadData">查询</el-button>
            <el-button @click="handleReset">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="gameName" label="游戏类型" />
        <el-table-column prop="familyName" label="家庭" />
        <el-table-column prop="playTime" label="时长(分钟)" width="120" />
        <el-table-column prop="participantCount" label="参与人数" width="100" />
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

const searchForm = ref({ gameType: '', familyName: '' })
const tableData = ref([
  { id: 1, gameName: '问答大挑战', familyName: '快乐家庭1号', playTime: 15, participantCount: 4, createTime: '2024-01-15 20:00:00' },
  { id: 2, gameName: '你画我猜', familyName: '幸福家庭2号', playTime: 12, participantCount: 3, createTime: '2024-01-15 18:30:00' },
  { id: 3, gameName: '问答大挑战', familyName: '温馨家庭3号', playTime: 20, participantCount: 5, createTime: '2024-01-15 16:00:00' }
])
const pageNum = ref(1)
const pageSize = ref(10)
const total = ref(50)

const handleReset = () => { searchForm.value = { gameType: '', familyName: '' }; loadData() }
const loadData = () => {}
onMounted(loadData)
</script>

<style lang="scss" scoped>
.page-container { .search-form { margin-bottom: 20px; } .pagination { margin-top: 20px; display: flex; justify-content: flex-end; } }
</style>
