package com.minihouse.controller;

import com.minihouse.request.CommentCreateRequest;
import com.minihouse.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}/comments")
    public Long registerComment(@PathVariable Long postId, @RequestBody CommentCreateRequest request) {
        return commentService.create(request.toEntity(postId));
    }
}
