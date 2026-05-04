<template>
  <div class="page-container">
    <el-card>
      <div class="search-form">
        <el-form :inline="true" :model="searchForm">
          <el-form-item label="昵称">
            <el-input v-model="searchForm.nickname" placeholder="请输入" />
          </el-form-item>
          <el-form-item label="状态">
            <el-select v-model="searchForm.status" placeholder="请选择">
              <el-option label="正常" :value="1" />
              <el-option label="禁用" :value="0" />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="handleSearch">查询</el-button>
            <el-button @click="handleReset">重置</el-button>
          </el-form-item>
        </el-form>
      </div>
      <div class="table-toolbar">
        <el-button type="primary" @click="handleAdd">新增用户</el-button>
      </div>
      <el-table :data="tableData" border style="width: 100%">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column label="头像" width="80">
          <template #default="{ row }">
            <el-avatar :size="40" :src="row.avatar" />
          </template>
        </el-table-column>
        <el-table-column prop="nickname" label="昵称" />
        <el-table-column prop="phone" label="手机号" />
        <el-table-column prop="familyName" label="所属家庭" />
        <el-table-column prop="role" label="角色" />
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
        <el-table-column label="操作" width="200">
          <template #default="{ row }">
            <el-button type="primary" link size="small">编辑</el-button>
            <el-button type="danger" link size="small">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="pagination">
        <el-pagination
          v-model:current-page="pageNum"
          v-model:page-size="pageSize"
          :page-sizes="[10, 20, 50, 100]"
          :total="total"
          layout="total, sizes, prev, pager, next, jumper"
        />
      </div>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'

const searchForm = ref({
  nickname: '',
  status: null
})
const tableData = ref([])
const pageNum = ref(1)
const pageSize = ref(10)
const total = ref(0)

const loadData = async () => {
  const mockRes = {
    list: [
      { id: 1, nickname: '用户1', phone: '13812345678', avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', familyName: '快乐家庭1号', role: '家长', gameCount: 15, activityCount: 5, status: 1, createTime: '2024-01-01 12:00:00' },
      { id: 2, nickname: '用户2', phone: '13812345679', avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', familyName: '快乐家庭1号', role: '儿童', gameCount: 10, activityCount: 3, status: 1, createTime: '2024-01-02 12:00:00' },
      { id: 3, nickname: '用户3', phone: '13812345680', avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', familyName: '幸福家庭2号', role: '家长', gameCount: 20, activityCount: 8, status: 1, createTime: '2024-01-03 12:00:00' },
      { id: 4, nickname: '用户4', phone: '13812345681', avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', familyName: '幸福家庭2号', role: '儿童', gameCount: 8, activityCount: 2, status: 0, createTime: '2024-01-04 12:00:00' },
      { id: 5, nickname: '用户5', phone: '13812345682', avatar: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png', familyName: '温馨家庭3号', role: '家长', gameCount: 18, activityCount: 6, status: 1, createTime: '2024-01-05 12:00:00' }
    ],
    total: 50
  }
  tableData.value = mockRes.list
  total.value = mockRes.total
}

const handleSearch = () => {
  loadData()
}

const handleReset = () => {
  searchForm.value = { nickname: '', status: null }
  loadData()
}

const handleAdd = () => {
}

onMounted(() => {
  loadData()
})
</script>

<style lang="scss" scoped>
.page-container {
  .search-form {
    margin-bottom: 20px;
  }
  .table-toolbar {
    margin-bottom: 15px;
  }
  .pagination {
    margin-top: 20px;
    display: flex;
    justify-content: flex-end;
  }
}
</style>
