<template>
  <div class="page-container">
    <el-card>
      <div class="search-form">
        <el-form :inline="true" :model="searchForm">
          <el-form-item label="题目内容">
            <el-input v-model="searchForm.question" placeholder="请输入" />
          </el-form-item>
          <el-form-item label="分类">
            <el-select v-model="searchForm.category" placeholder="请选择">
              <el-option label="科学" value="科学" />
              <el-option label="生活" value="生活" />
              <el-option label="艺术" value="艺术" />
              <el-option label="体育" value="体育" />
              <el-option label="地理" value="地理" />
              <el-option label="历史" value="历史" />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="loadData">查询</el-button>
            <el-button @click="handleReset">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div class="table-toolbar">
        <el-button type="primary">新增题目</el-button>
        <el-button type="success">导入题目</el-button>
      </div>
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="question" label="题目" show-overflow-tooltip />
        <el-table-column prop="category" label="分类" width="100" />
        <el-table-column prop="difficulty" label="难度" width="100">
          <template #default="{ row }">
            <el-tag :type="row.difficulty === '简单' ? 'success' : row.difficulty === '中等' ? 'warning' : 'danger'">
              {{ row.difficulty }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="playCount" label="使用次数" width="100" />
        <el-table-column label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="row.status === 1 ? 'success' : 'danger'">
              {{ row.status === 1 ? '启用' : '禁用' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column label="操作" width="200">
          <template #default="{ row }">
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

const searchForm = ref({ question: '', category: '' })
const tableData = ref([
  { id: 1, question: '中国的首都是哪里？', category: '地理', difficulty: '简单', playCount: 120, status: 1, createTime: '2024-01-01 12:00:00' },
  { id: 2, question: '水的化学式是什么？', category: '科学', difficulty: '简单', playCount: 89, status: 1, createTime: '2024-01-02 12:00:00' },
  { id: 3, question: '《蒙娜丽莎》是谁的作品？', category: '艺术', difficulty: '中等', playCount: 67, status: 1, createTime: '2024-01-03 12:00:00' }
])
const pageNum = ref(1)
const pageSize = ref(10)
const total = ref(50)

const handleReset = () => { searchForm.value = { question: '', category: '' }; loadData() }
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
