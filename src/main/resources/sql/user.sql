-- ????? user ??
CREATE TABLE IF NOT EXISTS `user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '??',
    `username` VARCHAR(50) NOT NULL COMMENT '???',
    `email` VARCHAR(100) DEFAULT NULL COMMENT '??',
    `password` VARCHAR(100) NOT NULL COMMENT '??',
    `status` INT DEFAULT 1 COMMENT '??(0:?? 1:??)',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '??',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '??',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='????';

-- ?????
INSERT INTO user (username, email, password, status) VALUES
('zhangsan', 'zhangsan@example.com', '123456', 1),
('lisi', 'lisi@example.com', '123456', 1),
('wangwu', 'wangwu@example.com', '123456', 1),
('zhaoliu', 'zhaoliu@example.com', '123456', 0);
