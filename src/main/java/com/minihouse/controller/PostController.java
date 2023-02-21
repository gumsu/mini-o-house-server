package com.minihouse.controller;

import com.minihouse.domain.Post;
import com.minihouse.request.PostCreateRequest;
import com.minihouse.request.PostUpdateRequest;
import com.minihouse.response.PostSearchResponse;
import com.minihouse.service.PostService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> updatePost(@PathVariable Long id, @RequestBody PostUpdateRequest request) {
        String title = request.getTitle();
        String content = request.getContent();
        postService.update(id, title, content);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable Long id) {
        postService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping()
    public ResponseEntity<List<PostSearchResponse>> getPage(
        @RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
        @RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {

        List<PostSearchResponse> response = postService.getAll(pageNumber, pageSize)
            .stream()
            .map(PostSearchResponse::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }
}
