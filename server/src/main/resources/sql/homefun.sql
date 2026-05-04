-- HomeFun 家庭游戏应用数据库初始化脚本

CREATE DATABASE IF NOT EXISTS `homefun` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE `homefun`;

-- ----------------------------
-- 1. 管理员用户表 sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(30) NOT NULL COMMENT '用户账号',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `nick_name` varchar(30) DEFAULT '' COMMENT '用户昵称',
  `email` varchar(50) DEFAULT '' COMMENT '用户邮箱',
  `phone` varchar(11) DEFAULT '' COMMENT '手机号码',
  `sex` char(1) DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) DEFAULT '' COMMENT '头像地址',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `login_ip` varchar(128) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='管理员用户表';

-- ----------------------------
-- 2. 角色信息表 sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int DEFAULT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_role_key` (`role_key`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='角色信息表';

-- ----------------------------
-- 3. 菜单权限表 sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int DEFAULT 0 COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) DEFAULT NULL COMMENT '路由参数',
  `is_frame` int DEFAULT 1 COMMENT '是否为外链（0是 1否）',
  `is_cache` int DEFAULT 0 COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='菜单权限表';

-- ----------------------------
-- 4. 用户和角色关联表 sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户和角色关联表';

-- ----------------------------
-- 5. 角色和菜单关联表 sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色和菜单关联表';

-- ----------------------------
-- 6. 字典类型表 sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='字典类型表';

-- ----------------------------
-- 7. 字典数据表 sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int DEFAULT NULL COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='字典数据表';

-- ----------------------------
-- 8. 应用用户表 app_user
-- ----------------------------
DROP TABLE IF EXISTS `app_user`;
CREATE TABLE `app_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `openid` varchar(64) DEFAULT NULL COMMENT '微信OpenID',
  `nick_name` varchar(30) DEFAULT '' COMMENT '用户昵称',
  `phone` varchar(11) DEFAULT '' COMMENT '手机号码',
  `avatar` varchar(255) DEFAULT '' COMMENT '头像地址',
  `gender` char(1) DEFAULT '0' COMMENT '性别（0未知 1男 2女）',
  `family_id` bigint DEFAULT NULL COMMENT '所属家庭ID',
  `role_in_family` varchar(20) DEFAULT '' COMMENT '家庭角色（家长/儿童）',
  `status` char(1) DEFAULT '0' COMMENT '用户状态（0正常 1禁用）',
  `total_score` int DEFAULT 0 COMMENT '累计积分',
  `game_count` int DEFAULT 0 COMMENT '参与游戏次数',
  `activity_count` int DEFAULT 0 COMMENT '参与活动次数',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `login_ip` varchar(128) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_family_id` (`family_id`),
  KEY `idx_phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='应用用户表';

-- ----------------------------
-- 9. 家庭表 family
-- ----------------------------
DROP TABLE IF EXISTS `family`;
CREATE TABLE `family` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '家庭ID',
  `name` varchar(50) NOT NULL COMMENT '家庭名称',
  `avatar` varchar(255) DEFAULT '' COMMENT '家庭头像',
  `code` varchar(20) NOT NULL COMMENT '邀请码',
  `creator_id` bigint NOT NULL COMMENT '创建者ID',
  `member_count` int DEFAULT 1 COMMENT '成员数量',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1禁用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_code` (`code`),
  KEY `idx_creator_id` (`creator_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='家庭表';

