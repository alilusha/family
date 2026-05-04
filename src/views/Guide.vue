<template>
  <div class="guide-page">
    <div class="guide-container">
      <div class="step-indicator">
        <span class="step-dot active"></span>
        <span class="step-dot active"></span>
        <span class="step-dot"></span>
      </div>

      <div class="icon-section">
        <div class="family-icon">👨‍👩‍👧‍👦</div>
        <h1 class="title">创建你的家庭空间</h1>
      </div>

      <div class="form-section">
        <div class="form-item" v-if="activeTab === 'create'">
          <input
            v-model="familyName"
            type="text"
            class="input"
            placeholder="请输入家庭名称"
          />
        </div>

        <div class="form-item" v-if="activeTab === 'join'">
          <input
            v-model="inviteCode"
            type="text"
            class="input"
            placeholder="请输入邀请码"
            maxlength="6"
          />
        </div>

        <div class="tab-switch">
          <button
            class="tab-btn"
            :class="{ active: activeTab === 'create' }"
            @click="activeTab = 'create'"
          >
            创建家庭
          </button>
          <button
            class="tab-btn"
            :class="{ active: activeTab === 'join' }"
            @click="activeTab = 'join'"
          >
            加入家庭
          </button>
        </div>

        <button class="btn btn-primary submit-btn" @click="handleSubmit">
          {{ activeTab === 'create' ? '创建家庭' : '加入家庭' }}
        </button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useFamilyStore } from '@/stores/family'

const router = useRouter()
const familyStore = useFamilyStore()

const activeTab = ref('create')
const familyName = ref('')
const inviteCode = ref('')

const handleSubmit = () => {
  if (activeTab.value === 'create') {
    if (!familyName.value) {
      alert('请输入家庭名称')
      return
    }
    familyStore.createFamily(familyName.value)
  } else {
    if (!inviteCode.value) {
      alert('请输入邀请码')
      return
    }
    familyStore.joinFamily(inviteCode.value)
  }
  router.push('/main/home')
}
</script>

<style lang="scss" scoped>
.guide-page {
  min-height: 100vh;
  background: #f8f9fa;
  padding: 48px 24px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.guide-container {
  width: 100%;
  max-width: 400px;
}

.step-indicator {
  display: flex;
  justify-content: center;
  gap: 8px;
  margin-bottom: 48px;

  .step-dot {
    width: 8px;
    height: 8px;
    border-radius: 50%;
    background: #dfe6e9;

    &.active {
      background: #ff6b35;
      width: 24px;
      border-radius: 4px;
    }
  }
}

.icon-section {
  text-align: center;
  margin-bottom: 48px;

  .family-icon {
    font-size: 64px;
    margin-bottom: 24px;
  }

  .title {
    font-size: 24px;
    font-weight: 600;
    color: #2d3436;
  }
}

.form-section {
  .form-item {
    margin-bottom: 24px;
  }

  .tab-switch {
    display: flex;
    background: #fff;
    border-radius: 12px;
    padding: 4px;
    margin-bottom: 24px;

    .tab-btn {
      flex: 1;
      padding: 12px;
      border: none;
      background: transparent;
      border-radius: 8px;
      font-size: 15px;
      color: #636e72;
      cursor: pointer;
      transition: all 0.2s;

      &.active {
        background: #ff6b35;
        color: #fff;
      }
    }
  }

  .submit-btn {
    width: 100%;
    height: 48px;
  }
}
</style>