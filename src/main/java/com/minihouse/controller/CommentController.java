package com.minihouse.controller;

import com.minihouse.request.CommentCreateRequest;
import com.minihouse.request.CommentUpdateRequest;
import com.minihouse.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @PostMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<HttpStatus> updateComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId, @RequestBody CommentUpdateRequest request) {
        String content = request.getContent();
        commentService.update(postId, commentId, content);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId) {
        commentService.delete(commentId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
