package edu.asu.diging.tutorial.spring.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class SenderConfig {
	
	@Bean
	  public KafkaTemplate<String, String> kafkaTemplate() {
		Map<String, Object> senderProps = producerProps();
	    ProducerFactory<String, String> pf =
	              new DefaultKafkaProducerFactory<String, String>(senderProps);
	    return new KafkaTemplate<>(pf);
	  }
		@Bean
		public Map<String, Object> producerProps() {
		    Map<String, Object> props = new HashMap<>();
		    props.put(ProducerConfig.CLIENT_ID_CONFIG, "test.app.producer");
		    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "cedar.dhcp.asu.edu:9092");
		    props.put(ProducerConfig.RETRIES_CONFIG, 0);
		    props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
		    props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
		    props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
		    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class);
		    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		    return props;
		}
		@Bean
		  public Sender sender() {
		    return new Sender();
		  }
}
