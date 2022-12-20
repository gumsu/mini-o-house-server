package com.minihouse.repository;

import com.minihouse.domain.Post;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PostRepository {

    void save(Post post);

    Post findById(Long id);

    void update(Post post);
}
