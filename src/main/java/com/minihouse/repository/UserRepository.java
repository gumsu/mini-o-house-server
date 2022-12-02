package com.minihouse.repository;

import com.minihouse.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserRepository {

    void save(User user);
}
