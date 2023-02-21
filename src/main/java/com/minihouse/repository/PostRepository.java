package com.minihouse.repository;

import com.minihouse.domain.Post;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PostRepository {

    void save(Post post);

    Post findById(Long id);

    void update(Post post);

    void delete(Long id);

    List<Post> getAll(int offset, int pageSize);
}
