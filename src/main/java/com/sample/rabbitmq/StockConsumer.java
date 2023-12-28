package com.sample.rabbitmq;

import jakarta.jms.ConnectionFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.util.ErrorHandler;

@Configuration
public class StockConsumer {
    private static final Log log = LogFactory.getLog(StockConsumer.class);

    @JmsListener(destination = "rabbit-trader-channel", containerFactory = "myFactory")
    public void receiveMessage(String message) {
        log.info("Received " + message);
    }
}
