package com.minihouse.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CommentUpdateRequest {

    private String content;

    public CommentUpdateRequest() {
    }

    @Builder
    public CommentUpdateRequest(String content) {
        this.content = content;
    }
}
