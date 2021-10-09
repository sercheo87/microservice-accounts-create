package com.example.microservice.accounts.queue.publishers;

import com.example.microservice.accounts.models.Student;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class RedisMessagePublisher implements MessagePublisher {

    RedisTemplate<String, Object> redisTemplate;
    ChannelTopic topic;

    @Autowired
    public RedisMessagePublisher(RedisTemplate<String, Object> redisTemplate, ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }

    @Override
    public void publish(Student student) {
        log.info("Message: {}", student);
        redisTemplate.convertAndSend(topic.getTopic(), student);
    }
}