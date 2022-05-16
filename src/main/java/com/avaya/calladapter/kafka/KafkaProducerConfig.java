package com.avaya.calladapter.kafka;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class KafkaProducerConfig {

    private String kafkaBootstrap;
    private String schemaRegistryUrl;

    public KafkaProducerConfig(
        @Value("${kafka.bootstrap}") final String kafkaBootstrap, @Value("${schema.registry.url}") final String schemaRegistryUrl
    ) {
        this.kafkaBootstrap = kafkaBootstrap;
        this.schemaRegistryUrl = schemaRegistryUrl;
    }

    @Bean
    public Properties propertiesFactory() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrap);
        properties.put(ProducerConfig.RETRIES_CONFIG, 1);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        properties.put("schema.registry.url", schemaRegistryUrl);
        return properties;
    }

    @Bean
    public KafkaProducer<String, GenericRecord> kafkaProducerCall() {
        return new KafkaProducer<String, GenericRecord>(propertiesFactory());
    }
}
