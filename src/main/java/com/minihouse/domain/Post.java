package com.minihouse.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Post {

    private final Long id;
    private String title;
    private String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final Long userId;

    @Builder
    public Post(Long id, String title, String content, LocalDateTime createdAt,
        LocalDateTime updatedAt, Long userId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
    }

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
