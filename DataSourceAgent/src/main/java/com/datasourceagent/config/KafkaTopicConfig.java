package com.datasourceagent.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	@Value("${kafka.delete.retention}")
	public String deleteRetention;

	@Bean
	public NewTopic createBulkDataTopic() {
		return TopicBuilder.name("data-creation-topic-5").config(TopicConfig.DELETE_RETENTION_MS_CONFIG, deleteRetention)
				.partitions(4).build();
	}
	
	@Bean
	public NewTopic createTestValueRangesTopic() {
		return TopicBuilder.name("test-value-ranges-topic-4").config(TopicConfig.DELETE_RETENTION_MS_CONFIG, deleteRetention)
				.partitions(4).build();
	}


}
