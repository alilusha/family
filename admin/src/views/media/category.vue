<template>
  <div class="page-container">
    <el-card>
      <div class="table-toolbar">
        <el-button type="primary">新增分类</el-button>
      </div>
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="name" label="分类名称" />
        <el-table-column prop="sort" label="排序" width="100" />
        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column label="操作" width="200">
          <template #default="{ row }">
            <el-button type="primary" link size="small">编辑</el-button>
            <el-button type="danger" link size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const tableData = ref([
  { id: 1, name: '电影', sort: 1, createTime: '2024-01-01 12:00:00' },
  { id: 2, name: '动画', sort: 2, createTime: '2024-01-01 12:00:00' },
  { id: 3, name: '音乐', sort: 3, createTime: '2024-01-01 12:00:00' },
  { id: 4, name: '纪录片', sort: 4, createTime: '2024-01-01 12:00:00' }
])

onMounted(() => {})
</script>

<style lang="scss" scoped>
.page-container { .table-toolbar { margin-bottom: 15px; } }
</style>