-- ----------------------------
-- 10. 问答题目表 game_question
-- ----------------------------
DROP TABLE IF EXISTS `game_question`;
CREATE TABLE `game_question` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '题目ID',
  `question` varchar(500) NOT NULL COMMENT '题目内容',
  `category` varchar(20) DEFAULT '' COMMENT '题目分类',
  `options` varchar(1000) NOT NULL COMMENT '选项（JSON格式）',
  `answer` varchar(10) NOT NULL COMMENT '正确答案',
  `difficulty` char(1) DEFAULT '1' COMMENT '难度等级（1简单 2中等 3困难）',
  `explanation` varchar(500) DEFAULT '' COMMENT '题目解析',
  `play_count` int DEFAULT 0 COMMENT '使用次数',
  `correct_count` int DEFAULT 0 COMMENT '正确次数',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1禁用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_category` (`category`),
  KEY `idx_difficulty` (`difficulty`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='问答题目表';

-- ----------------------------
-- 11. 你画我猜词库表 game_word
-- ----------------------------
DROP TABLE IF EXISTS `game_word`;
CREATE TABLE `game_word` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '词汇ID',
  `word` varchar(50) NOT NULL COMMENT '词汇内容',
  `category` varchar(20) DEFAULT '' COMMENT '词汇分类',
  `difficulty` char(1) DEFAULT '1' COMMENT '难度等级（1简单 2中等 3困难）',
  `hint` varchar(100) DEFAULT '' COMMENT '提示词',
  `play_count` int DEFAULT 0 COMMENT '使用次数',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1禁用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_category` (`category`),
  KEY `idx_difficulty` (`difficulty`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='你画我猜词库表';

-- ----------------------------
-- 12. 游戏记录表 game_record
-- ----------------------------
DROP TABLE IF EXISTS `game_record`;
CREATE TABLE `game_record` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `game_type` varchar(20) NOT NULL COMMENT '游戏类型（quiz/draw）',
  `game_name` varchar(50) NOT NULL COMMENT '游戏名称',
  `family_id` bigint NOT NULL COMMENT '家庭ID',
  `family_name` varchar(50) DEFAULT '' COMMENT '家庭名称',
  `room_id` varchar(32) DEFAULT '' COMMENT '房间ID',
  `duration` int DEFAULT 0 COMMENT '游戏时长（秒）',
  `participant_count` int DEFAULT 0 COMMENT '参与人数',
  `total_score` int DEFAULT 0 COMMENT '总得分',
  `winner_name` varchar(30) DEFAULT '' COMMENT '获胜者',
  `game_data` text COMMENT '游戏数据（JSON）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_family_id` (`family_id`),
  KEY `idx_game_type` (`game_type`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='游戏记录表';

-- ----------------------------
-- 13. 媒体分类表 media_category
-- ----------------------------
DROP TABLE IF EXISTS `media_category`;
CREATE TABLE `media_category` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `name` varchar(50) NOT NULL COMMENT '分类名称',
  `icon` varchar(100) DEFAULT '' COMMENT '分类图标',
  `sort` int DEFAULT 0 COMMENT '排序',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1禁用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='媒体分类表';

-- ----------------------------
-- 14. 媒体内容表 media_content
-- ----------------------------
DROP TABLE IF EXISTS `media_content`;
CREATE TABLE `media_content` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '内容ID',
  `title` varchar(100) NOT NULL COMMENT '标题',
  `type` varchar(20) NOT NULL COMMENT '类型（movie/animation/music/documentary）',
  `category_id` bigint DEFAULT NULL COMMENT '分类ID',
  `cover_url` varchar(255) DEFAULT '' COMMENT '封面地址',
  `media_url` varchar(255) DEFAULT '' COMMENT '媒体地址',
  `duration` int DEFAULT 0 COMMENT '时长（秒）',
  `description` varchar(500) DEFAULT '' COMMENT '简介',
  `director` varchar(50) DEFAULT '' COMMENT '导演',
  `actors` varchar(200) DEFAULT '' COMMENT '演员',
  `year` int DEFAULT NULL COMMENT '年份',
  `rating` decimal(3,1) DEFAULT NULL COMMENT '评分',
  `view_count` int DEFAULT 0 COMMENT '观看次数',
  `favorite_count` int DEFAULT 0 COMMENT '收藏次数',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1下架）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_category_id` (`category_id`),
  KEY `idx_type` (`type`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='媒体内容表';

-- ----------------------------
-- 15. 活动表 activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '活动ID',
  `title` varchar(100) NOT NULL COMMENT '活动标题',
  `family_id` bigint NOT NULL COMMENT '家庭ID',
  `family_name` varchar(50) DEFAULT '' COMMENT '家庭名称',
  `activity_time` datetime NOT NULL COMMENT '活动时间',
  `location` varchar(100) DEFAULT '' COMMENT '活动地点',
  `description` varchar(500) DEFAULT '' COMMENT '活动描述',
  `cover_url` varchar(255) DEFAULT '' COMMENT '封面图片',
  `participant_ids` varchar(500) DEFAULT '' COMMENT '参与者ID列表',
  `max_participants` int DEFAULT 10 COMMENT '最大参与人数',
  `participant_count` int DEFAULT 0 COMMENT '实际参与人数',
  `status` char(1) DEFAULT '0' COMMENT '状态（0待开始 1进行中 2已结束 3已取消）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 1代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_family_id` (`family_id`),
  KEY `idx_activity_time` (`activity_time`),
  KEY `idx_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='活动表';

-- ----------------------------
-- 16. 初始化数据
-- ----------------------------

-- 插入管理员用户 (密码: admin123)
INSERT INTO `sys_user` (`id`, `username`, `password`, `nick_name`, `email`, `phone`, `sex`, `status`, `create_by`, `create_time`) VALUES
(1, 'admin', '$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE/sLenFcLigBC', '管理员', 'admin@homefun.com', '15888888888', '0', '0', 'system', NOW());

-- 插入角色
INSERT INTO `sys_role` (`id`, `role_name`, `role_key`, `role_sort`, `status`, `create_by`, `create_time`, `remark`) VALUES
(1, '超级管理员', 'admin', 1, '0', 'system', NOW(), '拥有所有权限'),
(2, '普通管理员', 'common', 2, '0', 'system', NOW(), '普通管理员角色');

-- 关联用户角色
INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES (1, 1);

-- 插入菜单数据
INSERT INTO `sys_menu` (`id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `menu_type`, `status`, `perms`, `icon`, `create_time`) VALUES
(1, '系统管理', 0, 1, 'system', NULL, 'M', '0', '', 'el-icon-setting', NOW()),
(100, '仪表盘', 0, 2, 'dashboard', 'dashboard/index', 'C', '0', 'system:dashboard:list', 'el-icon-data-line', NOW()),
(200, '用户管理', 0, 3, 'user', NULL, 'M', '0', '', 'el-icon-user', NOW()),
(201, '应用用户', 200, 1, 'app', 'user/appUser', 'C', '0', 'user:app:list', 'el-icon-user-filled', NOW()),
(202, '管理员用户', 200, 2, 'admin', 'user/adminUser', 'C', '0', 'user:admin:list', 'el-icon-user', NOW()),
(300, '家庭管理', 0, 4, 'family', 'family/list', 'C', '0', 'family:list', 'el-icon-house', NOW()),
(400, '游戏管理', 0, 5, 'game', NULL, 'M', '0', '', 'el-icon-game', NOW()),
(401, '问答题目', 400, 1, 'question', 'game/question', 'C', '0', 'game:question:list', 'el-icon-question-filled', NOW()),
(402, '你画我猜', 400, 2, 'word', 'game/word', 'C', '0', 'game:word:list', 'el-icon-edit', NOW()),
(403, '游戏记录', 400, 3, 'record', 'game/record', 'C', '0', 'game:record:list', 'el-icon-document', NOW()),
(500, '媒体管理', 0, 6, 'media', NULL, 'M', '0', '', 'el-icon-video-camera', NOW()),
(501, '内容管理', 500, 1, 'content', 'media/content', 'C', '0', 'media:content:list', 'el-icon-film', NOW()),
(502, '分类管理', 500, 2, 'category', 'media/category', 'C', '0', 'media:category:list', 'el-icon-folder', NOW()),
(600, '活动管理', 0, 7, 'activity', 'activity/list', 'C', '0', 'activity:list', 'el-icon-calendar', NOW()),
(700, '系统设置', 0, 8, 'system', NULL, 'M', '0', '', 'el-icon-tools', NOW()),
(701, '菜单管理', 700, 1, 'menu', 'system/menu', 'C', '0', 'system:menu:list', 'el-icon-menu', NOW()),
(702, '字典管理', 700, 2, 'dict', 'system/dict', 'C', '0', 'system:dict:list', 'el-icon-collection', NOW());

-- 关联角色菜单
INSERT INTO `sys_role_menu` (`role_id`, `menu_id`)
SELECT 1, `id` FROM `sys_menu`;

-- 插入字典类型
INSERT INTO `sys_dict_type` (`dict_name`, `dict_type`, `status`, `remark`) VALUES
('用户性别', 'sys_user_sex', '0', '用户性别列表'),
('游戏难度', 'game_difficulty', '0', '游戏难度等级'),
('媒体类型', 'media_type', '0', '媒体内容类型'),
('活动状态', 'activity_status', '0', '活动状态'),
('题目分类', 'question_category', '0', '问答题目分类'),
('词库分类', 'word_category', '0', '你画我猜词库分类');

-- 插入字典数据
INSERT INTO `sys_dict_data` (`dict_sort`, `dict_label`, `dict_value`, `dict_type`, `list_class`, `status`) VALUES
(1, '男', '0', 'sys_user_sex', '', '0'),
(2, '女', '1', 'sys_user_sex', '', '0'),
(3, '未知', '2', 'sys_user_sex', 'danger', '0'),
(1, '简单', '1', 'game_difficulty', 'success', '0'),
(2, '中等', '2', 'game_difficulty', 'warning', '0'),
(3, '困难', '3', 'game_difficulty', 'danger', '0'),
(1, '电影', 'movie', 'media_type', '', '0'),
(2, '动画', 'animation', 'media_type', '', '0'),
(3, '音乐', 'music', 'media_type', '', '0'),
(4, '纪录片', 'documentary', 'media_type', '', '0'),
(1, '待开始', '0', 'activity_status', 'info', '0'),
(2, '进行中', '1', 'activity_status', 'success', '0'),
(3, '已结束', '2', 'activity_status', 'default', '0'),
(4, '已取消', '3', 'activity_status', 'danger', '0'),
(1, '科学', 'science', 'question_category', '', '0'),
(2, '生活', 'life', 'question_category', '', '0'),
(3, '艺术', 'art', 'question_category', '', '0'),
(4, '体育', 'sports', 'question_category', '', '0'),
(5, '地理', 'geography', 'question_category', '', '0'),
(6, '历史', 'history', 'question_category', '', '0'),
(1, '动物', 'animal', 'word_category', '', '0'),
(2, '日常', 'daily', 'word_category', '', '0'),
(3, '食物', 'food', 'word_category', '', '0'),
(4, '运动', 'sports', 'word_category', '', '0');
