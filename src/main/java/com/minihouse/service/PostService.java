package com.minihouse.service;

import com.minihouse.domain.Post;
import com.minihouse.exception.PostNotFoundException;
import com.minihouse.repository.PostRepository;
import java.util.List;
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

    @Transactional
    public void update(Long id, String title, String content) {
        Post foundPost = findById(id);
        foundPost.updatePost(title, content);
        postRepository.update(foundPost);
    }

    @Transactional
    public void delete(Long id) {
        Post foundPost = findById(id);
        postRepository.delete(foundPost.getId());
    }

    public List<Post> getPage(int pageNumber, int pageSize){
        int offset = pageNumber * pageSize;
        return postRepository.getPage(offset, pageSize);
    }

    public Post findById(Long postId) {
        Post foundPost = postRepository.findById(postId);
        if (foundPost == null) {
            throw new PostNotFoundException("게시글이 존재하지 않습니다.");
        }
        return foundPost;
    }
}
