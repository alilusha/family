import { createRouter, createWebHashHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/Login.vue')
  },
  {
    path: '/guide',
    name: 'Guide',
    component: () => import('@/views/Guide.vue')
  },
  {
    path: '/main',
    name: 'Main',
    component: () => import('@/views/Main.vue'),
    redirect: '/main/home',
    children: [
      {
        path: 'home',
        name: 'Home',
        component: () => import('@/views/Home.vue')
      },
      {
        path: 'game',
        name: 'Game',
        component: () => import('@/views/Game.vue')
      },
      {
        path: 'media',
        name: 'Media',
        component: () => import('@/views/Media.vue')
      },
      {
        path: 'calendar',
        name: 'Calendar',
        component: () => import('@/views/Calendar.vue')
      }
    ]
  },
  {
    path: '/game-hall',
    name: 'GameHall',
    component: () => import('@/views/game/GameHall.vue')
  },
  {
    path: '/game-room',
    name: 'GameRoom',
    component: () => import('@/views/game/GameRoom.vue')
  },
  {
    path: '/quiz-game',
    name: 'QuizGame',
    component: () => import('@/views/game/QuizGame.vue')
  },
  {
    path: '/draw-guess',
    name: 'DrawGuess',
    component: () => import('@/views/game/DrawGuess.vue')
  },
  {
    path: '/video-player',
    name: 'VideoPlayer',
    component: () => import('@/views/media/VideoPlayer.vue')
  },
  {
    path: '/create-event',
    name: 'CreateEvent',
    component: () => import('@/views/calendar/CreateEvent.vue')
  },
  {
    path: '/family-space',
    name: 'FamilySpace',
    component: () => import('@/views/FamilySpace.vue')
  },
  {
    path: '/profile',
    name: 'Profile',
    component: () => import('@/views/Profile.vue')
  },
  {
    path: '/notifications',
    name: 'Notifications',
    component: () => import('@/views/Notifications.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router