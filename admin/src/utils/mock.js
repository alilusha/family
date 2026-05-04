import Mock from 'mockjs'

Mock.setup({
  timeout: '200-600'
})

// 家庭列表数据
const familyList = []
for (let i = 1; i <= 50; i++) {
  familyList.push({
    id: i,
    name: `快乐家庭${i}号`,
    memberCount: Math.floor(Math.random() * 5) + 2,
    creator: `用户${i}`,
    status: i % 3 === 0 ? 0 : 1,
    createTime: Mock.Random.date() + ' ' + Mock.Random.time(),
    activityCount: Math.floor(Math.random() * 20),
    gameCount: Math.floor(Math.random() * 50)
  })
}

Mock.mock('/api/family/list', 'get', () => {
  return {
    code: 200,
    data: {
      list: familyList.slice(0, 10),
      total: familyList.length
    }
  }
})

// 用户列表数据
const userList = []
for (let i = 1; i <= 50; i++) {
  userList.push({
    id: i,
    nickname: `用户${i}`,
    phone: `138${String(Math.random()).slice(2, 10)}`,
    avatar: `https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png`,
    familyName: `快乐家庭${Math.ceil(i / 3)}号`,
    role: i % 3 === 0 ? '儿童' : '家长',
    status: i % 5 === 0 ? 0 : 1,
    createTime: Mock.Random.date() + ' ' + Mock.Random.time(),
    gameCount: Math.floor(Math.random() * 30),
    activityCount: Math.floor(Math.random() * 10)
  })
}

Mock.mock('/api/user/list', 'get', () => {
  return {
    code: 200,
    data: {
      list: userList.slice(0, 10),
      total: userList.length
    }
  }
})

// 问答题目数据
const questionList = []
const categories = ['科学', '生活', '艺术', '体育', '地理', '历史']
for (let i = 1; i <= 50; i++) {
  questionList.push({
    id: i,
    question: `问题${i}：${Mock.Random.ctitle(10, 20)}`,
    category: categories[Math.floor(Math.random() * categories.length)],
    options: ['A. 选项1', 'B. 选项2', 'C. 选项3', 'D. 选项4'],
    answer: 'A',
    difficulty: ['简单', '中等', '困难'][Math.floor(Math.random() * 3)],
    status: i % 4 === 0 ? 0 : 1,
    createTime: Mock.Random.date() + ' ' + Mock.Random.time(),
    playCount: Math.floor(Math.random() * 500)
  })
}

Mock.mock('/api/game/question/list', 'get', () => {
  return {
    code: 200,
    data: {
      list: questionList.slice(0, 10),
      total: questionList.length
    }
  }
})

// 你画我猜词库
const wordList = []
for (let i = 1; i <= 50; i++) {
  wordList.push({
    id: i,
    word: Mock.Random.ctitle(2, 4),
    category: ['动物', '日常', '食物', '运动'][Math.floor(Math.random() * 4)],
    difficulty: ['简单', '中等', '困难'][Math.floor(Math.random() * 3)],
    status: i % 4 === 0 ? 0 : 1,
    createTime: Mock.Random.date() + ' ' + Mock.Random.time(),
    playCount: Math.floor(Math.random() * 500)
  })
}

Mock.mock('/api/game/word/list', 'get', () => {
  return {
    code: 200,
    data: {
      list: wordList.slice(0, 10),
      total: wordList.length
    }
  }
})

// 游戏记录数据
const gameRecordList = []
for (let i = 1; i <= 50; i++) {
  gameRecordList.push({
    id: i,
    gameName: i % 2 === 0 ? '问答大挑战' : '你画我猜',
    familyName: `快乐家庭${Math.ceil(i / 3)}号`,
    playTime: Math.floor(Math.random() * 30) + 5,
    participantCount: Math.floor(Math.random() * 4) + 2,
    createTime: Mock.Random.date() + ' ' + Mock.Random.time()
  })
}

Mock.mock('/api/game/record/list', 'get', () => {
  return {
    code: 200,
    data: {
      list: gameRecordList.slice(0, 10),
      total: gameRecordList.length
    }
  }
})

// 影音内容数据
const mediaList = []
const mediaTypes = ['电影', '动画', '音乐', '纪录片']
for (let i = 1; i <= 50; i++) {
  mediaList.push({
    id: i,
    title: `影音内容${i}`,
    type: mediaTypes[Math.floor(Math.random() * mediaTypes.length)],
    category: '家庭',
    duration: `${Math.floor(Math.random() * 120)}:${String(Math.floor(Math.random() * 60)).padStart(2, '0')}`,
    cover: 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png',
    viewCount: Math.floor(Math.random() * 10000),
    status: i % 4 === 0 ? 0 : 1,
    createTime: Mock.Random.date() + ' ' + Mock.Random.time()
  })
}

Mock.mock('/api/media/list', 'get', () => {
  return {
    code: 200,
    data: {
      list: mediaList.slice(0, 10),
      total: mediaList.length
    }
  }
})

// 活动数据
const activityList = []
for (let i = 1; i <= 50; i++) {
  activityList.push({
    id: i,
    title: `活动${i}`,
    familyName: `快乐家庭${Math.ceil(i / 3)}号`,
    activityTime: Mock.Random.date() + ' ' + Mock.Random.time(),
    location: '家里',
    participantCount: Math.floor(Math.random() * 4) + 2,
    status: i % 3 === 0 ? 2 : i % 2 === 0 ? 1 : 0,
    createTime: Mock.Random.date() + ' ' + Mock.Random.time()
  })
}

Mock.mock('/api/activity/list', 'get', () => {
  return {
    code: 200,
    data: {
      list: activityList.slice(0, 10),
      total: activityList.length
    }
  }
})

// 统计数据
Mock.mock('/api/dashboard/overview', 'get', () => {
  return {
    code: 200,
    data: {
      userCount: 12580,
      familyCount: 3200,
      gameCount: 56800,
      activityCount: 12300
    }
  }
})

console.log('Mock 数据已加载')
