SET NAMES utf8mb4;

-- 角色信息表
DROP TABLE IF EXISTS `b_auth_role`;
CREATE TABLE `b_auth_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL COMMENT '角色名称',
  `role_sign` varchar(50) DEFAULT NULL COMMENT '角色标识，用于标识角色（注：shrio中可以根据这标识来）',
  `inline` char(1) NOT NULL DEFAULT '0' COMMENT '是否内置标识(1:内置，0：添加) 默认为0',
  `description` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `operator_id` bigint NOT NULL DEFAULT 0 COMMENT '操作者ID',
  `flag` int NOT NULL DEFAULT 1 COMMENT '有效标识(默认有效)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色信息表';

-- 用户与角色中间表
DROP TABLE IF EXISTS `b_auth_user_role`;

CREATE TABLE `b_auth_user_role` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '表id',
  `user_id` bigint unsigned DEFAULT NULL COMMENT '用户id',
  `role_id` bigint unsigned DEFAULT NULL COMMENT '角色id',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `operator_id` bigint NOT NULL DEFAULT 0 COMMENT '操作者ID',
  `flag` int NOT NULL DEFAULT 1 COMMENT '有效标识(默认有效)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='用户与角色关联表';

INSERT INTO `b_auth_role` (`id`, `name`, `role_sign`, `inline`) VALUES (1, '管理员', 'admin', '1');
-- 用户中心云集控管理员账号ID为： 200
INSERT INTO `b_auth_user_role` (`id`, `user_id`, `role_id`) VALUES (1, 200, 1);