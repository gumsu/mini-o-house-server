DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`
(
    `user_id`    bigint PRIMARY KEY AUTO_INCREMENT,
    `name`       varchar(255),
    `nickname`   varchar(255),
    `email`      varchar(255),
    `password`   varchar(255),
    `phone`      varchar(255),
    `created_at` timestamp,
    `updated_at` timestamp,
    `role`       varchar(255)
);

DROP TABLE IF EXISTS `posts`;
CREATE TABLE `posts`
(
    `post_id`    bigint PRIMARY KEY AUTO_INCREMENT,
    `title`      varchar(255),
    `content`    varchar(255),
    `created_at` timestamp,
    `updated_at` timestamp,
    `views`      bigint,
    `likes`      int,
    `user_id`    bigint
);

DROP TABLE IF EXISTS `stores`;
CREATE TABLE `stores`
(
    `store_id`   bigint PRIMARY KEY AUTO_INCREMENT,
    `name`       varchar(255),
    `owner`      varchar(255),
    `status`     varchar(255),
    `created_at` timestamp,
    `updated_at` timestamp,
    `user_id`    bigint
);

DROP TABLE IF EXISTS `products`;
CREATE TABLE `products`
(
    `product_id` bigint PRIMARY KEY AUTO_INCREMENT,
    `name`       varchar(255),
    `price`      int,
    `content`    varchar(255),
    `stock`      int,
    `created_at` timestamp,
    `updated_at` timestamp,
    `category`   varchar(255),
    `store_id`   bigint
);

DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`
(
    `order_id`     bigint PRIMARY KEY AUTO_INCREMENT,
    `recipient`    varchar(255),
    `phone`        varchar(255),
    `address`      varchar(255),
    `status`       varchar(255),
    `created_at`   timestamp,
    `delivered_at` timestamp,
    `user_id`      bigint
);

DROP TABLE IF EXISTS `order_products`;
CREATE TABLE `order_products`
(
    `order_product_id` bigint PRIMARY KEY AUTO_INCREMENT,
    `count`            int,
    `order_id`         bigint,
    `product_id`       bigint
);

DROP TABLE IF EXISTS `post_images`;
CREATE TABLE `post_images`
(
    `post_image_id` bigint PRIMARY KEY AUTO_INCREMENT,
    `name`          varchar(255),
    `origin_name`   varchar(255),
    `file_path`     varchar(255),
    `post_id`       bigint
);

DROP TABLE IF EXISTS `product_tag`;
CREATE TABLE `product_tag`
(
    `product_tag_id` bigint PRIMARY KEY AUTO_INCREMENT,
    `width`          int,
    `height`         int,
    `post_image_id`  bigint,
    `order_id`       bigint
);

DROP TABLE IF EXISTS `product_images`;
CREATE TABLE `product_images`
(
    `product_image_id` bigint PRIMARY KEY AUTO_INCREMENT,
    `name`             varchar(255),
    `origin_name`      varchar(255),
    `file_path`        varchar(255),
    `product_id`       bigint
);

DROP TABLE IF EXISTS `comments`;
CREATE TABLE `comments`
(
    `comment_id` bigint PRIMARY KEY AUTO_INCREMENT,
    `content`    varchar(255),
    `depth`      int,
    `created_at` timestamp,
    `updated_at` timestamp,
    `post_id`    bigint,
    `user_id`    bigint
);

DROP TABLE IF EXISTS `reviews`;
CREATE TABLE `reviews`
(
    `review_id`  bigint PRIMARY KEY AUTO_INCREMENT,
    `content`    varchar(255),
    `likes`      int,
    `rating`     int,
    `created_at` timestamp,
    `updated_at` timestamp,
    `user_id`    bigint,
    `order_id`   bigint
);

ALTER TABLE `posts`
    ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `stores`
    ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `products`
    ADD FOREIGN KEY (`store_id`) REFERENCES `stores` (`store_id`);

ALTER TABLE `orders`
    ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `order_products`
    ADD FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`);

ALTER TABLE `order_products`
    ADD FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`);

ALTER TABLE `post_images`
    ADD FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`);

ALTER TABLE `product_tag`
    ADD FOREIGN KEY (`post_image_id`) REFERENCES `post_images` (`post_image_id`);

ALTER TABLE `product_tag`
    ADD FOREIGN KEY (`order_id`) REFERENCES `posts` (`post_id`);

ALTER TABLE `product_images`
    ADD FOREIGN KEY (`product_id`) REFERENCES `products` (`product_id`);

ALTER TABLE `comments`
    ADD FOREIGN KEY (`post_id`) REFERENCES `posts` (`post_id`);

ALTER TABLE `comments`
    ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `reviews`
    ADD FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

ALTER TABLE `reviews`
    ADD FOREIGN KEY (`order_id`) REFERENCES `orders` (`order_id`);
