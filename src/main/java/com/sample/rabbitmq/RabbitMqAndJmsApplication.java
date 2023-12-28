package com.sample.rabbitmq;

import com.rabbitmq.jms.admin.RMQConnectionFactory;
import jakarta.jms.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RabbitMqAndJmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitMqAndJmsApplication.class, args);
    }

    @Bean
    ConnectionFactory connectionFactory() {
        return new RMQConnectionFactory();
    }
}
