<template>
  <div class="page-container">
    <el-card>
      <div class="search-form">
        <el-form :inline="true" :model="searchForm">
          <el-form-item label="用户名">
            <el-input v-model="searchForm.username" placeholder="请输入" />
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
        </el-form-item>
        </el-form>
      </div>
      <div class="table-toolbar">
        <el-button type="primary" @click="handleAdd">新增用户</el-button>
      </div>
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="username" label="用户名" />
        <el-table-column prop="nickname" label="昵称" />
        <el-table-column prop="roleName" label="角色" />
        <el-table-column label="状态" width="100">
          <template #default="{ row }">
            <el-tag :type="row.status === 1 ? 'success' : 'danger'">
              {{ row.status === 1 ? '正常' : '禁用' }}
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

const searchForm = ref({ username: '', status: null })
const tableData = ref([
  { id: 1, username: 'admin', nickname: '超级管理员', roleName: '超级管理员', status: 1, createTime: '2024-01-01 12:00:00' },
  { id: 2, username: 'operator', nickname: '运营人员', roleName: '运营', status: 1, createTime: '2024-01-02 12:00:00' }
])
const pageNum = ref(1)
const pageSize = ref(10)
const total = ref(10)

const handleReset = () => { searchForm.value = { username: '', status: null }; loadData() }
const handleAdd = () => {}
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
