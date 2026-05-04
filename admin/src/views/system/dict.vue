<template>
  <div class="page-container">
    <el-card>
      <div class="search-form">
        <el-form :inline="true" :model="searchForm">
          <el-form-item label="字典名称">
            <el-input v-model="searchForm.name" placeholder="请输入" />
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="loadData">查询</el-button>
            <el-button @click="handleReset">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div class="table-toolbar">
        <el-button type="primary">新增字典</el-button>
      </div>
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="name" label="字典名称" />
        <el-table-column prop="code" label="字典编码" />
        <el-table-column prop="remark" label="备注" />
        <el-table-column prop="createTime" label="创建时间" width="180" />
        <el-table-column label="操作" width="240">
          <template #default="{ row }">
            <el-button type="primary" link size="small">编辑</el-button>
            <el-button type="success" link size="small">数据</el-button>
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

const searchForm = ref({ name: '' })
const tableData = ref([
  { id: 1, name: '游戏类型', code: 'game_type', remark: '问答、你画我猜等', createTime: '2024-01-01 12:00:00' },
  { id: 2, name: '内容类型', code: 'media_type', remark: '电影、动画、音乐等', createTime: '2024-01-01 12:00:00' },
  { id: 3, name: '难度等级', code: 'difficulty', remark: '简单、中等、困难', createTime: '2024-01-01 12:00:00' }
])
const pageNum = ref(1)
const pageSize = ref(10)
const total = ref(10)

const handleReset = () => { searchForm.value = { name: '' }; loadData() }
const loadData = () => {}
onMounted(loadData)
</script>

<style lang="scss" scoped>
.page-container { .search-form { margin-bottom: 20px; } .table-toolbar { margin-bottom: 15px; } .pagination { margin-top: 20px; display: flex; justify-content: flex-end; } }
</style>
