package com.minihouse.request;

import com.minihouse.domain.Post;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostCreateRequest {

    private String title;
    private String content;

    public PostCreateRequest() {
    }

    @Builder
    public PostCreateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Post toEntity() {
        return Post.builder()
            .title(title)
            .content(content)
            .build();
    }
}
