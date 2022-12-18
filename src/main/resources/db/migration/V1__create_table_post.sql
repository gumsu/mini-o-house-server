DROP TABLE IF EXISTS `posts`;
CREATE TABLE `posts`
(
    `id`         bigint PRIMARY KEY AUTO_INCREMENT,
    `title`      varchar(255),
    `content`    varchar(255),
    `created_at` timestamp,
    `updated_at` timestamp,
    `user_id`    bigint
);

ALTER TABLE `posts`
    ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
