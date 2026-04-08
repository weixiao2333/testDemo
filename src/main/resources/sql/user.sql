-- 创建用户表 user
CREATE TABLE IF NOT EXISTS `user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `username` VARCHAR(50) NOT NULL COMMENT '用户名',
    `email` VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
    `password` VARCHAR(100) NOT NULL COMMENT '密码',
    `status` INT DEFAULT 1 COMMENT '状态(0:禁用 1:启用)',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

-- 插入测试数据
INSERT INTO user (username, email, password, status) VALUES
('zhangsan', 'zhangsan@example.com', '123456', 1),
('lisi', 'lisi@example.com', '123456', 1),
('wangwu', 'wangwu@example.com', '123456', 1),
('zhaoliu', 'zhaoliu@example.com', '123456', 0);
