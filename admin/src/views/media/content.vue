<template>
  <div class="page-container">
    <el-card>
      <div class="search-form">
        <el-form :inline="true" :model="searchForm">
          <el-form-item label="内容标题">
            <el-input v-model="searchForm.title" placeholder="请输入" />
          </el-form-item>
          <el-form-item label="类型">
            <el-select v-model="searchForm.type" placeholder="请选择">
              <el-option label="电影" value="电影" />
              <el-option label="动画" value="动画" />
              <el-option label="音乐" value="音乐" />
              <el-option label="纪录片" value="纪录片" />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="loadData">查询</el-button>
            <el-button @click="handleReset">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div class="table-toolbar">
        <el-button type="primary">新增内容</el-button>
        <el-button type="success">上传内容</el-button>
      </div>
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="title" label="标题" />
        <el-table-column prop="type" label="类型" width="100" />
        <el-table-column prop="duration" label="时长" width="100" />
        <el-table-column prop="viewCount" label="播放次数" width="100" />
        <el-table-column label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="row.status === 1 ? 'success' : 'danger'">
              {{ row.status === 1 ? '上架' : '下架' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column label="操作" width="240">
          <template #default="{ row }">
            <el-button type="primary" link size="small">编辑</el-button>
            <el-button type="primary" link size="small">{{ row.status === 1 ? '下架' : '上架' }}</el-button>
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

const searchForm = ref({ title: '', type: '' })
const tableData = ref([
  { id: 1, title: '寻梦环游记', type: '电影', duration: '105分钟', viewCount: 560, status: 1, createTime: '2024-01-01 12:00:00' },
  { id: 2, title: '哪吒之魔童降世', type: '动画', duration: '110分钟', viewCount: 1200, status: 1, createTime: '2024-01-02 12:00:00' },
  { id: 3, title: '家庭音乐合集', type: '音乐', duration: '60分钟', viewCount: 450, status: 1, createTime: '2024-01-03 12:00:00' }
])
const pageNum = ref(1)
const pageSize = ref(10)
const total = ref(50)

const handleReset = () => { searchForm.value = { title: '', type: '' }; loadData() }
const loadData = () => {}
onMounted(loadData)
</script>

<style lang="scss" scoped>
.page-container { .search-form { margin-bottom: 20px; } .table-toolbar { margin-bottom: 15px; } .pagination { margin-top: 20px; display: flex; justify-content: flex-end; } }
</style>
