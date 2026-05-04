<template>
  <div class="login-page">
    <div class="login-container">
      <div class="logo-section">
        <div class="logo">🏠</div>
        <h1 class="brand-name">家庭乐</h1>
        <p class="slogan">让家庭时光更精彩</p>
      </div>

      <div class="form-section">
        <div class="form-item">
          <input
            v-model="phone"
            type="tel"
            class="input"
            placeholder="请输入手机号"
            maxlength="11"
          />
        </div>

        <div class="form-item code-item">
          <input
            v-model="code"
            type="text"
            class="input code-input"
            placeholder="请输入验证码"
            maxlength="6"
          />
          <button
            class="btn btn-outline btn-small get-code-btn"
            @click="getCode"
            :disabled="countdown > 0"
          >
            {{ countdown > 0 ? `${countdown}s` : '获取验证码' }}
          </button>
        </div>

        <button class="btn btn-primary login-btn" @click="handleLogin">
          登录
        </button>

        <div class="other-login">
          <div class="divider">
            <span class="divider-line"></span>
            <span class="divider-text">其他登录方式</span>
            <span class="divider-line"></span>
          </div>

          <button class="btn wechat-btn" @click="handleWechatLogin">
            <span class="wechat-icon">💚</span>
            微信一键登录
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useUserStore } from '@/stores/user'
import { useFamilyStore } from '@/stores/family'

const router = useRouter()
const userStore = useUserStore()
const familyStore = useFamilyStore()

const phone = ref('')
const code = ref('')
const countdown = ref(0)

const getCode = () => {
  if (!phone.value) {
    alert('请先输入手机号')
    return
  }
  countdown.value = 60
  const timer = setInterval(() => {
    countdown.value--
    if (countdown.value <= 0) {
      clearInterval(timer)
    }
  }, 1000)
}

const handleLogin = () => {
  if (!phone.value || !code.value) {
    alert('请输入手机号和验证码')
    return
  }

  // 模拟登录
  const mockToken = 'mock_token_' + Date.now()
  const mockUserInfo = {
    id: Date.now(),
    name: '小明',
    avatar: '👦',
    phone: phone.value
  }

  userStore.setToken(mockToken)
  userStore.setUserInfo(mockUserInfo)

  if (familyStore.hasFamily) {
    router.push('/main/home')
  } else {
    router.push('/guide')
  }
}

const handleWechatLogin = () => {
  // 模拟微信登录
  const mockToken = 'mock_wechat_token_' + Date.now()
  const mockUserInfo = {
    id: Date.now(),
    name: '微信用户',
    avatar: '👤',
    wechatOpenId: 'mock_openid'
  }

  userStore.setToken(mockToken)
  userStore.setUserInfo(mockUserInfo)

  if (familyStore.hasFamily) {
    router.push('/main/home')
  } else {
    router.push('/guide')
  }
}
</script>

<style lang="scss" scoped>
.login-page {
  min-height: 100vh;
  background: linear-gradient(180deg, #fff5f0 0%, #ffe8dd 100%);
  padding: 0 24px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.login-container {
  width: 100%;
  max-width: 400px;
}

.logo-section {
  text-align: center;
  margin-bottom: 48px;

  .logo {
    font-size: 64px;
    margin-bottom: 16px;
  }

  .brand-name {
    font-size: 28px;
    font-weight: 700;
    color: #2d3436;
    margin-bottom: 8px;
  }

  .slogan {
    font-size: 15px;
    color: #636e72;
  }
}

.form-section {
  .form-item {
    margin-bottom: 16px;
  }

  .code-item {
    display: flex;
    gap: 12px;

    .code-input {
      flex: 1;
    }

    .get-code-btn {
      white-space: nowrap;
      min-width: 100px;
    }
  }

  .login-btn {
    width: 100%;
    margin-top: 24px;
    height: 48px;
  }
}

.other-login {
  margin-top: 48px;

  .divider {
    display: flex;
    align-items: center;
    margin-bottom: 32px;

    .divider-line {
      flex: 1;
      height: 1px;
      background: #dfe6e9;
    }

    .divider-text {
      padding: 0 16px;
      font-size: 13px;
      color: #b2bec3;
    }
  }

  .wechat-btn {
    width: 100%;
    background: #07c160;
    color: #fff;
    display: flex;
    align-items: center;
    justify-content: center;
    gap: 8px;

    .wechat-icon {
      font-size: 20px;
    }
  }
}
</style>