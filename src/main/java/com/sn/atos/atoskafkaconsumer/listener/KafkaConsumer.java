package com.sn.atos.atoskafkaconsumer.listener;

import com.sn.atos.atoskafkaconsumer.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;


@Service
public class KafkaConsumer {

    @KafkaListener(topics="json-web-books", groupId = "group_json",
    containerFactory = "bookKafkaListenerContainerFactory")
    public void consumeJson(Book book){
        System.out.println("Consumer Book: "+ book);
        // save to database
    }

}
