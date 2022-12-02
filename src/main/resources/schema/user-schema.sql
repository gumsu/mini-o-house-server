DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `id`         bigint PRIMARY KEY AUTO_INCREMENT,
    `name`       varchar(255),
    `nickname`   varchar(255),
    `email`      varchar(255),
    `password`   varchar(255),
    `phone`      varchar(255),
    `created_at` timestamp,
    `updated_at` timestamp,
    `role`       varchar(255)
);
