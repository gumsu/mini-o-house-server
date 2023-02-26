package com.minihouse.domain;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Comment {

    private final Long id;
    private String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final Long postId;
    private final Long userId;
    private final Long commentId;

    @Builder
    public Comment(Long id, String content, LocalDateTime createdAt, LocalDateTime updatedAt,
        Long postId, Long userId, Long commentId) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.postId = postId;
        this.userId = userId;
        this.commentId = commentId;
    }

    public void updateComment(String content) {
        this.content = content;
    }
}
