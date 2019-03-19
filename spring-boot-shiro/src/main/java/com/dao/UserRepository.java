package com.dao;

import org.springframework.stereotype.Repository;

import com.bean.User;
@Repository
public interface UserRepository extends BaseRepository<User,Long>{
    User findByName(String name);
}
