

-- ----------------------------
-- Table structure for dict_t
-- ----------------------------
DROP TABLE IF EXISTS `dict_t`;
CREATE TABLE `dict_t`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_type_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典父类型编码',
  `dict_type_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典父类型名称',
  `dict_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典类型编码',
  `dict_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典类型名称',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `sort` tinyint UNSIGNED NULL DEFAULT NULL COMMENT '序号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `search_index` (`dict_type_code`,`dict_code`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic COMMENT='字典表';

-- ----------------------------
-- Records of dict_t
-- ----------------------------
DELETE FROM `dict_t`;
INSERT INTO `dict_t` ( dict_type_code, dict_type_name, dict_code, dict_name, sort ) VALUES ( 'weather_type', '天气类型', 'cloudy_day', '阴天', 1 );
INSERT INTO `dict_t` ( dict_type_code, dict_type_name, dict_code, dict_name, sort ) VALUES ( 'weather_type', '天气类型', 'scorching_heat', '炎热', 2 );
INSERT INTO `dict_t` ( dict_type_code, dict_type_name, dict_code, dict_name, sort ) VALUES ( 'weather_type', '天气类型', 'muggy_weather', '闷热', 3 );
INSERT INTO `dict_t` ( dict_type_code, dict_type_name, dict_code, dict_name, sort ) VALUES ( 'weather_type', '天气类型', 'freakish_weather', '反常天气', 4 );
INSERT INTO `dict_t` ( dict_type_code, dict_type_name, dict_code, dict_name, sort ) VALUES ( 'weather_type', '天气类型', 'rainbow', '虹', 5 );
INSERT INTO `dict_t` ( dict_type_code, dict_type_name, dict_code, dict_name, sort ) VALUES ( 'weather_type', '天气类型', 'fog', '雾', 6 );
INSERT INTO `dict_t` ( dict_type_code, dict_type_name, dict_code, dict_name, sort ) VALUES ( 'weather_type', '天气类型', 'frost', '霜', 7 );
INSERT INTO `dict_t` ( dict_type_code, dict_type_name, dict_code, dict_name, sort ) VALUES ( 'weather_type', '天气类型', 'dew', '露', 8 );
INSERT INTO `dict_t` ( dict_type_code, dict_type_name, dict_code, dict_name, sort ) VALUES ( 'weather_type', '天气类型', 'snow', '雪', 9 );
INSERT INTO `dict_t` ( dict_type_code, dict_type_name, dict_code, dict_name, sort ) VALUES ( 'weather_type', '天气类型', 'hail', '雹', 10 );
INSERT INTO `dict_t` ( dict_type_code, dict_type_name, dict_code, dict_name, sort ) VALUES ( 'weather_type', '天气类型', 'thunder', '雷', 11 );
INSERT INTO `dict_t` ( dict_type_code, dict_type_name, dict_code, dict_name, sort ) VALUES ( 'weather_type', '天气类型', 'heavy_rain', '大雨', 12 );

DROP TABLE IF EXISTS user_t;
CREATE TABLE user_t
(
	id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
	user_name VARCHAR(64) NULL DEFAULT NULL COMMENT '姓名',
	pass_word VARCHAR(64) NULL DEFAULT NULL COMMENT '密码',
	email VARCHAR(128) NULL DEFAULT NULL COMMENT '邮箱',
	nick_name VARCHAR(64) NULL DEFAULT NULL COMMENT '昵称',
	reg_time datetime DEFAULT NULL COMMENT '注册时间',
	PRIMARY KEY (id)  USING BTREE
);

DROP TABLE IF EXISTS user;
CREATE TABLE user
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '姓名',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	email VARCHAR(50) NULL DEFAULT NULL COMMENT '邮箱',
	PRIMARY KEY (id)
);
DELETE FROM user;
INSERT INTO user (id, name, age, email) VALUES
(1, 'Jone', 18, 'test1@baomidou.com'),
(2, 'Jack', 20, 'test2@baomidou.com'),
(3, 'Tom', 28, 'test3@baomidou.com'),
(4, 'Sandy', 21, 'test4@baomidou.com'),
(5, 'Billie', 24, 'test5@baomidou.com');
