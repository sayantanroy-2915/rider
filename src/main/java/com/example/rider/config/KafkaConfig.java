package com.example.rider.config;

import com.example.rider.model.entity.Order;
import com.example.rider.repository.OrderRepository;
import com.example.rider.service.OrderService;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class KafkaConfig {

	@Autowired
	private OrderService orderService;

	@Autowired
	private ObjectMapper objMapper;

	@Bean
	public NewTopic newTopic() {
		return TopicBuilder.name("orders-topic").build();
	}

	@KafkaListener(topics = "orders-topic")
	public void orderReader(String orderJSON) {
		try {
			Order order = objMapper.readValue(orderJSON, Order.class);
			System.out.println("\t***\tOrder Received\t***");
			System.out.println(order);
			orderService.newOrder(order);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
