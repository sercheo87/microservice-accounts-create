package com.example.microservice.accounts.queue.publishers;

import com.example.microservice.accounts.models.Student;

public interface MessagePublisher {
    void publish(Student student);
}