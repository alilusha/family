<template>
  <div class="page-container">
    <el-card>
      <div class="table-toolbar">
        <el-button type="primary">新增菜单</el-button>
      </div>
      <el-table :data="tableData" border style="width: 100%" row-key="id">
        <el-table-column prop="title" label="菜单名称" />
        <el-table-column prop="path" label="路径" />
        <el-table-column prop="icon" label="图标" width="100" />
        <el-table-column prop="sort" label="排序" width="100" />
        <el-table-column label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="row.status === 1 ? 'success' : 'danger'">
              {{ row.status === 1 ? '启用' : '禁用' }}
            </el-tag>
          </template>
        </el-table-column>
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
  { id: 1, title: '运营概览', path: '/dashboard', icon: 'DataLine', sort: 1, status: 1 },
  { id: 2, title: '用户管理', path: '/user', icon: 'User', sort: 2, status: 1 },
  { id: 3, title: '家庭管理', path: '/family', icon: 'House', sort: 3, status: 1 },
  { id: 4, title: '游戏管理', path: '/game', icon: 'VideoPlay', sort: 4, status: 1 },
  { id: 5, title: '影音管理', path: '/media', icon: 'VideoCamera', sort: 5, status: 1 },
  { id: 6, title: '活动管理', path: '/activity', icon: 'Calendar', sort: 6, status: 1 },
  { id: 7, title: '系统管理', path: '/system', icon: 'Setting', sort: 7, status: 1 }
])

onMounted(() => {})
</script>

<style lang="scss" scoped>
.page-container { .table-toolbar { margin-bottom: 15px; } }
</style>
