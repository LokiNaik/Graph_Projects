package com.datasourceagent.listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import com.datasourceagent.models.KafkaMessage;
import com.datasourceagent.services.DataSourceService;

@Component
public class DataSourceListener {

	private static final Logger logger = LoggerFactory.getLogger(DataSourceListener.class);

	@Autowired
	DataSourceService dataSourceService;

	@KafkaListener(topics = "data-creation-topic-5", groupId = "data-creation-group-5", containerFactory = "kafkaStatusListenerFactory")
	public void generateBulkData(KafkaMessage kafkaMessage, Acknowledgment Acknowledgment) {
		try {
			Acknowledgment.acknowledge();
			logger.info("Request received for creating data");
			dataSourceService.generateData(kafkaMessage);
		} catch (Exception e) {
			logger.error("Message: {} {}", e.getMessage(), e.getStackTrace());
		}
	}

}
