
# HomeFun - 家庭乐部署与启动指南

家庭乐是一个面向家庭的互动娱乐平台，包含小程序端、管理后台和后端服务。

## 项目概述

HomeFun 包含三个主要应用：
1. **小程序端** - 用户端应用，家庭用户使用
2. **管理后台** - 管理员使用的后台管理系统
3. **后端服务** - 基于 Spring Boot 3 的 RESTful API 服务

## 技术栈

### 前端技术栈
- **小程序端**: Vue 3 + Vite + Pinia + Vue Router + Sass
- **管理后台**: Vue 3 + Vite + Element Plus + Pinia + Vue Router + ECharts + Axios + MockJS

### 后端技术栈
- **后端服务**: Spring Boot 3.2.0 + Spring Security + MyBatis Plus + MySQL 8 + Redis + Druid 连接池
- **数据库**: MySQL 8
- **缓存**: Redis

## 项目结构

```
/workspace/
├── src/                    # 小程序端源码
├── admin/                  # 管理后台源码
├── server/                 # 后端服务源码
├── docs/                   # 项目文档
└── README.md              # 本文档
```

## 环境要求

### 系统环境
- **Node.js**: &gt;= 18.0.0
- **JDK**: &gt;= 17.0.0
- **Maven**: &gt;= 3.8.0
- **MySQL**: &gt;= 8.0.0
- **Redis**: &gt;= 6.0.0

### 端口占用说明
| 应用 | 端口 | 说明 |
|------|------|------|
| 小程序端 | 3000 | http://localhost:3000 |
| 管理后台 | 3001 | http://localhost:3001 |
| 后端服务 | 8080 | http://localhost:8080/homefun |
| MySQL | 3306 | |
| Redis | 6379 | |

---

## 一、数据库准备

### 1.1 安装并启动 MySQL

#### Linux (Ubuntu/Debian)
```bash
# 安装 MySQL
sudo apt update
sudo apt install mysql-server -y

# 启动 MySQL 服务
sudo systemctl start mysql
sudo systemctl enable mysql

# 检查服务状态
sudo systemctl status mysql
```

#### macOS (Homebrew)
```bash
# 安装 MySQL
brew install mysql

# 启动 MySQL 服务
brew services start mysql
```

#### Windows
1. 下载 MySQL 8.0 安装包
2. 安装并配置 root 密码
3. 启动 MySQL 服务

### 1.2 安装并启动 Redis

#### Linux (Ubuntu/Debian)
```bash
# 安装 Redis
sudo apt install redis-server -y

# 启动 Redis 服务
sudo systemctl start redis
sudo systemctl enable redis

# 检查服务状态
sudo systemctl status redis
```

#### macOS (Homebrew)
```bash
# 安装 Redis
brew install redis

# 启动 Redis 服务
brew services start redis
```

#### Windows
1. 下载 Redis for Windows 或使用 WSL2
2. 启动 Redis 服务

### 1.3 创建数据库

登录 MySQL：
```bash
mysql -u root -p
```

创建数据库和用户（可选）：
```sql
CREATE DATABASE IF NOT EXISTS homefun CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

导入数据库脚本：
```bash
mysql -u root -p homefun &lt; /workspace/server/src/main/resources/sql/homefun.sql
```

或者在 MySQL 命令行中：
```sql
USE homefun;
SOURCE /workspace/server/src/main/resources/sql/homefun.sql;
```

### 1.4 验证数据库

```sql
USE homefun;
SHOW TABLES;
```

应该能看到以下表：
- sys_user - 系统用户表
- sys_role - 角色表
- sys_menu - 菜单表
- sys_dict_type - 字典类型表
- sys_dict_data - 字典数据表
- app_user - 应用用户表
- family - 家庭表
- game_question - 游戏题库表
- game_word - 猜词游戏词库表
- game_record - 游戏记录表
- media_category - 媒体分类表
- media_content - 媒体内容表
- activity - 活动表

---

## 二、后端服务部署

### 2.1 配置后端

配置文件位置：[server/src/main/resources/application.yml](file:///workspace/server/src/main/resources/application.yml)

**关键配置项：
```yaml
server:
  port: 8080
  servlet:
    context-path: /homefun

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/homefun?useUnicode=true&amp;characterEncoding=utf8&amp;zeroDateTimeBehavior=convertToNull&amp;useSSL=true&amp;serverTimezone=GMT%2B8
    username: root
    password: root  # 修改为你的 MySQL 密码

  data:
    redis:
      host: localhost
      port: 6379
      password:  # 如果 Redis 有密码请配置
```

根据你的环境修改数据库和 Redis 配置。

### 2.2 编译并启动后端

#### 方式一：使用 Maven 命令

进入 server 目录：
```bash
cd /workspace/server
```

编译并下载依赖：
```bash
mvn clean install -DskipTests
```

启动应用：
```bash
mvn spring-boot:run
```

#### 方式二：直接运行 JAR 包

```bash
# 先打包
mvn clean package -DskipTests

