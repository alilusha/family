import { createRouter, createWebHashHistory } from 'vue-router'
import { useUserStore } from '@/store/user'

const routes = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login.vue')
  },
  {
    path: '/',
    name: 'Layout',
    component: () => import('@/layout/index.vue'),
    redirect: '/dashboard',
    children: [
      {
        path: 'dashboard',
        name: 'Dashboard',
        component: () => import('@/views/dashboard/index.vue')
      },
      {
        path: 'user/app',
        name: 'AppUser',
        component: () => import('@/views/user/appUser.vue')
      },
      {
        path: 'user/admin',
        name: 'AdminUser',
        component: () => import('@/views/user/adminUser.vue')
      },
      {
        path: 'family/list',
        name: 'FamilyList',
        component: () => import('@/views/family/list.vue')
      },
      {
        path: 'game/question',
        name: 'GameQuestion',
        component: () => import('@/views/game/question.vue')
      },
      {
        path: 'game/word',
        name: 'GameWord',
        component: () => import('@/views/game/word.vue')
      },
      {
        path: 'game/record',
        name: 'GameRecord',
        component: () => import('@/views/game/record.vue')
      },
      {
        path: 'media/content',
        name: 'MediaContent',
        component: () => import('@/views/media/content.vue')
      },
      {
        path: 'media/category',
        name: 'MediaCategory',
        component: () => import('@/views/media/category.vue')
      },
      {
        path: 'activity/list',
        name: 'ActivityList',
        component: () => import('@/views/activity/list.vue')
      },
      {
        path: 'system/menu',
        name: 'SystemMenu',
        component: () => import('@/views/system/menu.vue')
      },
      {
        path: 'system/dict',
        name: 'SystemDict',
        component: () => import('@/views/system/dict.vue')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const userStore = useUserStore()
  const token = userStore.token

  if (to.path === '/login') {
    next()
  } else {
    if (token) {
      next()
    } else {
      next('/login')
    }
  }
})

export default router
