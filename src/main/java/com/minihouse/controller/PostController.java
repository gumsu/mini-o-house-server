package com.minihouse.controller;

import com.minihouse.domain.Post;
import com.minihouse.request.PostCreateRequest;
import com.minihouse.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping()
    public Long registerPost(@RequestBody PostCreateRequest request) {
        Post post = request.toEntity();
        return postService.create(post);
    }
}
