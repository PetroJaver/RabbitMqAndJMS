package com.sample.rabbitmq;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class StockQuoter {
    private static final Log log = LogFactory.getLog(StockQuoter.class);

    private List<String> stocks = new ArrayList<>();

    private Map<String, Double> lastPrice = new HashMap<>();

    {
        stocks.add("AAPL");
        stocks.add("GD");
        stocks.add("BRK.B");

        lastPrice.put("AAPL", 494.64);
        lastPrice.put("GD", 86.74);
        lastPrice.put("BRK.B", 113.59);
    }

    private JmsTemplate jmsTemplate;

    public StockQuoter(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @Scheduled(fixedRate = 2000L)
    public void run() throws Exception {
        Collections.shuffle(stocks);
        final String symbol = stocks.get(0);

        if (RandomUtils.nextBoolean()) {
            lastPrice.put(symbol, new Double(Math.round(lastPrice.get(symbol) * (1 + RandomUtils.nextInt(10) / 100.0) * 100) / 100));
        } else {
            lastPrice.put(symbol, new Double(Math.round(lastPrice.get(symbol) * (1 - RandomUtils.nextInt(10) / 100.0) * 100) / 100));
        }

        MessageCreator messageCreator = session -> session.createObjectMessage("Quote..." + symbol + " is now " + lastPrice.get(symbol));

        jmsTemplate.send("rabbit-trader-channel", messageCreator);
    }
}
