package com.minihouse.service;

import com.minihouse.domain.Post;
import com.minihouse.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long create(Post post) {
        postRepository.save(post);
        return post.getId();
    }

    public void update(Long id, String title, String content) {
        Post foundPost = postRepository.findById(id);
        foundPost.updatePost(title, content);
        postRepository.update(foundPost);
    }
}
