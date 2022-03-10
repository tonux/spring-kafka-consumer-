package com.sn.atos.atoskafkaconsumer.config;

import com.sn.atos.atoskafkaconsumer.model.Book;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfig {

    @Bean
    public ConsumerFactory<String, String> consumerFactory(){

        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.0.0.241:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"group_id");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        return new DefaultKafkaConsumerFactory<>(config);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String , String> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }


    @Bean
    public ConsumerFactory<String, Book> bookConsumerFactory(){

        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "10.0.0.241:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG,"group_json");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false );
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest" );
        config.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.sn.atos.atoskafkaconsumer.model.Book");
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "com.sn.atos.atoskafkaconsumer.model.*");


        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(Book.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String , Book> bookKafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, Book> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(bookConsumerFactory());
        return factory;
    }


}
