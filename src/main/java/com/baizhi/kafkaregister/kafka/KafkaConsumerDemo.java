package com.baizhi.kafkaregister.kafka;

import com.baizhi.kafkaregister.entity.User;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

@Component
public class KafkaConsumerDemo {

    @KafkaListener(topics = "t4")
    public void receive(ConsumerRecord<String, User> record) throws MessagingException {
        Properties properties = new Properties();
//        发送邮件
        properties.setProperty("mail.host", "smtp.163.com");
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        //使用JavaMail发送邮件的5个步骤
        //1、创建session
        Session session = Session.getInstance(properties);
        //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);
        //2、通过session得到transport对象
        Transport ts = session.getTransport();
        //3、使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
        ts.connect("smtp.163.com", "18354505066", "zhanghui0911");

        System.out.println("------------------------"+record.value());
        String mail = record.value().getMail();
        String username = record.value().getUsername();
        //4、创建邮件
        Message message = createSimpleMail(session, mail,record.value());
        //5、发送邮件
        ts.sendMessage(message, message.getAllRecipients());

        //4. 拉取新产生的记录
//        while (true) {
//            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(10));
//            for (ConsumerRecord<String, String> record : records) {
//                String mail = record.value();
////
//                //4、创建邮件
//                Message message = createSimpleMail(session, mail);
//                //5、发送邮件
//                ts.sendMessage(message, message.getAllRecipients());
//            }
//
//
//        }


    }

    /**
     * @param session
     * @return
     * @throws Exception
     * @Method: createSimpleMail
     * @Description: 创建一封只包含文本的邮件
     * @Anthor:孤傲苍狼
     */

    public static MimeMessage createSimpleMail(Session session, String mail,User user) throws MessagingException, AddressException {
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress("18354505066@163.com"));
        //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("18354505066@163.com"));
        message.setRecipient(Message.RecipientType.CC,new InternetAddress(mail));
        //邮件的标题
        message.setSubject("欢迎加入大家庭");
        //邮件的文本内容
        message.setContent("注册成功！,姓名为"+user.getUsername()+",密码为"+user.getPassword()+"。", "text/html;charset=UTF-8");
        //返回创建好的邮件对象
        return message;


    }
}
