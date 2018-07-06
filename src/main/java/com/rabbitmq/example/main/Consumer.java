package com.rabbitmq.example.main;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Consumer {
    private CountDownLatch latch = new CountDownLatch(1);
/*
For convenience, this POJO also has a CountDownLatch.
This allows it to signal that the message is received.
This is something you are not likely to implement in a production application.
*/

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
