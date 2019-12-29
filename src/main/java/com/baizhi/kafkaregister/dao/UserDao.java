package com.baizhi.kafkaregister.dao;

import com.baizhi.kafkaregister.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {
    @Select("select * from t_register")
    public List<User> findAll();

    @Insert("insert into t_register(username,password,mail) values(#{username},#{password},#{mail})")
    public void insertOne(User user);
}
