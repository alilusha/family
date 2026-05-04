<template>
  <div class="layout-container">
    <el-container>
      <el-aside :width="isCollapse ? '64px' : '200px'" class="sidebar">
        <div class="logo">
          <h2 v-if="!isCollapse">HomeFun后台</h2>
          <span v-else>HF</span>
        </div>
        <el-menu
          :default-active="activeMenu"
          :collapse="isCollapse"
          :unique-opened="false"
          router
          background-color="#304156"
          text-color="#bfcbd9"
          active-text-color="#409eff"
        >
          <el-menu-item index="/dashboard">
            <el-icon><DataLine /></el-icon>
            <template #title>运营概览</template>
          </el-menu-item>
          <el-sub-menu index="1">
            <template #title>
              <el-icon><User /></el-icon>
              <span>用户管理</span>
            </template>
            <el-menu-item index="/user/app">小程序用户</el-menu-item>
            <el-menu-item index="/user/admin">后台用户</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="2">
            <template #title>
              <el-icon><House /></el-icon>
              <span>家庭管理</span>
            </template>
            <el-menu-item index="/family/list">家庭列表</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="3">
            <template #title>
              <el-icon><VideoPlay /></el-icon>
              <span>游戏管理</span>
            </template>
            <el-menu-item index="/game/question">问答题库</el-menu-item>
            <el-menu-item index="/game/word">你画我猜词库</el-menu-item>
            <el-menu-item index="/game/record">游戏记录</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="4">
            <template #title>
              <el-icon><VideoCamera /></el-icon>
              <span>影音管理</span>
            </template>
            <el-menu-item index="/media/content">内容列表</el-menu-item>
            <el-menu-item index="/media/category">分类管理</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="5">
            <template #title>
              <el-icon><Calendar /></el-icon>
              <span>活动管理</span>
            </template>
            <el-menu-item index="/activity/list">活动列表</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="6">
            <template #title>
              <el-icon><Setting /></el-icon>
              <span>系统管理</span>
            </template>
            <el-menu-item index="/system/menu">菜单管理</el-menu-item>
            <el-menu-item index="/system/dict">字典管理</el-menu-item>
          </el-sub-menu>
        </el-menu>
      </el-aside>
      <el-container class="main-container">
        <el-header class="header">
          <div class="header-left">
            <div class="collapse-btn" @click="toggleCollapse">
              <el-icon v-if="!isCollapse"><Fold /></el-icon>
              <el-icon v-else><Expand /></el-icon>
            </div>
            <el-breadcrumb separator="/">
              <el-breadcrumb-item>首页</el-breadcrumb-item>
              <el-breadcrumb-item v-if="currentTitle">{{ currentTitle }}</el-breadcrumb-item>
            </el-breadcrumb>
          </div>
          <div class="header-right">
            <el-dropdown>
              <div class="user-info">
                <el-avatar :size="36" :src="userInfo.avatar" />
                <span class="username">{{ userInfo.username }}</span>
                <el-icon><ArrowDown /></el-icon>
              </div>
              <template #dropdown>
                <el-dropdown-menu>
                  <el-dropdown-item>个人中心</el-dropdown-item>
                  <el-dropdown-item>修改密码</el-dropdown-item>
                  <el-dropdown-item @click="handleLogout">退出登录</el-dropdown-item>
                </el-dropdown-menu>
              </template>
            </el-dropdown>
          </div>
        </el-header>
        <el-main class="main-content">
          <router-view />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useUserStore } from '@/store/user'
import { ElMessage, ElMessageBox } from 'element-plus'

const router = useRouter()
const route = useRoute()
const userStore = useUserStore()
const isCollapse = ref(false)

const activeMenu = computed(() => route.path)
const userInfo = computed(() => userStore.userInfo)

const menuMap = {
  '/dashboard': '运营概览',
  '/user/app': '小程序用户',
  '/user/admin': '后台用户',
  '/family/list': '家庭列表',
  '/game/question': '问答题库',
  '/game/word': '你画我猜词库',
  '/game/record': '游戏记录',
  '/media/content': '内容列表',
  '/media/category': '分类管理',
  '/activity/list': '活动列表',
  '/system/menu': '菜单管理',
  '/system/dict': '字典管理'
}

const currentTitle = computed(() => menuMap[route.path] || '')

const toggleCollapse = () => {
  isCollapse.value = !isCollapse.value
}

const handleLogout = async () => {
  try {
    await ElMessageBox.confirm('确定要退出登录吗?', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    await userStore.logout()
    ElMessage.success('退出成功')
    router.push('/login')
  } catch (error) {
  }
}
</script>

<style lang="scss" scoped>
.layout-container {
  width: 100%;
  height: 100%;
}

.sidebar {
  background: #304156;
  transition: width 0.3s;
  height: 100vh;
  overflow-x: hidden;
}

.logo {
  height: 60px;
  line-height: 60px;
  text-align: center;
  background: #263445;
  color: #fff;
  font-size: 20px;
  font-weight: bold;

  h2 {
    margin: 0;
    font-size: 18px;
  }

  span {
    font-size: 24px;
  }
}

.main-container {
  height: 100vh;
  display: flex;
  flex-direction: column;
}

.header {
  background: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 0 20px;
  height: 60px;
}

.header-left {
  display: flex;
  align-items: center;
  gap: 20px;
}

.collapse-btn {
  cursor: pointer;
  font-size: 20px;
  color: #606266;
}

.header-right {
  display: flex;
  align-items: center;
  gap: 20px;
}

.user-info {
  display: flex;
  align-items: center;
  gap: 10px;
  cursor: pointer;

  .username {
    color: #606266;
  }
}

.main-content {
  background: #f0f2f5;
  padding: 20px;
  overflow-y: auto;
}
</style>
