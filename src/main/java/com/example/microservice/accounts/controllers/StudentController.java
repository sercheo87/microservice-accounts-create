package com.example.microservice.accounts.controllers;

import com.example.microservice.accounts.models.Student;
import com.example.microservice.accounts.queue.publishers.RedisMessagePublisher;
import com.example.microservice.accounts.queue.publishers.StudentProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    StudentProducer studentProducer;
    RedisMessagePublisher redisMessagePublisher;

    @Autowired
    public StudentController(StudentProducer studentProducer,
                             RedisMessagePublisher redisMessagePublisher) {
        this.studentProducer = studentProducer;
        this.redisMessagePublisher = redisMessagePublisher;
    }

    @PostMapping(value = "/send-message-topic", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendMessageTopic(@RequestBody Student student) {
        studentProducer.sendMessage(student);
        return new ResponseEntity<>("Message sent successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/send-message-channel", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sendMessageChannel(@RequestBody Student student) {
        redisMessagePublisher.publish(student);
        return new ResponseEntity<>("Message sent successfully", HttpStatus.OK);
    }

}