package com.example.microservice.accounts.queue.subscribers;

import com.example.microservice.accounts.models.Student;
import com.example.microservice.accounts.repositories.StudentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class RedisMessageSubscriber implements MessageListener {

    StudentRepository studentRepository;

    @Autowired
    public RedisMessageSubscriber(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @SneakyThrows
    public void onMessage(Message message, byte[] pattern) {
        log.info("Message received: {}", message);
        Student student = new ObjectMapper().readValue(message.getBody(), Student.class);

        studentRepository.save(student);
    }
}