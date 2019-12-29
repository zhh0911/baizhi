package com.baizhi.kafkaregister;

import com.baizhi.kafkaregister.dao.UserDao;
import com.baizhi.kafkaregister.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaRegisterApplicationTests {
    @Autowired
    private UserDao userDao;

    @Test
    public void contextLoads() {
        User user = new User();
        user.setUsername("zhangsan1");
        user.setPassword("123456");
        user.setMail("123@qq.com");
         userDao.insertOne(user);
    }

}
