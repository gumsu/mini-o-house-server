package com.minihouse.request;

import com.minihouse.domain.Comment;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentCreateRequest {

    private String content;
    private Long userId;

    public CommentCreateRequest() {
    }

    @Builder
    public CommentCreateRequest(String content, Long userId) {
        this.content = content;
        this.userId = userId;
    }

    public Comment toEntity(Long postId) {
        return Comment.builder()
            .content(content)
            .postId(postId)
            .userId(userId)
            .build();
    }
}
