<template>
  <div class="page-container">
    <el-card>
      <div class="search-form">
        <el-form :inline="true" :model="searchForm">
          <el-form-item label="家庭名称">
            <el-input v-model="searchForm.name" placeholder="请输入" />
          </el-form-item>
          <el-form-item label="状态">
            <el-select v-model="searchForm.status" placeholder="请选择">
              <el-option label="正常" :value="1" />
              <el-option label="禁用" :value="0" />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="loadData">查询</el-button>
            <el-button @click="handleReset">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div class="table-toolbar">
        <el-button type="primary">新增家庭</el-button>
      </div>
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="name" label="家庭名称" />
        <el-table-column prop="memberCount" label="成员数" width="100" />
        <el-table-column prop="creator" label="创建者" />
        <el-table-column prop="gameCount" label="游戏次数" />
        <el-table-column prop="activityCount" label="活动次数" />
        <el-table-column label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="row.status === 1 ? 'success' : 'danger'">
              {{ row.status === 1 ? '正常' : '禁用' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column label="操作" width="240">
          <template #default="{ row }">
            <el-button type="primary" link size="small">查看详情</el-button>
            <el-button type="primary" link size="small">编辑</el-button>
            <el-button type="danger" link size="small">删除</el-button>
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

const searchForm = ref({ name: '', status: null })
const tableData = ref([
  { id: 1, name: '快乐家庭1号', memberCount: 4, creator: '用户1', gameCount: 56, activityCount: 12, status: 1, createTime: '2024-01-01 12:00:00' },
  { id: 2, name: '幸福家庭2号', memberCount: 3, creator: '用户3', gameCount: 38, activityCount: 8, status: 1, createTime: '2024-01-02 12:00:00' },
  { id: 3, name: '温馨家庭3号', memberCount: 5, creator: '用户5', gameCount: 72, activityCount: 15, status: 1, createTime: '2024-01-03 12:00:00' }
])
const pageNum = ref(1)
const pageSize = ref(10)
const total = ref(50)

const handleReset = () => { searchForm.value = { name: '', status: null }; loadData() }
const loadData = () => {}
onMounted(loadData)
</script>

<style lang="scss" scoped>
.page-container {
  .search-form { margin-bottom: 20px; }
  .table-toolbar { margin-bottom: 15px; }
  .pagination { margin-top: 20px; display: flex; justify-content: flex-end; }
}
</style>
