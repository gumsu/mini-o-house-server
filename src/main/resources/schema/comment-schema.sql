DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`
(
    `id`         bigint PRIMARY KEY AUTO_INCREMENT,
    `content`    text,
    `created_at` timestamp,
    `updated_at` timestamp,
    `post_id`    bigint,
    `user_id`    bigint,
    `comment_id` bigint
);

ALTER TABLE `comments`
    ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
ALTER TABLE `comments`
    ADD FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`);
