

-- ----------------------------
-- Table structure for dict_t
-- ----------------------------
DROP TABLE IF EXISTS `dict_t`;
CREATE TABLE `dict_t`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dict_type_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典父类型编码',
  `dict_type_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典父类型名称',
  `dict_code` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典类型编码',
  `dict_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典类型名称',
  `desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `sort` int NULL DEFAULT NULL COMMENT '序号',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic COMMENT='字典表';

-- ----------------------------
-- Records of dict_t
-- ----------------------------
DELETE FROM `dict_t`;
INSERT INTO `dict_t` ( dict_type_code, dict_type_name, dict_code, dict_name, seq ) VALUES ( 'weather_type', '天气类型', 'cloudy_day', '阴天', 1 );
INSERT INTO `dict_t` ( dict_type_code, dict_type_name, dict_code, dict_name, seq ) VALUES ( 'weather_type', '天气类型', 'scorching_heat', '炎热', 2 );
INSERT INTO `dict_t` ( dict_type_code, dict_type_name, dict_code, dict_name, seq ) VALUES ( 'weather_type', '天气类型', 'muggy_weather', '闷热', 3 );
INSERT INTO `dict_t` ( dict_type_code, dict_type_name, dict_code, dict_name, seq ) VALUES ( 'weather_type', '天气类型', 'freakish_weather', '反常天气', 4 );
INSERT INTO `dict_t` ( dict_type_code, dict_type_name, dict_code, dict_name, seq ) VALUES ( 'weather_type', '天气类型', 'rainbow', '虹', 5 );
INSERT INTO `dict_t` ( dict_type_code, dict_type_name, dict_code, dict_name, seq ) VALUES ( 'weather_type', '天气类型', 'fog', '雾', 6 );
INSERT INTO `dict_t` ( dict_type_code, dict_type_name, dict_code, dict_name, seq ) VALUES ( 'weather_type', '天气类型', 'frost', '霜', 7 );
INSERT INTO `dict_t` ( dict_type_code, dict_type_name, dict_code, dict_name, seq ) VALUES ( 'weather_type', '天气类型', 'dew', '露', 8 );
INSERT INTO `dict_t` ( dict_type_code, dict_type_name, dict_code, dict_name, seq ) VALUES ( 'weather_type', '天气类型', 'snow', '雪', 9 );
INSERT INTO `dict_t` ( dict_type_code, dict_type_name, dict_code, dict_name, seq ) VALUES ( 'weather_type', '天气类型', 'hail', '雹', 10 );
INSERT INTO `dict_t` ( dict_type_code, dict_type_name, dict_code, dict_name, seq ) VALUES ( 'weather_type', '天气类型', 'thunder', '雷', 11 );
INSERT INTO `dict_t` ( dict_type_code, dict_type_name, dict_code, dict_name, seq ) VALUES ( 'weather_type', '天气类型', 'heavy_rain', '大雨', 12 );
