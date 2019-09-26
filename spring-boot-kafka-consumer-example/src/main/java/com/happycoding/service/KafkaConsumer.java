package com.happycoding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.happycoding.model.Provider;

@Service
public class KafkaConsumer {

	@Autowired
	private MongoTemplate mongoTemplate;

	@KafkaListener(topics = "#{'${topic.id}'}", containerFactory = "providerKafkaListenerContainerFactory")
	public void consumeJson(Provider provider) {
		mongoTemplate.save(provider);
		System.out.println("Consumed JSON Message:" + provider);
	}
}