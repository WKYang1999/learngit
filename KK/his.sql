/*
Navicat MySQL Data Transfer

Source Server         : y
Source Server Version : 50625
Source Host           : localhost:3306
Source Database       : his

Target Server Type    : MYSQL
Target Server Version : 50625
File Encoding         : 65001

Date: 2021-09-26 15:59:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for apply
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of apply
-- ----------------------------

-- ----------------------------
-- Table structure for collection
-- ----------------------------
DROP TABLE IF EXISTS `collection`;
CREATE TABLE `collection` (
  `videoId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `videoId` (`videoId`),
  CONSTRAINT `collection_ibfk_1` FOREIGN KEY (`videoId`) REFERENCES `video` (`id`),
  CONSTRAINT `collection_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of collection
-- ----------------------------
INSERT INTO `collection` VALUES ('2', '1', '1');
INSERT INTO `collection` VALUES ('1', '1', '2');
INSERT INTO `collection` VALUES ('2', '15', '7');
INSERT INTO `collection` VALUES ('2', '1', '8');
INSERT INTO `collection` VALUES ('12', '1', '9');
INSERT INTO `collection` VALUES ('5', '1', '10');

-- ----------------------------
-- Table structure for comments
-- ----------------------------
DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `videoId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `content` varchar(255) NOT NULL,
  `dateComm` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `videoId` (`videoId`),
  KEY `userId` (`userId`),
  CONSTRAINT `comments_ibfk_1` FOREIGN KEY (`videoId`) REFERENCES `video` (`id`),
  CONSTRAINT `comments_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of comments
-- ----------------------------
INSERT INTO `comments` VALUES ('1', '1', '1', '我爱了', '2021-05-05 21:05:52');
INSERT INTO `comments` VALUES ('2', '1', '1', '真好看', '2021-05-05 21:05:53');
INSERT INTO `comments` VALUES ('4', '2', '1', 'Aileen', '2021-05-05 21:05:54');
INSERT INTO `comments` VALUES ('5', '2', '1', '爱', '2021-05-05 21:05:55');
INSERT INTO `comments` VALUES ('6', '2', '1', '爱上了', '2021-05-05 21:10:10');
INSERT INTO `comments` VALUES ('25', '6', '1', '1', '2021-05-15 22:12:45');
INSERT INTO `comments` VALUES ('26', '6', '1', '谢谢你', '2021-05-15 22:13:03');
INSERT INTO `comments` VALUES ('27', '6', '1', '我爱了', '2021-05-15 22:13:10');
INSERT INTO `comments` VALUES ('29', '8', '15', '1111', '2021-05-16 11:59:30');
INSERT INTO `comments` VALUES ('31', '2', '1', '我爱了', '2021-05-16 12:23:12');
INSERT INTO `comments` VALUES ('32', '12', '1', '谢谢你', '2021-05-31 16:49:42');
INSERT INTO `comments` VALUES ('33', '2', '1', '无语', '2021-09-17 16:33:00');

-- ----------------------------
-- Table structure for contents
-- ----------------------------
DROP TABLE IF EXISTS `contents`;
CREATE TABLE `contents` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `yourId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `date` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of contents
-- ----------------------------
INSERT INTO `contents` VALUES ('35', '15', '1', '1111', '2021-06-02 15:19:04');
INSERT INTO `contents` VALUES ('36', '1', '15', '111', '2021-06-02 15:19:10');
INSERT INTO `contents` VALUES ('37', '15', '1', '1111212', '2021-06-02 15:19:18');

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `num` varchar(10) NOT NULL,
  `parentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parentId` (`parentId`),
  CONSTRAINT `dept_ibfk_1` FOREIGN KEY (`parentId`) REFERENCES `dept` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('1', 'xx', 'xx', null);
INSERT INTO `dept` VALUES ('4', 'a', 'a', '1');
INSERT INTO `dept` VALUES ('5', 'ab', 'ab', '4');
INSERT INTO `dept` VALUES ('7', '走', '之', null);
INSERT INTO `dept` VALUES ('8', '(*^_^*)', '/(ㄒoㄒ)/~~', null);
INSERT INTO `dept` VALUES ('9', 'root', 's', '1');
INSERT INTO `dept` VALUES ('11', 'root', 's', null);

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `yourId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES ('1', '3', '1');
INSERT INTO `friend` VALUES ('2', '1', '3');
INSERT INTO `friend` VALUES ('3', '1', '4');
INSERT INTO `friend` VALUES ('4', '4', '1');
INSERT INTO `friend` VALUES ('5', '4', '5');
INSERT INTO `friend` VALUES ('6', '5', '4');
INSERT INTO `friend` VALUES ('7', '4', '6');
INSERT INTO `friend` VALUES ('8', '6', '4');
INSERT INTO `friend` VALUES ('29', '1', '15');
INSERT INTO `friend` VALUES ('30', '15', '1');
INSERT INTO `friend` VALUES ('31', '1', '6');
INSERT INTO `friend` VALUES ('32', '6', '1');

-- ----------------------------
-- Table structure for friendapply
-- ----------------------------
DROP TABLE IF EXISTS `friendapply`;
CREATE TABLE `friendapply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `yourId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friendapply
-- ----------------------------
INSERT INTO `friendapply` VALUES ('2', '7', '1');

-- ----------------------------
-- Table structure for homepage
-- ----------------------------
DROP TABLE IF EXISTS `homepage`;
CREATE TABLE `homepage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of homepage
-- ----------------------------
INSERT INTO `homepage` VALUES ('1', '最新公告', '显示');
INSERT INTO `homepage` VALUES ('2', '热门推荐', '显示');
INSERT INTO `homepage` VALUES ('3', '动漫', '显示');
INSERT INTO `homepage` VALUES ('4', '电影', '显示');
INSERT INTO `homepage` VALUES ('5', '纪录片', '显示');

-- ----------------------------
-- Table structure for likes
-- ----------------------------
DROP TABLE IF EXISTS `likes`;
CREATE TABLE `likes` (
  `videoId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `videoId` (`videoId`),
  CONSTRAINT `likes_ibfk_1` FOREIGN KEY (`videoId`) REFERENCES `video` (`id`),
  CONSTRAINT `likes_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of likes
-- ----------------------------
INSERT INTO `likes` VALUES ('2', '1', '1');
INSERT INTO `likes` VALUES ('8', '15', '2');
INSERT INTO `likes` VALUES ('6', '1', '3');
INSERT INTO `likes` VALUES ('7', '1', '4');
INSERT INTO `likes` VALUES ('8', '1', '5');
INSERT INTO `likes` VALUES ('12', '1', '6');
INSERT INTO `likes` VALUES ('2', '15', '7');
INSERT INTO `likes` VALUES ('5', '1', '8');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `icon` varchar(40) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `leaf` tinyint(255) NOT NULL,
  `accessToken` varchar(100) NOT NULL,
  `parentId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `parentId` (`parentId`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`parentId`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '系统管理', 'fa fa-user', null, '0', '', null);
INSERT INTO `menu` VALUES ('2', '部门管理', 'fa fa-user', '/depts', '0', '/depts!list', '1');
INSERT INTO `menu` VALUES ('3', '用户管理(管理员专属)', 'fa fa-user', '/users', '0', '/users!list', '1');
INSERT INTO `menu` VALUES ('4', '添加', 'fa fa-user', '/depts/add', '1', '/videos!add', '6');
INSERT INTO `menu` VALUES ('5', '角色管理(管理员专属)', 'fa fa-user', '/roles', '0', '/roles!list', '1');
INSERT INTO `menu` VALUES ('6', '视频管理(管理员专属)', 'fa fa-user', '/videos', '0', '/videos!list', '1');
INSERT INTO `menu` VALUES ('7', '信息管理', 'fa fa-user', null, '0', '', null);
INSERT INTO `menu` VALUES ('8', '修改密码', 'fa fa-user', '/users/modify', '0', '/modify!list', '7');
INSERT INTO `menu` VALUES ('9', 'SP管理(管理员专属)', 'fa fa-user', '/videoNums', '0', '/videoNums!list', '1');
INSERT INTO `menu` VALUES ('10', '公告编辑', 'fa fa-user', '/notices', '0', '/notice!list', '25');
INSERT INTO `menu` VALUES ('11', '我的评论', 'fa fa-user', '/comments', '0', '/comments!list', '25');
INSERT INTO `menu` VALUES ('12', '我的收藏', 'fa fa-user', '/collections', '0', '/collection!list', '25');
INSERT INTO `menu` VALUES ('13', '个人信息', 'fa fa-user', '/users/perInfor', '0', '/perInfor!list', '7');
INSERT INTO `menu` VALUES ('14', 'UP申请', 'fa fa-user', '/applys/save', '0', '/apply!list', '1');
INSERT INTO `menu` VALUES ('15', 'UP审批', 'fa fa-user', '/applys', '0', '/applyList!list', '1');
INSERT INTO `menu` VALUES ('16', '视频管理', 'fa fa-user', '/videos/videoUP', '0', '/videoUP!list', '1');
INSERT INTO `menu` VALUES ('17', 'SP管理', 'fa fa-user', '/videoNums/videoNumUP', '0', '/videoNumUP!list', '1');
INSERT INTO `menu` VALUES ('18', '我的点赞', 'fa fa-user', '/likes', '0', '/likes!list', '25');
INSERT INTO `menu` VALUES ('19', '举报视频核查', 'fa fa-user', '/reportVideos', '0', '/reportVideos!list', '1');
INSERT INTO `menu` VALUES ('20', '举报评论核查', 'fa fa-user', '/reportComms', '0', '/reportComms!list', '1');
INSERT INTO `menu` VALUES ('21', '我的好友', 'fa fa-user', '/friends', '0', '/friends!list', '25');
INSERT INTO `menu` VALUES ('22', '好友申请', 'fa fa-user', '/friendApplys', '0', '/friendApplys!list', '25');
INSERT INTO `menu` VALUES ('23', '板块管理', 'fa fa-user', '/homePages', '0', '/homePages!list', '1');
INSERT INTO `menu` VALUES ('24', '我的分享', 'fa fa-user', '/recomms', '0', '/recomms!list', '25');
INSERT INTO `menu` VALUES ('25', '我的专栏', 'fa fa-user', null, '0', ' ', null);

-- ----------------------------
-- Table structure for middle_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `middle_role_menu`;
CREATE TABLE `middle_role_menu` (
  `roleId` int(11) NOT NULL,
  `menuId` int(11) NOT NULL,
  PRIMARY KEY (`roleId`,`menuId`),
  KEY `menuId` (`menuId`),
  CONSTRAINT `middle_role_menu_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`),
  CONSTRAINT `middle_role_menu_ibfk_2` FOREIGN KEY (`menuId`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of middle_role_menu
-- ----------------------------
INSERT INTO `middle_role_menu` VALUES ('1', '1');
INSERT INTO `middle_role_menu` VALUES ('2', '1');
INSERT INTO `middle_role_menu` VALUES ('3', '1');
INSERT INTO `middle_role_menu` VALUES ('1', '3');
INSERT INTO `middle_role_menu` VALUES ('1', '5');
INSERT INTO `middle_role_menu` VALUES ('1', '6');
INSERT INTO `middle_role_menu` VALUES ('1', '7');
INSERT INTO `middle_role_menu` VALUES ('2', '7');
INSERT INTO `middle_role_menu` VALUES ('3', '7');
INSERT INTO `middle_role_menu` VALUES ('1', '8');
INSERT INTO `middle_role_menu` VALUES ('2', '8');
INSERT INTO `middle_role_menu` VALUES ('3', '8');
INSERT INTO `middle_role_menu` VALUES ('1', '9');
INSERT INTO `middle_role_menu` VALUES ('1', '10');
INSERT INTO `middle_role_menu` VALUES ('1', '11');
INSERT INTO `middle_role_menu` VALUES ('2', '11');
INSERT INTO `middle_role_menu` VALUES ('3', '11');
INSERT INTO `middle_role_menu` VALUES ('1', '12');
INSERT INTO `middle_role_menu` VALUES ('2', '12');
INSERT INTO `middle_role_menu` VALUES ('3', '12');
INSERT INTO `middle_role_menu` VALUES ('1', '13');
INSERT INTO `middle_role_menu` VALUES ('2', '13');
INSERT INTO `middle_role_menu` VALUES ('3', '13');
INSERT INTO `middle_role_menu` VALUES ('2', '14');
INSERT INTO `middle_role_menu` VALUES ('1', '15');
INSERT INTO `middle_role_menu` VALUES ('1', '16');
INSERT INTO `middle_role_menu` VALUES ('3', '16');
INSERT INTO `middle_role_menu` VALUES ('1', '17');
INSERT INTO `middle_role_menu` VALUES ('3', '17');
INSERT INTO `middle_role_menu` VALUES ('1', '18');
INSERT INTO `middle_role_menu` VALUES ('2', '18');
INSERT INTO `middle_role_menu` VALUES ('3', '18');
INSERT INTO `middle_role_menu` VALUES ('1', '19');
INSERT INTO `middle_role_menu` VALUES ('1', '20');
INSERT INTO `middle_role_menu` VALUES ('1', '21');
INSERT INTO `middle_role_menu` VALUES ('2', '21');
INSERT INTO `middle_role_menu` VALUES ('3', '21');
INSERT INTO `middle_role_menu` VALUES ('1', '22');
INSERT INTO `middle_role_menu` VALUES ('2', '22');
INSERT INTO `middle_role_menu` VALUES ('3', '22');
INSERT INTO `middle_role_menu` VALUES ('1', '23');
INSERT INTO `middle_role_menu` VALUES ('1', '24');
INSERT INTO `middle_role_menu` VALUES ('2', '24');
INSERT INTO `middle_role_menu` VALUES ('3', '24');
INSERT INTO `middle_role_menu` VALUES ('1', '25');
INSERT INTO `middle_role_menu` VALUES ('2', '25');
INSERT INTO `middle_role_menu` VALUES ('3', '25');

-- ----------------------------
-- Table structure for middle_role_user
-- ----------------------------
DROP TABLE IF EXISTS `middle_role_user`;
CREATE TABLE `middle_role_user` (
  `roleId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`roleId`,`userId`),
  KEY `userId` (`userId`),
  CONSTRAINT `middle_role_user_ibfk_1` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`),
  CONSTRAINT `middle_role_user_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of middle_role_user
-- ----------------------------
INSERT INTO `middle_role_user` VALUES ('1', '1');
INSERT INTO `middle_role_user` VALUES ('3', '3');
INSERT INTO `middle_role_user` VALUES ('3', '6');
INSERT INTO `middle_role_user` VALUES ('2', '7');
INSERT INTO `middle_role_user` VALUES ('2', '8');
INSERT INTO `middle_role_user` VALUES ('3', '15');
INSERT INTO `middle_role_user` VALUES ('2', '18');

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `notice` varchar(255) NOT NULL,
  `time` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice` VALUES ('2', '关于恭喜KK视频网的建立', '2021-05-08 17:19:50', '欢迎大家！');
INSERT INTO `notice` VALUES ('3', '欢迎各位来到我的视频网', '2021-05-08 17:19:55', '感谢各位的支持！');

-- ----------------------------
-- Table structure for recomm
-- ----------------------------
DROP TABLE IF EXISTS `recomm`;
CREATE TABLE `recomm` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) DEFAULT NULL,
  `videoId` int(11) DEFAULT NULL,
  `date` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of recomm
-- ----------------------------
INSERT INTO `recomm` VALUES ('2', '1', '6', '2021-05-15 22:12:22');
INSERT INTO `recomm` VALUES ('3', '3', '1', '2021-05-15 22:12:23');
INSERT INTO `recomm` VALUES ('4', '4', '2', '2021-05-15 22:12:26');
INSERT INTO `recomm` VALUES ('6', '15', '6', '2021-05-16 12:26:56');
INSERT INTO `recomm` VALUES ('7', '15', '8', '2021-05-16 12:27:01');
INSERT INTO `recomm` VALUES ('8', '1', '8', '2021-05-18 04:32:19');
INSERT INTO `recomm` VALUES ('9', '1', '7', '2021-05-18 04:32:22');
INSERT INTO `recomm` VALUES ('10', '1', '2', '2021-05-18 04:32:25');
INSERT INTO `recomm` VALUES ('11', '1', '3', '2021-05-18 04:47:49');
INSERT INTO `recomm` VALUES ('12', '1', '12', '2021-05-31 16:49:33');
INSERT INTO `recomm` VALUES ('13', '15', '2', '2021-05-31 16:51:54');
INSERT INTO `recomm` VALUES ('14', '15', '5', '2021-06-02 15:14:54');
INSERT INTO `recomm` VALUES ('15', '1', '5', '2021-07-16 16:53:41');

-- ----------------------------
-- Table structure for reportcomm
-- ----------------------------
DROP TABLE IF EXISTS `reportcomm`;
CREATE TABLE `reportcomm` (
  `commId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `commId` (`commId`),
  KEY `userId` (`userId`),
  CONSTRAINT `reportcomm_ibfk_1` FOREIGN KEY (`commId`) REFERENCES `comments` (`id`),
  CONSTRAINT `reportcomm_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reportcomm
-- ----------------------------

-- ----------------------------
-- Table structure for reportvideo
-- ----------------------------
DROP TABLE IF EXISTS `reportvideo`;
CREATE TABLE `reportvideo` (
  `videoId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `videoId` (`videoId`),
  KEY `userId` (`userId`),
  CONSTRAINT `reportvideo_ibfk_1` FOREIGN KEY (`videoId`) REFERENCES `video` (`id`),
  CONSTRAINT `reportvideo_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of reportvideo
-- ----------------------------
INSERT INTO `reportvideo` VALUES ('5', '1', '2');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '管理员');
INSERT INTO `role` VALUES ('2', '会员');
INSERT INTO `role` VALUES ('3', 'UP主');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(40) NOT NULL,
  `loginName` varchar(40) NOT NULL,
  `password` char(32) NOT NULL,
  `head` varchar(100) DEFAULT NULL,
  `deptId` int(11) DEFAULT NULL,
  `state` varchar(20) DEFAULT '正常',
  PRIMARY KEY (`id`,`loginName`),
  KEY `id` (`id`),
  KEY `deptId` (`deptId`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`deptId`) REFERENCES `dept` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'yang', 'yang', 'yang', 'xx.jpg', null, '正常');
INSERT INTO `user` VALUES ('3', 'lihua', 'lihua', 'lihua', '6.jpeg', null, '正常');
INSERT INTO `user` VALUES ('6', 'zhangsan', 'zhangsan', 'zhangsan', '9.jpg', null, '正常');
INSERT INTO `user` VALUES ('7', 'kang', 'kang', 'kang', '29.jpg', null, '注销');
INSERT INTO `user` VALUES ('8', '杨', '杨', 'yang', '7.jpg', null, '正常');
INSERT INTO `user` VALUES ('15', 'yangweikang', 'yangweikang', 'yang', '10.jpg', null, '正常');
INSERT INTO `user` VALUES ('18', 'sys', 'sys', 'yang', '7.jpg', null, '正常');

-- ----------------------------
-- Table structure for video
-- ----------------------------
DROP TABLE IF EXISTS `video`;
CREATE TABLE `video` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `videoName` varchar(40) NOT NULL,
  `brief` varchar(255) DEFAULT NULL,
  `primary` varchar(20) DEFAULT NULL,
  `secondary` varchar(20) DEFAULT NULL,
  `state` varchar(20) DEFAULT '已上架',
  `author` varchar(40) DEFAULT NULL,
  `clickNum` int(20) DEFAULT '0',
  `score` varchar(5) DEFAULT '9.1',
  `pic` varchar(255) DEFAULT NULL,
  `uploadId` int(11) NOT NULL,
  `time` varchar(40) DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `uploadId` (`uploadId`),
  CONSTRAINT `video_ibfk_1` FOREIGN KEY (`uploadId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of video
-- ----------------------------
INSERT INTO `video` VALUES ('1', '刀剑神域 Alicization', '林寻独自从矿山牢狱中走出，桐人不知为什么陷入了庞大的幻想风格虚拟世界。', '动漫', '热血', '已上架', '川原砾', '105', '9.1', '01.png', '1', '2021-05-11 18:41:23');
INSERT INTO `video` VALUES ('2', '关于我转生变成史莱姆这档事', '三上悟过着不起眼的人生，在随机杀人魔肆虐下结束了三十七年生涯，看似如此。', '动漫', '热血', '已上架', '伏濑', '248', '9.8', '04.png', '1', '2021-05-1118:41:27');
INSERT INTO `video` VALUES ('3', '狐妖小红娘', '《狐妖小红娘》改编自小新创作的同名漫画。讲述了以红娘为职业的狐妖在为前世恋人牵红线过程当中发生的一系列有趣、神秘的故事。', '动漫', '恋爱', '已上架', '庹小新', '106', '9.1', 'huyao.png', '1', '2021-03-21 18:41:50');
INSERT INTO `video` VALUES ('4', '斗罗大陆', '网络动画《斗罗大陆》改编自中国作家唐家三少原作的同名玄幻小说，由企鹅影视、玄机科技联合出品。', '动漫', '玄幻', '已上架', '唐家三少', '101', '9.5', 'douluo.png', '1', '2021-03-21 18:42:50');
INSERT INTO `video` VALUES ('5', '东京暗鸦', '土御门春虎是个最后之运很差的少年。生在名门的阴阳师家庭，才能丝毫没有。在某个夏日，最为巨大的灾难降临到他的头上。', '动漫', '热血/恋爱', '已上架', '字野耕平（あざの耕平）', '340', '9.1', 'dongjinganya.png', '1', '2021-03-21 18:43:50');
INSERT INTO `video` VALUES ('6', '伍六七之玄武国篇', '为了保护小鸡岛居民和这里平静的生活，伍六七和他的伙伴大保和小飞开启了去往玄武国的冒险旅程，去寻找身世的真相和解救小岛的办法。', '动漫', '热血/搞笑', '已上架', '何小疯', '0', '9.8', 'cike1.png', '1', '2021-03-21 18:44:50');
INSERT INTO `video` VALUES ('7', '灵笼', '《灵笼》讲述的是地球经历一场毁灭性的浩劫，幸存的人类不得不避难于一座悬浮于空中的灯塔上，继而面对善恶对立。', '动漫', '科幻/奇幻', '已下架', '艺画开天', '205', '9.1', 'linglong.png', '1', '2021-03-21 18:45:50');
INSERT INTO `video` VALUES ('8', '雾山五行', '上古时期妖兽纵横，一位神秘道人分别授予了阴阳五行的特殊能力给金木水火土五个家族来抵抗妖兽。', '动漫', '玄幻', '已上架', '林魂', '222', '9.8', 'wushan.png', '1', '2021-03-21 18:46:50');
INSERT INTO `video` VALUES ('9', '飞驰人生', '讲述了一个曾经叱咤赛车界的炒饭摊老板，试图重返车坛，却频频遭现实打脸的故事。', '电影', '励志', '已上架', '韩寒', '100', '9.1', 'feichirensheng.png', '1', '2021-03-21 18:47:50');
INSERT INTO `video` VALUES ('10', '唐人街探案3', '讲述了继“曼谷夺金杀人案”“纽约五行连环杀人案”后，唐仁、秦风被野田昊请到东京，调查一桩离奇的谋杀案的故事。', '电影', '悬疑', '已上架', '陈思诚', '101', '9.1', 'tangrenjie3.png', '1', '2021-03-21 18:47:51');
INSERT INTO `video` VALUES ('12', '西游·降魔篇', '故事围绕年少时期的唐僧与段小姐的相识经过及冒险历程，更会交代周星驰经典对白“爱你一万年”的源起， 故事中涉及一段爱恨交缠的感情。', '电影', '奇幻', '已上架', '周星驰', '106', '9.1', 'xiyou.png', '1', '2021-03-22 19:47:51');
INSERT INTO `video` VALUES ('13', '前任3：再见前任', '讲述了孟云和余飞都因为一点小事而与各自的恋人林佳、丁点分手，在久违的黄金单身期里放飞自我，却又频频翻车引发爆笑的故事', '电影', '爱情', '已上架', '田羽生', '100', '9.1', 'qianren.png', '1', '2021-03-23 19:47:51');
INSERT INTO `video` VALUES ('14', '你的名字', '讲述了男女高中生在梦中相遇，并寻找彼此的故事。', '电影', '爱情/科幻/动画', '已上架', '新海诚', '100', '9.1', 'nidemz.png', '1', '2021-03-24 19:47:51');
INSERT INTO `video` VALUES ('15', '哥斯拉2：怪兽之王', '讲述了哥斯拉、拉顿、魔斯拉、以及基多拉等这些被人类认为是神话中的史前巨兽再次崛起，它们再度上演王者争霸的故事。', '电影', '科幻', '已上架', '迈克尔·道赫蒂', '100', '9.1', 'gesila.png', '1', '2021-03-25 19:47:51');
INSERT INTO `video` VALUES ('16', '行星', '此部纪录片用独特的拟人化手法解构八大行星的故事，并结合最先进的科技向观众们呈现太阳系八大行星的奥秘。', '纪录片', '自然', '已上架', 'BBC', '100', '9.1', 'xingxing.png', '1', '2021-03-26 19:47:51');
INSERT INTO `video` VALUES ('17', '完美星球', '展示了自然力量如何推动、塑造和维持地球生物的多样性，揭示了人类对地球造成的巨大影响以及如何恢复地球完美的生态平衡。', '纪录片', '自然', '已上架', 'BBC', '100', '9.1', 'xingqiu.png', '1', '2021-03-27 19:47:51');
INSERT INTO `video` VALUES ('18', '航拍中国 第三季', '展示中国大美自然景观和丰富多彩的生态环境，彰显经济建设的辉煌成就,揭秘“中国奇迹”背后的创新动力，让世界分享中华文明的博大精深。', '纪录片', '自然', '已上架', '央视纪录国际传媒有限公司', '222', '9.1', 'hangpai.png', '1', '2021-03-28 19:47:51');
INSERT INTO `video` VALUES ('19', '超简中国史·宋', '华夏文化造极于赵宋之世，关于宋朝，你了解多少？', '纪录片', '历史', '已上架', '未知', '100', '9.1', 'song.png', '1', '2021-03-26 11:47:51');
INSERT INTO `video` VALUES ('20', '决战兰州', '兰州战役是西北解放战争史上规模最大、战斗最激烈的一次城市攻坚战。守军之凶悍顽强工事之坚固解放军鏖战之艰苦是西北战场前所未有的', '纪录片', '历史', '已上架', '兰州市委宣传部、甘肃省广播电影电视总台、兰州电视台', '100', '9.1', 'lanzhou.png', '1', '2021-03-25 12:47:51');
INSERT INTO `video` VALUES ('21', '海上丝绸之路', '海上丝绸之路是一条中西贸易之路、也是中西文化交融之路，它使中国从陆地走向海洋。', '纪录片', '历史', '已上架', '上海广播电视台、广东广播电视台、泉州广播电视台', '100', '9.1', 'sichou.png', '1', '2021-03-24 12:47:51');
INSERT INTO `video` VALUES ('40', '121', '1', '动漫', '1', '已下架', '1', '1', '9.1', '7.jpg', '15', '2021-06-02 15:17:13');

-- ----------------------------
-- Table structure for video_num
-- ----------------------------
DROP TABLE IF EXISTS `video_num`;
CREATE TABLE `video_num` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `numId` int(11) NOT NULL,
  `videoId` int(11) NOT NULL,
  `name` varchar(20) NOT NULL,
  `sp` varchar(20) NOT NULL,
  `date` varchar(40) NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `videoId` (`videoId`),
  CONSTRAINT `video_num_ibfk_1` FOREIGN KEY (`videoId`) REFERENCES `video` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of video_num
-- ----------------------------
INSERT INTO `video_num` VALUES ('1', '1', '1', '老年桐人', '1.mp4', '2021-01-11 17:08:37');
INSERT INTO `video_num` VALUES ('2', '1', '2', '暴风龙维鲁德拉', '1.mp4', '2021-01-11 17:10:37');
INSERT INTO `video_num` VALUES ('3', '2', '1', '桐姥爷1', '2.mp4', '2021-01-11 17:09:37');
INSERT INTO `video_num` VALUES ('4', '2', '2', '与哥布林们的邂逅', '2.mp4', '2021-01-11 17:11:37');
INSERT INTO `video_num` VALUES ('5', '3', '2', '哥布林村的战斗', '3.mp4', '2021-01-11 17:11:40');
INSERT INTO `video_num` VALUES ('6', '4', '2', '在矮人王国', '4.mp4', '2021-01-11 17:12:40');
INSERT INTO `video_num` VALUES ('7', '5', '2', '英雄王加泽尔·多瓦格', '5.mp4', '2021-01-11 18:12:40');
INSERT INTO `video_num` VALUES ('8', '6', '2', '静', '6.mp4', '2021-01-11 19:12:40');
INSERT INTO `video_num` VALUES ('9', '7', '2', '爆炎支配者', '6.mp4', '2021-01-11 20:12:40');
INSERT INTO `video_num` VALUES ('10', '1', '5', '第一集', '1.mp4', '2021-01-11 21:12:40');
INSERT INTO `video_num` VALUES ('11', '2', '5', '第二集', '2.mp4', '2021-01-12 21:12:40');
INSERT INTO `video_num` VALUES ('12', '3', '5', '第三集', '3.mp4', '2021-01-13 21:12:40');
INSERT INTO `video_num` VALUES ('13', '4', '5', '第四集', '4.mp4', '2021-01-14 21:12:40');
INSERT INTO `video_num` VALUES ('14', '5', '5', '第五集', '5.mp4', '2021-01-15 21:12:40');
INSERT INTO `video_num` VALUES ('15', '6', '5', '第六集', '6.mp4', '2021-01-16 21:12:40');
INSERT INTO `video_num` VALUES ('16', '7', '5', '第七集', '7.mp4', '2021-01-17 21:12:40');
INSERT INTO `video_num` VALUES ('17', '1', '40', '乌鱼子', '1.mp4', '2021-06-02 15:17:59');
