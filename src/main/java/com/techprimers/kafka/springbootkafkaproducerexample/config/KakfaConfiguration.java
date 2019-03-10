package com.techprimers.kafka.springbootkafkaproducerexample.config;

import com.techprimers.kafka.springbootkafkaproducerexample.model.User;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KakfaConfiguration {

    @Bean
    public ProducerFactory<String, User> producerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "hal-kfka-q01.dsd.fmcna.com:9093,hal-kfka-q02.dsd.fmcna.com:9093,hal-kfka-q03.dsd.fmcna.com:9093");
        config.put("acks", "all");
        config.put("retries", 10);
        config.put("batch.size", 16384);
        config.put("linger.ms", 1);
        config.put("buffer.memory", 33554432);
        
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.CLIENT_ID_CONFIG, "Demo-Producer");
        config.put("security.protocol", "SSL");
        config.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, "/Users/atulrenapurkar/Desktop/APP_HOME/workspace/sandbox1/spring-boot-kafka-producer-example-master/src/main/resources/server.truststorefabaq09.jks");
        config.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG,  "kafkaqa123");
        config.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, "/Users/atulrenapurkar/Desktop/APP_HOME/workspace/sandbox1/spring-boot-kafka-producer-example-master/src/main/resources/server.keystorefabaq09.jks");
        config.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, "kafkaqa123");
        config.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, "kafkaqa123");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return new DefaultKafkaProducerFactory<>(config);
    }


    @Bean
    public KafkaTemplate<String, User> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }


}
