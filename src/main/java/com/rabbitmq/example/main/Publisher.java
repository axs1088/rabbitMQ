package com.rabbitmq.example.main;

import java.util.concurrent.TimeUnit;

import com.rabbitmq.example.Application;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Publisher implements CommandLineRunner {

    private final RabbitTemplate rabbitTemplate;
    private final Consumer consumer;

    public Publisher(Consumer consumer, RabbitTemplate rabbitTemplate) {
        this.consumer = consumer;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(Application.topicExchangeName, "foo.bar.#", "Hello from RabbitMQ!");
        consumer.getLatch().await(10000, TimeUnit.MILLISECONDS);
    }

}