# 运行
java -jar target/homefun-admin.jar
```

#### 方式三：使用 IDE

在 IDE (IntelliJ IDEA / Eclipse) 中打开项目，找到主类：
`com.homefun.HomeFunApplication`，右键运行 `main` 方法。

### 2.3 验证后端服务

打开浏览器访问：
```
http://localhost:8080/homefun
```

或者使用 curl：
```bash
curl http://localhost:8080/homefun
```

---

## 三、小程序端部署

### 3.1 安装依赖

进入项目根目录：
```bash
cd /workspace
```

安装 npm 依赖：
```bash
npm install
```

### 3.2 启动开发服务器

```bash
npm run dev
```

启动成功后，访问：
```
http://localhost:3000
```

### 3.3 构建生产版本

```bash
npm run build
```

构建产物在 `dist/` 目录，可部署到 Nginx 或其他静态文件服务器。

---

## 四、管理后台部署

### 4.1 安装依赖

进入 admin 目录：
```bash
cd /workspace/admin
```

安装 npm 依赖：
```bash
npm install
```

### 4.2 启动开发服务器

```bash
npm run dev
```

启动成功后，访问：
```
http://localhost:3001
```

### 4.3 构建生产版本

```bash
npm run build
```

构建产物在 `admin/dist/` 目录，可部署到 Nginx 或其他静态文件服务器。

---

## 五、Nginx 部署配置（生产环境）

### 5.1 安装 Nginx

#### Linux (Ubuntu/Debian)
```bash
sudo apt install nginx -y
sudo systemctl start nginx
sudo systemctl enable nginx
```

#### macOS (Homebrew)
```bash
brew install nginx
brew services start nginx
```

### 5.2 Nginx 配置示例

创建配置文件 `/etc/nginx/sites-available/homefun`：

```nginx
# 小程序端
server {
    listen 80;
    server_name your-domain.com;

    root /workspace/dist;
    index index.html;

    location / {
        try_files $uri $uri/ /index.html;
    }

    location /api {
        proxy_pass http://localhost:8080/homefun;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }
}

# 管理后台
server {
    listen 81;
    server_name admin.your-domain.com;

    root /workspace/admin/dist;
    index index.html;

    location / {
        try_files $uri $uri/ /index.html;
    }

    location /api {
        proxy_pass http://localhost:8080/homefun;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
    }
}
```

启用配置：
```bash
sudo ln -s /etc/nginx/sites-available/homefun /etc/nginx/sites-enabled/
sudo nginx -t
sudo systemctl reload nginx
```

---

## 六、常见问题

### 6.1 端口被占用

检查端口占用：
```bash
# Linux/macOS
lsof -i :3000
lsof -i :3001
lsof -i :8080

# Windows
netstat -ano | findstr "3000"
```

### 6.2 Maven 依赖下载失败

修改 Maven 配置，添加国内镜像源：

编辑 `~/.m2/settings.xml`：

```xml
&lt;mirrors&gt;
  &lt;mirror&gt;
    &lt;id&gt;aliyunmaven&lt;/id&gt;
    &lt;mirrorOf&gt;*&lt;/mirrorOf&gt;
    &lt;name&gt;阿里云公共仓库&lt;/name&gt;
    &lt;url&gt;https://maven.aliyun.com/repository/public&lt;/url&gt;
  &lt;/mirror&gt;
&lt;/mirrors&gt;
```

### 6.3 MySQL 连接失败

- 确认 MySQL 服务已启动
- 确认数据库名称、用户名、密码正确
- 确认数据库字符集为 utf8mb4
- 确认防火墙允许 3306 端口

### 6.4 Redis 连接失败

- 确认 Redis 服务已启动
- 确认 Redis 密码配置正确
- 确认防火墙允许 6379 端口

### 6.5 npm install 慢

使用国内 npm 镜像：
```bash
npm config set registry https://registry.npmmirror.com
```

---

## 七、快速启动脚本（开发环境）

### Linux/macOS 可以使用以下脚本快速启动所有服务：

```bash
#!/bin/bash
# 快速启动 HomeFun 开发环境

echo "=== 启动 MySQL..."
# 根据你的系统调整命令

echo "=== 启动 Redis..."
# 根据你的系统调整命令

echo "=== 启动后端服务..."
cd /workspace/server &amp;&amp; mvn spring-boot:run &amp;
BACKEND_PID=$!
sleep 10

echo "=== 启动小程序端..."
cd /workspace &amp;&amp; npm run dev &amp;
MINIPROGRAM_PID=$!

echo "=== 启动管理后台..."
cd /workspace/admin &amp;&amp; npm run dev &amp;
ADMIN_PID=$!

echo ""
echo "========================================"
echo "HomeFun 开发环境已启动！"
echo "小程序端: http://localhost:3000"
echo "管理后台: http://localhost:3001"
echo "后端服务: http://localhost:8080/homefun"
echo "========================================"

# 等待用户输入停止
read -p "按 Enter 键停止所有服务..."

kill $BACKEND_PID $MINIPROGRAM_PID $ADMIN_PID
```

---

## 八、默认账号

### 管理后台默认账号
- **用户名**: admin
- **密码**: admin123

---

## 九、开发规范

### Git 提交规范
- feat: 新功能
- fix: 修复 bug
- docs: 文档更新
- style: 代码格式调整
- refactor: 重构
- test: 测试相关
- chore: 构建/工具链相关

### 代码风格
- 前端遵循 ESLint 规则
- 后端遵循 Alibaba Java 编码规范

---

## 十、技术支持

如有问题，请查看项目文档目录：
[docs/](file:///workspace/docs)

---

## 许可证

MIT License

