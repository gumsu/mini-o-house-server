package com.minihouse.response;

import com.minihouse.domain.Post;
import java.time.LocalDateTime;
import lombok.Getter;

@Getter
public class PostSearchResponse {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime updatedAt;

    public PostSearchResponse(Post entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.createdDate = entity.getCreatedAt();
        this.updatedAt = entity.getUpdatedAt();
    }
}
