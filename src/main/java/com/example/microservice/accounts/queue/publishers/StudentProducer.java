package com.example.microservice.accounts.queue.publishers;

import com.example.microservice.accounts.models.Student;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class StudentProducer {

    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public StudentProducer(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Value("${redis.student.topic}")
    String messageTopic;

    public void sendMessage(Student student) {
        log.info("Sending Student details: {}", student);
        redisTemplate.convertAndSend(messageTopic, student);
    }

}