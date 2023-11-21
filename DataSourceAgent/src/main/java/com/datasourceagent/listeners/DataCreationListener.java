package com.datasourceagent.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.datasourceagent.models.KafkaMessage;
import com.datasourceagent.services.DataCreationService;

@Component
public class DataCreationListener {
	
	private static final Logger logger = LoggerFactory.getLogger(DataCreationListener.class);
	
	@Autowired
	DataCreationService dataCreationService;
	
	@KafkaListener(topics = "test-value-ranges-topic-4", groupId = "test-value-ranges-group-4", containerFactory = "kafkaStatusListenerFactory")
	public void generateBulkData(KafkaMessage kafkaMessage, Acknowledgment Acknowledgment) {
		try {
			Acknowledgment.acknowledge();
			logger.info("Request received for creating data");
			dataCreationService.generateData(kafkaMessage);
		} catch (Exception e) {
			logger.error("Message: {} {}", e.getMessage(), e.getStackTrace());
		}
	}
}
