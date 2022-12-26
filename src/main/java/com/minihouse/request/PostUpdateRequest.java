package com.minihouse.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostUpdateRequest {

    private String title;
    private String content;

    PostUpdateRequest() {
    }

    @Builder
    public PostUpdateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
