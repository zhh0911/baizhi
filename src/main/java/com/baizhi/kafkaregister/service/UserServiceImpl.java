package com.baizhi.kafkaregister.service;

import com.baizhi.kafkaregister.dao.UserDao;
import com.baizhi.kafkaregister.entity.User;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Properties;
import java.util.UUID;

@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private KafkaTemplate<String,User> kafkaTemplate;



    @Override
    @Transactional
    public void register(User user) {
        userDao.insertOne(user);
//        System.out.println(user.getMail());
       kafkaTemplate.send("t4",UUID.randomUUID().toString(),user);



    }

}
